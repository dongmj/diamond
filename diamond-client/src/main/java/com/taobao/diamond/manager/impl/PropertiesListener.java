/*
 * (C) 2007-2012 Alibaba Group Holding Limited.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 * Authors:
 *   leiwen <chrisredfield1985@126.com> , boyan <killme2008@gmail.com>
 */
package com.taobao.diamond.manager.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.diamond.manager.ManagerListenerAdapter;


public abstract class PropertiesListener extends ManagerListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(PropertiesListener.class);


    public void receiveConfigInfo(String configInfo) {
        if (StringUtils.isEmpty(configInfo)) {
            log.warn("收到的配置信息为空");
            return;
        }

        Properties properties = new Properties();
        try {
            properties.load(new StringReader(configInfo));
            innerReceive(properties);
        }
        catch (IOException e) {
            log.warn("装载properties失败：" + configInfo, e);
        }

    }


    public abstract void innerReceive(Properties properties);

}
