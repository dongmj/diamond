package com.taobao.diamond.client.drm;

import java.util.Map;

import javax.management.MalformedObjectNameException;

import static com.taobao.diamond.client.drm.DistributedResourceConstant.DEFAULT_CONFREG_GROUP;

public class ConfregAssembly implements DistributedResourceAssembly {
    ConfregClient confregClient = new ConfregClient();

    @Override
    public Object assembly(DistributedResource distributedResource) {
        Map<String, DistributedResourceAttribute> attributes = distributedResource.getAttributes();
        for(DistributedResourceAttribute attribute : attributes.values()) {
            try {
                confregClient.registerDataSubscriber(ConfregDataBuilder.buildDataId(attribute), distributedResource.getApp(), attribute);
            } catch (MalformedObjectNameException e) {
                throw new RuntimeException("资源[" + ObjectNameBuilder.buildString(attribute) + "] 更新的订阅器出现异常", e);
            }
        }
        
        return null;
    }

    @Override
    public void unregister(DistributedResource distributedResource) {
        Map<String, DistributedResourceAttribute> attributes = distributedResource.getAttributes();
        for(DistributedResourceAttribute attribute : attributes.values()) {
            try {
                confregClient.unregisterSubscriber(ConfregDataBuilder.buildDataId(attribute), DEFAULT_CONFREG_GROUP);
            } catch (MalformedObjectNameException e) {
                throw new RuntimeException("资源[" + ObjectNameBuilder.buildString(attribute) + "] 解除的订阅器出现异常", e);
            }
        }
    }
    
}
