package com.taobao.diamond.client.drm;

public class DefaultDistributedResourceManagerImpl implements DistributedResourceManager {

    private DistributedResourceBuilder distributedResourceBuilder = new DistributedResourceBuilder();
    private ConfregAssembly confregAssembly = new ConfregAssembly();
    private LocalResourcePool localResourcePool = new LocalResourcePool();
    
    @Override
    public DistributedResource register(Object resourceObject, String app) {
        DistributedResource distributedResource = distributedResourceBuilder.build(resourceObject, app);
        return realRegister(distributedResource);
    }

    private DistributedResource realRegister(DistributedResource distributedResource) {
        confregAssembly.assembly(distributedResource);
        localResourcePool.registerResource(distributedResource.getId(), distributedResource);
        return distributedResource;
    }
    
    public void unregister(String id) {
        DistributedResource distributedResource = localResourcePool.removeResourceById(id);
        confregAssembly.unregister(distributedResource);
    }
}
