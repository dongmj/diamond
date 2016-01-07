package com.taobao.diamond.client.drm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.taobao.diamond.utils.StringConverter;

public class ResourceAttributeUpdater {
    public static void updateResource(DistributedResourceAttribute attribute, String value) throws Exception {
        Method setter = attribute.getWriterMethod();
        if(setter == null)
            throw new RuntimeException("����" + attribute.getAttrName() + "����д�����ṩset����");
        Object target = attribute.getResource().getImplementation();
        // set��������
        Class<?> paramType = setter.getParameterTypes()[0];
        // ���ַ���������ֵת��������������
        Object param = StringConverter.convertObjectFromString(paramType, value);
        // �ṩ���ص������Ĳ�������һ�������������ڶ����Ǹ���ֵ
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
