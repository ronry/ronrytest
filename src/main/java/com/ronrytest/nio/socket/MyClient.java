package com.ronrytest.nio.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyClient {
	static Socket server;

	public static void main(String[] args) throws Exception {
		server = new Socket();
		// server.setTcpNoDelay(true);
		// server.setSendBufferSize(10);
		server.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5678));
		// PrintWriter out = new PrintWriter(server.getOutputStream());
		OutputStream out = server.getOutputStream();
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

		BufferedInputStream fileIn = new BufferedInputStream(
				new FileInputStream("/home/ronry/test.log"));

		byte[] buffer = new byte[1024 * 100];
		while (fileIn.read(buffer) != -1) {
			out.write(buffer);
			buffer = new byte[1024 * 100];
		}

		System.out.println("end");

		// while (true) {
		// String str = wt.readLine();
		// out.println(str);
		// // out.flush();
		// if (str.equals("end")) {
		// break;
		// }
		// // System.out.println(in.readLine());
		// }
		server.close();
	}
}