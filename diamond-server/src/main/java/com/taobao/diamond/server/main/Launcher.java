package com.taobao.diamond.server.main;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
	public static final int PORT = 8080;
	public static final String CONTEXT = "/";
	
	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	
	public static Server createServerInSource(int port, String contextPath) {
		Server server = new Server();
		server.setStopAtShutdown(true);
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });
		WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
		webContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webContext.setResourceBase(DEFAULT_WEBAPP_PATH);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webContext);
		return server;
	}
	
	public void startJetty(int port, String context) {
		final Server server = Launcher.createServerInSource(PORT, CONTEXT);
		try {
			server.stop();
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Launcher().startJetty(8080, "");
	}
}
