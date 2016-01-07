package com.taobao.diamond.client.drm;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class ObjectNameBuilder {
    public static ObjectName build(DistributedResource distributedResource) throws MalformedObjectNameException {
        if(distributedResource.getObjectName() != null)
            return distributedResource.getObjectName();
        ObjectName objectName = new ObjectName(buildString(distributedResource));
        distributedResource.setObjectName(objectName);
        return objectName;
    }
    
    public static String buildString(DistributedResource distributedResource) {
        StringBuilder sb = new StringBuilder(distributedResource.getDomain());
        sb.append(":name=");
        sb.append(distributedResource.getId());
        sb.append(",version=");
        sb.append(distributedResource.getVersion());
        
        return sb.toString();
    }
    
    public static ObjectName build(DistributedResourceAttribute distributedResourceAttribute) throws MalformedObjectNameException {
        if(distributedResourceAttribute.getObjectName() != null) {
            return distributedResourceAttribute.getObjectName();
        }
        ObjectName objectName = new ObjectName(buildString(distributedResourceAttribute));
        return objectName;
    }
    
    public static String buildString(DistributedResourceAttribute distributedResourceAttribute) {
        DistributedResource distributedResource = distributedResourceAttribute.getResource();
        StringBuilder sb = new StringBuilder(distributedResource.getDomain());
        sb.append(":name=");
        sb.append(distributedResource.getId());
        sb.append(".");
        sb.append(distributedResourceAttribute.getAttrName());
        sb.append(",version=");
        sb.append(distributedResource.getVersion());
        
        return sb.toString();
    }
    
    public static String buildDataId(DistributedResourceAttribute distributedResourceAttribute, boolean groupManageable) throws MalformedObjectNameException {
        ObjectName objectName;
        objectName = build(distributedResourceAttribute);
        
        return objectName.toString();
    }
    
    public static String buildDataId(DistributedResource distributedResource) throws MalformedObjectNameException {
        ObjectName objectName = build(distributedResource);
        return objectName.toString();
    }
}
