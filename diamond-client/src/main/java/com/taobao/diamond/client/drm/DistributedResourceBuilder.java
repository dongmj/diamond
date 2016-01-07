package com.taobao.diamond.client.drm;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

public class DistributedResourceBuilder {
    
    /**
     * 根据分布式资源配置信息，构建分布式资源对象
     * 
     * @param resource
     * @param app
     * @return
     */
    public DistributedResource build(Object resource, String app) {
        validateResourceComponent(resource);
        DistributedResource dResource;
        try {        
            dResource = getResourceInfo(resource, app);
            dResource.setAttributes(getAttributeInfo(dResource, resource));
            setFuntionMethods(dResource, resource);
            dResource.setImplementation(this);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        
        return dResource;
    }
    
    private void validateResourceComponent(Object realTarget) {
        // TODO
    }
    
    protected DistributedResource getResourceInfo(Object resource, String app) {
        Class<?> clazz = resource.getClass();
        DResource ann = clazz.getAnnotation(DResource.class);
        if(ann == null)
            throw new RuntimeException("资源类[" + clazz.getName() + "] 必须设置DResource注解");
        
        String id = ann.id();
        String description = ann.description();
        
        if(StringUtils.isBlank(id))
            throw new RuntimeException("资源类[" + clazz.getName() + "] 必须设置id属性");
        
        String domain = "DRM." + app;
        String version = "2.0";
        
        DistributedResource distributedResource = new DistributedResource(id, domain, version, app);
        distributedResource.setDesc(description);
        
        return distributedResource;
    }
    
    protected Map<String, DistributedResourceAttribute> getAttributeInfo(DistributedResource distributedResource, Object resource) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> clazz = resource.getClass();
        PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(clazz);
        Map<String, DistributedResourceAttribute> attributes = new HashMap<String, DistributedResourceAttribute>();
        
        for(int i = 0; i < props.length; i++) {
            PropertyDescriptor propertyDescriptor = props[i];
            String attrName = propertyDescriptor.getName();
            DAttribute ann = getAnnotation(clazz, attrName);
            if(ann == null)
                continue;
            Method getter = propertyDescriptor.getReadMethod();
            Method setter = propertyDescriptor.getWriteMethod();
            if(setter == null || getter == null)
                throw new RuntimeException("分布式资源属性" + propertyDescriptor.getName() + " 缺少set或get方法");
            DistributedResourceAttribute attribute = new DistributedResourceAttribute(attrName);
            attribute.setResource(distributedResource);
            attributes.put(attribute.getAttrName(), attribute);
            attribute.setDesc(ann.description());
            attribute.setReaderMethod(getter);
            attribute.setWriterMethod(setter);
            
            // 首先保证getter方法正常
            getter.invoke(resource);
        }
        
        return attributes;
    }
    
    private DAttribute getAnnotation(Class<?> targetClass, String fieldName) throws NoSuchFieldException, SecurityException {
        Field f;
        f = targetClass.getDeclaredField(fieldName);
        return f.getAnnotation(DAttribute.class);
    }
    
    protected void setFuntionMethods(DistributedResource distributedResource, Object resource) {
        Method[] methods = resource.getClass().getDeclaredMethods();
        for(Method method : methods) {
            BeforeUpdate beforeAnn = method.getAnnotation(BeforeUpdate.class);
            if(beforeAnn != null) {
                validateCallbackMethodParams(method);
                distributedResource.setBeforeUpdateMethod(method);
            }
            AfterUpdate afterAnn = method.getAnnotation(AfterUpdate.class);
            if(afterAnn != null) {
                validateCallbackMethodParams(method);
                distributedResource.setAfterUpdateMethod(method);
            }
        }
    }
    
    private void validateCallbackMethodParams(Method callbackMethod) {
        Class<?>[] paramClazzs = callbackMethod.getParameterTypes();
        if(!(paramClazzs.length == 2 && paramClazzs[0].equals(String.class) && paramClazzs[1].equals(Object.class))) {
            throw new RuntimeException("回调方法[" + callbackMethod.getName() + "]参数类型必须为(String key, Object value)");
        }
    }
}
