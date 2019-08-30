package org.mymvc.framework;

import org.mymvc.framework.helper.BeanHelper;
import org.mymvc.framework.helper.ClassHelper;
import org.mymvc.framework.helper.ControllerHelper;
import org.mymvc.framework.helper.IocHelper;
import org.mymvc.framework.util.ClassUtil;

/**
 * Created by H.J
 * 2019/8/30
 */
public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
