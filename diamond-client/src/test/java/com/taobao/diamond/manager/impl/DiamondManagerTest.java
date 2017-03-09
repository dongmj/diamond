package com.taobao.diamond.manager.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;

public class DiamondManagerTest {
	@Test
	public void testListen() {
		DiamondManager manager = new DefaultDiamondManager(
				"MyGroup", "MyDataId", 
				new ManagerListener() {

			@Override
			public Executor getExecutor() {
				return Executors.newSingleThreadExecutor();
			}

			@Override
			public void receiveConfigInfo(String configInfo) {
				System.out.println("收到消息啦!内容是：" + configInfo);
			}

		});
		manager.start();
	}
}
