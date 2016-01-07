package com.taobao.diamond.client.drm;

import org.junit.Test;

public class DistributedResourceBuilderTest {
	private DistributedResourceBuilder distributedResourceBuilder = new DistributedResourceBuilder();
	
	@Test
	public void test() {
		DRMConfigure configure = new DRMConfigure();
		DistributedResource dResource = distributedResourceBuilder.build(configure, "diamond");
		System.out.println(dResource);
	}
}
