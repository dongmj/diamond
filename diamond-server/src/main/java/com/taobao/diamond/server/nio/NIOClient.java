package com.taobao.diamond.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NIOClient {
	private Selector selector;
	private Charset charset = Charset.forName("UTF-8");
	private SocketChannel sc = null;
	private String name = "";
	
	public NIOClient(Selector selector) {
		this.selector = selector;
	}
	
	public void initClient(String ip, int port) throws IOException {
		name = ip + ":" + port;
		sc = SocketChannel.open();
		sc.configureBlocking(false);
		sc.connect(new InetSocketAddress(ip, port));
		sc.register(selector, SelectionKey.OP_READ);
	}
	
	private static final String USER_CONTENT_SPLIT = "#@#";
	
	public void push(String msg) throws IOException {
		sc.write(charset.encode(name + USER_CONTENT_SPLIT +msg));
	}
}
