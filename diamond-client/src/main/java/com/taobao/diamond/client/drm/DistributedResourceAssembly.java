package com.taobao.diamond.client.drm;

public interface DistributedResourceAssembly {
    Object assembly(DistributedResource distributedResource);
    void unregister(DistributedResource distributedResource);
}
