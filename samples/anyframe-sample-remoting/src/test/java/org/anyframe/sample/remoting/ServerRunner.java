/*
 * Copyright 2002-2008 the original author or authors.
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
package org.anyframe.sample.remoting;

import org.anyframe.sample.remoting.server.Server;

/**
 * TestCase Name : ServerRunner <br>
 * <br>
 * 
 * [tip.] Server를 stop 시킨 후 다음 테스트 수행을 위해서 start 시키기 까지는 약간의 시간 term이 필요하므로
 * Thread.sleep(5 * 1000); 구문을 추가하였다.
 * 
 * @author SooYeon Park
 */
public class ServerRunner {

	private Server server = null;

	protected void setServer(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}

	// This method is called before test method is
	// executed.
	protected void onSetUp() throws Exception {
		server.start();
	}

	// This method is called after test method is
	// executed.
	protected void onTearDown() throws Exception {
		server.stop();
		Thread.sleep(5 * 1000);
	}
}
