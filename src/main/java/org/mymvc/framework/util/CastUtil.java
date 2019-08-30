package org.mymvc.framework.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by H.J
 * 2019/8/29
 */
public final class CastUtil {

    public static String castString(Object o){
        return CastUtil.castString(o,"");
    }

    public static String castString(Object o,String defaultValue){
        return o != null ? String.valueOf(o) : defaultValue;
    }

    public static double castDouble(Object o){
        return CastUtil.castDouble(o,0);
    }

    public static double castDouble(Object o,double defaultValue){
        double doubleValue = defaultValue;
        if(o != null){
            String strValue = castString(o);
            if(StringUtils.isEmpty(strValue)){
                try{
                    doubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static long castLong(Object o){
        return CastUtil.castLong(o,0);
    }

    public static long castLong(Object o,long defaultValue){
        long longValue = defaultValue;
        if(o != null){
            String strValue = castString(o);
            if(StringUtils.isEmpty(strValue)){
                try{
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    public static int castInt(Object o){
        return CastUtil.castInt(o,0);
    }

    public static int castInt(Object o,int defaultValue){
        int intValue = defaultValue;
        if(o != null){
            String strValue = castString(o);
            if(StringUtils.isEmpty(strValue)){
                try{
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object o){
        return CastUtil.castBoolean(o,false);
    }

    public static boolean castBoolean(Object o,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(o != null){
            booleanValue = Boolean.parseBoolean(castString(o));
        }
        return booleanValue;
    }
}
