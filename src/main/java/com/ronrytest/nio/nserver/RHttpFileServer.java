/**
 * 
 */
package com.ronrytest.nio.nserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

/**
 * @author ronry
 * 
 */
public class RHttpFileServer {

	private File rootPath;
	private int port = 8888;

	public RHttpFileServer() {
		rootPath = new File("/home/ronry");
	}

	public void start() {
		try {
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket socket = server.accept();

				new Thread(new FileRequestHandler(socket, rootPath)).start();
			}
		} catch (IOException e) {

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RHttpFileServer server = new RHttpFileServer();
		server.start();
	}

	public static class FileRequestHandler implements Runnable {

		private Socket client;
		private File rootPath;

		public FileRequestHandler(Socket client, File rootPath) {
			this.client = client;
			this.rootPath = rootPath;
		}

		@Override
		public void run() {
			try {
				InputStream in = client.getInputStream();
				OutputStream out = client.getOutputStream();

				LineNumberReader reader = new LineNumberReader(
						new InputStreamReader(new BufferedInputStream(in)));

				String line = reader.readLine();
				String file = URLDecoder.decode(line).split(" ")[1];

				File files = new File(rootPath.getAbsolutePath() + file);

				System.out.println("request " + files.getAbsolutePath());

				if (files.exists()) {
					FileInputStream fileIn = new FileInputStream(files);

					byte[] b = new byte[1024];
					int leng = 0;
					while ((leng = fileIn.read(b)) != -1) {
						out.write(b, 0, leng);
					}

				} else {
					PrintWriter write = new PrintWriter(out);
					write.write("file not exist");
				}
				out.flush();
				client.close();
			} catch (IOException e) {

			}
		}
	}

}
