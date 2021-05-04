package com.project.utils;

import org.springframework.beans.BeanUtils;

public class CopyUtils {
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) return null;
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        assert obj != null;
        BeanUtils.copyProperties(source, obj);
        return obj;

    }
}
