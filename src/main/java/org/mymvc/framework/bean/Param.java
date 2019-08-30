package org.mymvc.framework.bean;

import org.mymvc.framework.util.CastUtil;

import java.util.Map;

/**
 * Created by H.J
 * 2019/8/30
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }
}
