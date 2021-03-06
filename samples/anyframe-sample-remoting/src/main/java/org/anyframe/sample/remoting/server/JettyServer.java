/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.anyframe.sample.remoting.server;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * JettyServer makes server implementation simplified when you use JettyServer
 * explicitly using a Jetty APIs. You can set server information and start/stop
 * server consistently.
 * 
 * @author SooYeon Park
 */
public class JettyServer implements Server {

	private org.mortbay.jetty.Server server = null;

	public JettyServer() {
		server = new org.mortbay.jetty.Server();
	}

	public void setServerInfo(ServerInfo serverInfo) {
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(serverInfo.getPort());
		server.setConnectors(new Connector[] { connector });

		WebAppContext webappcontext = new WebAppContext();
		webappcontext.setContextPath("/");

		webappcontext.setWar(serverInfo.getWarpath());

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { webappcontext,
				new DefaultHandler() });

		server.setHandler(handlers);
	}

	public void start() throws Exception {
		server.start();
		Server.LOGGER.info("Server ready...");
	}

	public void stop() throws Exception {
		server.stop();
	}

}
