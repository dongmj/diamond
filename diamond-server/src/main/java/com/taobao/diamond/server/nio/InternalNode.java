package com.taobao.diamond.server.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;

public class InternalNode {
	private NIOServer local;
	private List<NIOClient> otherNodes = new LinkedList<>();
	private final Selector clientSelector;
	int currentTerm = 0;
	public static final Map<String, EventMedia<BasicEvent>> cache = new ConcurrentHashMap<>();
	final int localPort;
	
	public InternalNode(int localPort) throws IOException {
		local = new NIOServer();
		this.localPort = localPort;
		this.clientSelector = Selector.open();
	}
	
	public void start() throws Exception {
		local.initServer(localPort);
		initOtherNodes(localPort);
		new Thread(new ClientThread()).start();
	}
	
	private void initOtherNodes(int localPort) throws Exception {
		for(int i = 0; i < 10; i++) {
			int tryPort = 10011 + i;
			if(tryPort == localPort)
				continue;
			NIOClient client = new NIOClient(clientSelector);
			client.initClient("localhost", tryPort);
			otherNodes.add(client);
		}
		findLeader();
	}
	
	private void findLeader() throws Exception {
		BasicEvent event = new BasicEvent();
		event.setTerm(currentTerm);
		event.setType(EventType.FIND_LEADER);
		event.setSequence(UUID.randomUUID().toString());
		Future<BasicEvent> f = pushEvent(event);
		System.out.println(f.get());
	}
	
	private Future<BasicEvent> pushEvent(BasicEvent event) throws IOException {
		EventMedia<BasicEvent> m = new EventMedia<>(event);
		for(NIOClient node : otherNodes) {
			node.push(JSON.toJSONString(event));
		}
		cache.put(event.getSequence(), m);
		return m;
	}
	
	public static void main(String[] args) throws Exception {
		InternalNode node = new InternalNode(10012);
		node.start();
	}
	
	private Charset charset = Charset.forName("UTF-8");

	private class ClientThread implements Runnable {

		@Override
		public void run() {
			try {
				while(true) {
					int readChannels = clientSelector.select();
					if(readChannels == 0)
						continue;
					Set<SelectionKey> selectedKeys = clientSelector.selectedKeys();
					Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
					while(keyIterator.hasNext()) {
						SelectionKey sk = keyIterator.next();
						keyIterator.remove();
						dealWithSelectionKey(sk);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void dealWithSelectionKey(SelectionKey sk) throws IOException {
		if(sk.isReadable()) 
		{
			SocketChannel sc = (SocketChannel) sk.channel();
			ByteBuffer buff = ByteBuffer.allocate(1024);
			String content = "";
			while(sc.read(buff) > 0) {
				buff.flip();
				content += charset.decode(buff);
			}
			System.out.println(content);
			BasicEvent event = JSON.parseObject(content, BasicEvent.class);
			EventMedia<BasicEvent> media = InternalNode.cache.get(event.getSequence());
			media.set(event);
			sk.interestOps(SelectionKey.OP_READ);
		}
	}
}
