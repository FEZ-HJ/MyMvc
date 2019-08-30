package org.mymvc.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.mymvc.framework.bean.Data;
import org.mymvc.framework.bean.Handler;
import org.mymvc.framework.bean.Param;
import org.mymvc.framework.bean.View;
import org.mymvc.framework.helper.BeanHelper;
import org.mymvc.framework.helper.ConfigHelper;
import org.mymvc.framework.helper.ControllerHelper;
import org.mymvc.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by H.J
 * 2019/8/30
 */
@WebServlet(urlPatterns = "/",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig)throws ServletException{
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler != null){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Map<String,Object> paramMap = new HashMap<String,Object>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if(StringUtils.isNoneEmpty(body)){
                String[] params = body.split("&");
                if(ArrayUtils.isNotEmpty(params)){
                    for(String param : params){
                        String[] array = param.split("=");
                        if(ArrayUtils.isNotEmpty(array) && array.length == 2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);

            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);

            if(result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if(StringUtils.isNoneEmpty(path)){
                    if(path.startsWith("/")){
                        response.sendRedirect(request.getContextPath() + path);
                    }else{
                        Map<String,Object> model = view.getModel();
                        for(Map.Entry<String,Object> entry: model.entrySet()){
                            request.setAttribute(entry.getKey(),entry.getValue());
                        }
                        request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request,response);
                    }
                }
            }else if(result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if(model != null){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
