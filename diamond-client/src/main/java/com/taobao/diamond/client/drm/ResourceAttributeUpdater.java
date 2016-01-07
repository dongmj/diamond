package com.taobao.diamond.client.drm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.taobao.diamond.utils.StringConverter;

public class ResourceAttributeUpdater {
    public static void updateResource(DistributedResourceAttribute attribute, String value) throws Exception {
        Method setter = attribute.getWriterMethod();
        if(setter == null)
            throw new RuntimeException("属性" + attribute.getAttrName() + "不可写，请提供set方法");
        Object target = attribute.getResource().getImplementation();
        // set方法类型
        Class<?> paramType = setter.getParameterTypes()[0];
        // 将字符串的属性值转换成真正的类型
        Object param = StringConverter.convertObjectFromString(paramType, value);
        // 提供给回调方法的参数，第一个是属性名，第二个是更新值
        Object[] callbackParams = { attribute.getAttrName(), param };
        
        Method beforeUpdate = attribute.getResource().getBeforeUpdateMethod();
        if(beforeUpdate != null) {
            try {
                beforeUpdate.invoke(target, callbackParams);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        setter.invoke(target, param);
        Method afterUpdate = attribute.getResource().getAfterUpdateMethod();
        if(afterUpdate != null) {
            try {
                afterUpdate.invoke(target, callbackParams);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
