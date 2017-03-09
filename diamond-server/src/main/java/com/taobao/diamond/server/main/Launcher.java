package com.taobao.diamond.server.main;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
	public static final int PORT = 10510;
	public static final String CONTEXT = "/";
	
	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	
	public static Server createDevServer(int port, String contextPath) {
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
	
	public static Server createJettyServer(int port, String contextPath) {
		Server server = new Server(port);
		server.setStopAtShutdown(true);
		ProtectionDomain protectionDomain = Launcher.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();
		String warFile = location.toExternalForm();
		WebAppContext context = new WebAppContext(warFile, contextPath);
		context.setServer(server);
		String currentDir = new File(location.getPath()).getParent();
		File workDir = new File(currentDir, "work");
		context.setTempDirectory(workDir);
		server.setHandler(context);
		return server;
	}
	
	public void startJetty(int port, String context) {
		final Server server = Launcher.createDevServer(port, context);
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
		new Launcher().startJetty(PORT, CONTEXT);
	}
}
