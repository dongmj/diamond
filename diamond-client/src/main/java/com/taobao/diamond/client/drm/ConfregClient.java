package com.taobao.diamond.client.drm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import com.taobao.diamond.client.DiamondSubscriber;
import com.taobao.diamond.client.impl.DefaultSubscriberListener;
import com.taobao.diamond.client.impl.DiamondClientFactory;
import com.taobao.diamond.manager.ManagerListener;

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
                try {
                    ResourceAttributeUpdater.updateResource(attribute, configInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });
        ((DefaultSubscriberListener) diamondSubscriber.getSubscriberListener()).addManagerListeners(dataId,
            group, managerListeners);
        diamondSubscriber.start();
    }
    
    public void unregisterSubscriber(String dataId, String group) {
        diamondSubscriber.removeDataId(dataId, group);
    }
}
