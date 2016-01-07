package com.taobao.diamond.client.drm;

/**
 * ���ò�������
 * 
 * @author dongmj
 * @version $Id: DrmClient.java, v 0.1 2015��12��24�� ����10:31:34 dongmj Exp $
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
