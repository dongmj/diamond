package com.taobao.diamond.client.drm;

@DResource(id = "com.taobao.diamond.client.drm.DRMConfigure")
public class DRMConfigure {
	@DAttribute(name = "drmName")
	private String name;
	@DAttribute(name = "drmAttribute")
	private String attribute;
	
	public void init() {
		DrmClient.instance().register(this, "diamond");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@BeforeUpdate
	public void before(String key, Object value) {
		
	}
	
	@AfterUpdate
	public void after(String key, Object value) {
		
	}
}
