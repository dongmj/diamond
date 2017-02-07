package com.taobao.diamond.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	private Selector selector;
	private static String USER_CONTENT_SPILIT = "#@#";
	private Charset charset = Charset.forName("UTF-8");
	ServerSocketChannel serverChannel;
	
	public void initServer(int port) throws IOException {
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		this.selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务器启动...");
		new Thread(new ServerThread()).start();
	}
	
	private class ServerThread implements Runnable {

		@Override
		public void run() {
			try {
			while(true) {
				int readyChannels = selector.select();
				if(readyChannels == 0)
					continue;
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> ite = selectedKeys.iterator();
				while(ite.hasNext()) {
					SelectionKey sk = ite.next();
					ite.remove();
					dealWithSelectionKey(serverChannel, sk);
				}
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void dealWithSelectionKey(ServerSocketChannel server, SelectionKey sk) throws IOException {
		if(sk.isAcceptable()) {
			SocketChannel sc = server.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			sk.interestOps(SelectionKey.OP_ACCEPT);
			System.out.println("Server is listening from client: " + sc.getRemoteAddress());
			sc.write(charset.encode("ok"));
		} else if(sk.isReadable()) {
			SocketChannel sc = (SocketChannel) sk.channel();
			ByteBuffer buff = ByteBuffer.allocate(1024);
			StringBuilder content = new StringBuilder();
			try {
				while(sc.read(buff) > 0) {
					buff.flip();
					content.append(charset.decode(buff));
				}
				System.out.println("Server is listening from client " + sc.getRemoteAddress() + "data rev is : " + content.toString());
				sk.interestOps(SelectionKey.OP_READ);
			} catch(Exception e) {
				sk.cancel();
				if(sk.channel() != null) {
					sk.channel().close();
				}
			}
			if(content.length() > 0) {
				String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);
				if(arrayContent != null && arrayContent.length == 1) {
					// TODO 消息处理
					sc.write(charset.encode("success"));
				} else {
					sc.write(charset.encode("处理消息"));
				}
			}
		}
	}
	
	public void broadCast(Selector selector, SocketChannel except, String content) throws IOException {
		for(SelectionKey key : selector.keys()) {
			Channel targetChannel = key.channel();
			if(targetChannel instanceof SocketChannel && targetChannel != except) {
				SocketChannel dest = (SocketChannel) targetChannel;
				dest.write(charset.encode(content));
			}
		}
	}
}
