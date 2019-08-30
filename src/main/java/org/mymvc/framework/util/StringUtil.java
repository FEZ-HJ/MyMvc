package org.mymvc.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by H.J
 * 2019/8/29
 */
public final class StringUtil {

    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
