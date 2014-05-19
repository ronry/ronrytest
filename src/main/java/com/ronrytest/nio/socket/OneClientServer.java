package com.ronrytest.nio.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OneClientServer {

	private ServerSocket server;
	private Socket socket;

	public OneClientServer() throws Exception {
		server = new ServerSocket(8888);
	}

	public void nextClient() throws Exception {
		socket = server.accept();
	}

	public InputStream getInput() throws Exception {
		return socket.getInputStream();
	}

	public OutputStream getOutput() throws Exception {
		return socket.getOutputStream();
	}

	public void stopSocket() throws Exception {
		socket.close();
	}

	public void stop() throws Exception {
		server.close();
	}

}
