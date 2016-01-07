package com.taobao.diamond.client.drm;

/**
 * 配置参数管理
 * 
 * @author dongmj
 * @version $Id: DrmClient.java, v 0.1 2015年12月24日 上午10:31:34 dongmj Exp $
 */
public class DrmClient {
    protected static DistributedResourceManager _manager;
    
    public static synchronized DistributedResourceManager instance() {
        if(_manager == null)
            _manager = new DefaultDistributedResourceManagerImpl();
        
        return _manager;
    }
    
    public static synchronized void instance(DistributedResourceManager manager) {
        _manager = manager;
    }
}
