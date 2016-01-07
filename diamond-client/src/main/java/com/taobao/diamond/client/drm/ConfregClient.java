package com.taobao.diamond.client.drm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import com.taobao.diamond.client.DiamondSubscriber;
import com.taobao.diamond.client.impl.DefaultSubscriberListener;
import com.taobao.diamond.client.impl.DiamondClientFactory;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.utils.DrmClientLogger;

public class ConfregClient {
    protected DiamondSubscriber diamondSubscriber = DiamondClientFactory.getSingletonDiamondSubscriber();
    
    public void registerDataSubscriber(final String dataId, final String group, final DistributedResourceAttribute attribute) {
        diamondSubscriber.addDataId(dataId, group);
        List<ManagerListener> managerListeners = new LinkedList<ManagerListener>();
        managerListeners.add(new ManagerListener() {

            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
//                String orignalCommand = (String) datas.get(0);
            	DrmClientLogger.log_monitor.info("接收到分布式资源[" + dataId + ", " + group + "] 内容为 " + configInfo);
            	// 开始时间
            	long startTime = System.currentTimeMillis();
                try {
                    ResourceAttributeUpdater.updateResource(attribute, configInfo);
                } catch (Exception e) {
                	DrmClientLogger.log_monitor.error("更新分布式资源发生错误", e);
                } finally {
                	DrmClientLogger.log_monitor.info("处理分布式资源[" + dataId + ", " + group + "] 用时 [" + (System.currentTimeMillis()-startTime) + "]ms");
                }
            }
            
        });
        ((DefaultSubscriberListener) diamondSubscriber.getSubscriberListener()).addManagerListeners(dataId,
            group, managerListeners);
        diamondSubscriber.start();
        DrmClientLogger.log_boot.info("注册分布式资源 [" + dataId + ", " + group + "] 成功!");
    }
    
    public void unregisterSubscriber(String dataId, String group) {
        diamondSubscriber.removeDataId(dataId, group);
    }
}
