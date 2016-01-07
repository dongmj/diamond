package com.taobao.diamond.client.drm;

public interface DistributedResourceManager {
    DistributedResource register(Object resourceObject, String app);
}
