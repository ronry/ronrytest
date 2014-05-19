package com.ronrytest.nio.socket;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 * 测试大的数据，在TCP传输时，发送端发送数据的情况，和接收方接收消息的情况；按一下方式控制模拟(通过debug断点)
 * 1) 发送方启动到accept();(会阻塞，知道有客户端来连接)
 * 2) 接受方启动到read();(这时因为发送方还没有真正写数据，所以接收端read的时候会阻塞)
 * 3) 发送方write()一次，保证write()的数据足够大到发送一个tcp报文;(这时候因为发送方已经有数据发送了，所以接收端的read()将可以被唤醒)
 * 4) 接收方不动，发送端继续write()数据，执行几次;(因为接收端一直不读取数据，发送端接收不到接收方返回的确认信息，所以发送窗口被用完，发送方将会被阻塞，不能再继续发送)
 * 5) 接受方接受几次数据；(因为接收了部分数据，所以接收端发送确认消息，发送端可以继续发送数据，被激活；而对于接收方，虽然接收到了部分数据报文，但整个数据并还没有完全发送完，所以接收端读取到
 * 的不会是-1；当接收几次之后，没有数据了，则接收端会被再次阻塞)
 * 6) 不断重复上诉的过程，直到所有的数据都发送完成；为了确保所有的数据都会从本地的缓冲中发送出去，则需要flush()一下；
 * 7) 最后，发送方执行close()操作，这将往输出流中写入一个-1;
 * 8) 再发送方close()之后，接收方再继续执行read()操作的话，就可以读取到这个-1,代表所有数据传送完了。
 * 9) 最后执行tcp close的3次握手过程，并关闭socket。
 * </pre>
 * 
 * @author ronry
 * 
 */
public class BigDataTransetTest {

	public static void startServer() throws Exception {
		ServerSocket server = new ServerSocket(8888);
		Socket socket = server.accept();
		OutputStream out = socket.getOutputStream();

		BufferedInputStream fileIn = new BufferedInputStream(
				new FileInputStream(
						"/home/ronry/documents/ihome/authorization.pdf"));

		byte[] buffer = new byte[1024 * 100];
		while (fileIn.read(buffer) != -1) {
			out.write(buffer);
			buffer = new byte[1024 * 100];
		}

		// 只会往输出流中写入任何多余的数据，只会将现有的数据从缓冲中发送出去
		out.flush();

		out.write(1000);
		out.flush();

		// 会往输出流中写入一个-1，并开启关闭流程
		socket.close();
	}

	public static void startClient() throws Exception {
		Socket socket = new Socket("127.0.0.1", 8888);
		InputStream in = socket.getInputStream();

		byte[] data = new byte[1024 * 100];
		int read = 0;
		int count = 0;
		while ((read = in.read(data)) != -1) {
			count++;
			System.out.println(read);
		}

		System.out.println(read);
		System.out.println(count);

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					startServer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}) {

		}.start();

		startClient();
	}

}
