package org.mymvc.framework.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Created by H.J
 * 2019/8/29
 */
public final class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }
}
