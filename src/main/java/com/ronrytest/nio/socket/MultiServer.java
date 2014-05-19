package com.ronrytest.nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer extends Thread {
	private Socket client;

	public MultiServer(Socket c) {
		this.client = c;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			// Mutil User but can't parallel

			while (true) {
				String str = in.readLine();
				System.out.println(str);
				out.println(1);
				// out.flush();
				if (str.equals("end"))
					break;
			}
			client.close();
		} catch (IOException ex) {
		} finally {
		}
	}

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(5678);
		while (true) {
			// transfer location change Single User or Multi User
			MultiServer mc = new MultiServer(server.accept());
			mc.start();
		}
	}
}