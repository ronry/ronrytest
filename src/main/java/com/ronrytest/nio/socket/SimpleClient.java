package com.ronrytest.nio.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {

	private Socket socket;

	public SimpleClient() throws Exception {
		socket = new Socket("127.0.0.1", 8888);
	}

	public InputStream getInput() throws Exception {
		return socket.getInputStream();
	}

	public OutputStream getOutput() throws Exception {
		return socket.getOutputStream();
	}

	public void close() throws Exception {
		socket.close();
	}

}
