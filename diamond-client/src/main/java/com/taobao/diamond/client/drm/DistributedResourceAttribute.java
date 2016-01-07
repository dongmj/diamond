package com.taobao.diamond.client.drm;

import java.lang.reflect.Method;

import javax.management.ObjectName;

public class DistributedResourceAttribute {
    private String id;
    private ObjectName objectName;
    private DistributedResource resource;
    private String attrName;
    private Method readerMethod;
    private Method writerMethod;
    private String readDesc;
    private String writeDesc;
    private String desc;
    
    public DistributedResourceAttribute(String attrName) {
        this.attrName = attrName;
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
     * Getter method for property <tt>resource</tt>.
     * 
     * @return property value of resource
     */
    public DistributedResource getResource() {
        return resource;
    }

    /**
     * Setter method for property <tt>resource</tt>.
     * 
     * @param resource value to be assigned to property resource
     */
    public void setResource(DistributedResource resource) {
        this.resource = resource;
    }

    /**
     * Getter method for property <tt>attrName</tt>.
     * 
     * @return property value of attrName
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * Setter method for property <tt>attrName</tt>.
     * 
     * @param attrName value to be assigned to property attrName
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * Getter method for property <tt>readerMethod</tt>.
     * 
     * @return property value of readerMethod
     */
    public Method getReaderMethod() {
        return readerMethod;
    }

    /**
     * Setter method for property <tt>readerMethod</tt>.
     * 
     * @param readerMethod value to be assigned to property readerMethod
     */
    public void setReaderMethod(Method readerMethod) {
        this.readerMethod = readerMethod;
    }

    /**
     * Getter method for property <tt>writerMethod</tt>.
     * 
     * @return property value of writerMethod
     */
    public Method getWriterMethod() {
        return writerMethod;
    }

    /**
     * Setter method for property <tt>writerMethod</tt>.
     * 
     * @param writerMethod value to be assigned to property writerMethod
     */
    public void setWriterMethod(Method writerMethod) {
        this.writerMethod = writerMethod;
    }

    /**
     * Getter method for property <tt>readDesc</tt>.
     * 
     * @return property value of readDesc
     */
    public String getReadDesc() {
        return readDesc;
    }

    /**
     * Setter method for property <tt>readDesc</tt>.
     * 
     * @param readDesc value to be assigned to property readDesc
     */
    public void setReadDesc(String readDesc) {
        this.readDesc = readDesc;
    }

    /**
     * Getter method for property <tt>writeDesc</tt>.
     * 
     * @return property value of writeDesc
     */
    public String getWriteDesc() {
        return writeDesc;
    }

    /**
     * Setter method for property <tt>writeDesc</tt>.
     * 
     * @param writeDesc value to be assigned to property writeDesc
     */
    public void setWriteDesc(String writeDesc) {
        this.writeDesc = writeDesc;
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

    public boolean isWrite() {
        return this.writerMethod != null;
    }
    
    public boolean isRead() {
        return this.readerMethod != null;
    }

}
