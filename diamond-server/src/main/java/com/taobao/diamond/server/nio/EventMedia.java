package com.taobao.diamond.server.nio;

import java.util.concurrent.FutureTask;

public class EventMedia<V> extends FutureTask<V> {
	public EventMedia(V v) {
		super(new Runnable() {

			@Override
			public void run() {
			}
			
		}, v);
	}
	/* (non-Javadoc)
	 * @see java.util.concurrent.FutureTask#set(java.lang.Object)
	 */
	@Override
	public void set(V v) {
		super.set(v);
	}
}
