package org.mymvc.framework.bean;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by H.J
 * 2019/8/29
 */
@Data
public class Request {

    private String requestMethod;

    private String requestPath;

    public Request(String requestMethod,String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    @Override
    public int hashCode(){
            return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj){
            return EqualsBuilder.reflectionEquals(this, obj);
    }
}
