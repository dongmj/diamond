package com.taobao.diamond.client.drm;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class DistributedResource {
    private String id;
    private ObjectName objectName;
    private String domain;
    private String app;
    private String version;
    private Map<String, String> extensionProperties;
    private String desc;
    private Map<String, DistributedResourceAttribute> attributes;
    private Method beforeUpdateMethod;
    private Method afterUpdateMethod;
    private Object implementation;
    
    public DistributedResource(String id, String domain, String version, String app) {
        this.id = id;
        this.app = app;
        this.domain = domain;
        this.version = version;
        
        extensionProperties = new ConcurrentHashMap<String, String>(2);
        attributes = new ConcurrentHashMap<String, DistributedResourceAttribute>(8);
    }
    
    public void addAttribute(DistributedResourceAttribute attribute) {
        if(attribute == null)
            return;
        if(attributes.containsKey(attribute.getAttrName()))
            attributes.put(attribute.getAttrName(), attribute);
        else
            throw new RuntimeException("分布工资源添加属性的时已经存在注册的属性[" + attribute.getAttrName() + "]");
    }
    
    public void addExtensionProperties(String key, String value) {
        this.extensionProperties.put(key, value);
    }
    
    public ObjectName buildObjectName() throws MalformedObjectNameException {
        return ObjectNameBuilder.build(this);
    }

    public boolean includeReadAttribute(String attributeName) {
        if(attributes.get(attributeName) != null)
            return attributes.get(attributeName).isRead();
        
        return false;
    }
    
    public boolean includeWriteAttribute(String attrName) {
        if(attributes.get(attrName) != null)
            return attributes.get(attrName).isWrite();
        
        return false;
    }
    
    public DistributedResourceAttribute getAttribute(String attrName) {
        return attributes.get(attrName);
    }
    
    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>objectName</tt>.
     * 
     * @return property value of objectName
     */
    public ObjectName getObjectName() {
        return objectName;
    }

    /**
     * Setter method for property <tt>objectName</tt>.
     * 
     * @param objectName value to be assigned to property objectName
     */
    public void setObjectName(ObjectName objectName) {
        this.objectName = objectName;
    }

    /**
     * Getter method for property <tt>domain</tt>.
     * 
     * @return property value of domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Setter method for property <tt>domain</tt>.
     * 
     * @param domain value to be assigned to property domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Getter method for property <tt>app</tt>.
     * 
     * @return property value of app
     */
    public String getApp() {
        return app;
    }

    /**
     * Setter method for property <tt>app</tt>.
     * 
     * @param app value to be assigned to property app
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * Getter method for property <tt>version</tt>.
     * 
     * @return property value of version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter method for property <tt>version</tt>.
     * 
     * @param version value to be assigned to property version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Getter method for property <tt>extensionProperties</tt>.
     * 
     * @return property value of extensionProperties
     */
    public Map<String, String> getExtensionProperties() {
        return extensionProperties;
    }

    /**
     * Setter method for property <tt>extensionProperties</tt>.
     * 
     * @param extensionProperties value to be assigned to property extensionProperties
     */
    public void setExtensionProperties(Map<String, String> extensionProperties) {
        this.extensionProperties = extensionProperties;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     * 
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     * 
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>attributes</tt>.
     * 
     * @return property value of attributes
     */
    public Map<String, DistributedResourceAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Setter method for property <tt>attributes</tt>.
     * 
     * @param attributes value to be assigned to property attributes
     */
    public void setAttributes(Map<String, DistributedResourceAttribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Getter method for property <tt>beforeUpdateMethod</tt>.
     * 
     * @return property value of beforeUpdateMethod
     */
    public Method getBeforeUpdateMethod() {
        return beforeUpdateMethod;
    }

    /**
     * Setter method for property <tt>beforeUpdateMethod</tt>.
     * 
     * @param beforeUpdateMethod value to be assigned to property beforeUpdateMethod
     */
    public void setBeforeUpdateMethod(Method beforeUpdateMethod) {
        this.beforeUpdateMethod = beforeUpdateMethod;
    }

    /**
     * Getter method for property <tt>afterUpdateMethod</tt>.
     * 
     * @return property value of afterUpdateMethod
     */
    public Method getAfterUpdateMethod() {
        return afterUpdateMethod;
    }

    /**
     * Setter method for property <tt>afterUpdateMethod</tt>.
     * 
     * @param afterUpdateMethod value to be assigned to property afterUpdateMethod
     */
    public void setAfterUpdateMethod(Method afterUpdateMethod) {
        this.afterUpdateMethod = afterUpdateMethod;
    }

    /**
     * Getter method for property <tt>implementation</tt>.
     * 
     * @return property value of implementation
     */
    public Object getImplementation() {
        return implementation;
    }

    /**
     * Setter method for property <tt>implementation</tt>.
     * 
     * @param implementation value to be assigned to property implementation
     */
    public void setImplementation(Object implementation) {
        this.implementation = implementation;
    }
    
    @Override
    public String toString() {
        return ObjectNameBuilder.buildString(this);
    }
}
