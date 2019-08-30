package org.mymvc.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by H.J
 * 2019/8/29
 */
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class controllerclass, Method actionMethod) {
        this.controllerClass = controllerclass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass(){
            return controllerClass;
    }

    public Method getActionMethod(){
        return actionMethod;
    }
}
