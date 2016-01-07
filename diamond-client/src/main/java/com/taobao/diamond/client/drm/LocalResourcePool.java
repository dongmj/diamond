package com.taobao.diamond.client.drm;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalResourcePool {
    Map<String, DistributedResource> resources = new ConcurrentHashMap<String, DistributedResource>();
    
    public void registerResource(String id, DistributedResource resource) {
        resources.put(id, resource);
    }
    
    public Collection<DistributedResource> findAllResources() {
        return resources.values();
    }
    
    public DistributedResource findResourceById(String id) {
        return resources.get(id);
    }
    
    public DistributedResource removeResourceById(String id) {
        return resources.remove(id);
    }
}
