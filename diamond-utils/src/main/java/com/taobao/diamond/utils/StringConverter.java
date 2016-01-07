package com.taobao.diamond.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class StringConverter {
    public static Object convertObjectFromString(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        } else if (type == Float.class || type == float.class) {
            return Float.valueOf(value);
        } else if(type == Double.class || type == double.class) {
            return Double.valueOf(value);
        } else if(type == Byte.class || type == byte.class) {
            return Byte.valueOf(value);
        } else if(type == Short.class || type == short.class) {
            return Short.valueOf(value);
        } else if(type == Integer.class || type == int.class) {
            return Integer.valueOf(value);
        } else if(type == Long.class || type == long.class) {
            return Long.valueOf(value);
        } else if(type == Character.class || type == char.class) {
            return Character.valueOf(value.toCharArray()[0]);
        } else if(type == Boolean.class || type == boolean.class) {
            return Boolean.valueOf(value);
        } else if(type == List.class) {
            return parseJson2ListMap(value);
        } else if(type.isArray()) {
            Class<?> componentType = type.getComponentType();
            return JSON.parseArray(value);
        } else {
            return JSON.parseObject(value);
        }
    }
    
    public static List<HashMap<Object, Object>> parseJson2ListMap(String s) {
        List<HashMap<Object, Object>> result = new ArrayList<HashMap<Object, Object>>();
        s = s.substring(1,  s.length() - 1);
        while(s.contains("{")) {
            HashMap<Object, Object> tmpMap = new HashMap<Object, Object>();
            String mapStr = s.substring(s.indexOf("{") + 1, s.indexOf("}"));
            String[] entryStr = mapStr.split(",");
            for(String entry : entryStr) {
                entry = entry.trim();
                String[] keyAndValue = entry.split("=");
                if(keyAndValue.length == 1) {
                    tmpMap.put(keyAndValue[0], "");
                } else if(keyAndValue.length == 2) {
                    tmpMap.put(keyAndValue[0], keyAndValue[1]);
                }
                result.add(tmpMap);
                s = s.substring(s.indexOf("}") + 1);
            }
        }
        return result;
    }
}
