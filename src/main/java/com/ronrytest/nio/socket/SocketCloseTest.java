/**
 * 
 */
package com.ronrytest.nio.socket;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * <pre>
 * 测试socket.close()执行的效果，重点关注：
 * 1) 发送端执行close()之后，已经写入输出流，但是还未发送的数据的接收情况：一定能保证发送到接收端
 * 2) 如果只是由一方调用了close()，则还没有调用close()的一端仍然可以继续write()数据，但其实这个数据是不能被发送到接收放的
 * 	  如果写进去的数据比较小，因为可能写到了缓冲区中，则不会立即报错()。但如果写入的数据较大，需要立即发送一个包了，则会立即报错
 * 
 * 所以说调用close()方法只是关闭了调用该方法的一端的socket的输入/输出流；而对另外一端，则输入输出流都还可用(对于输出流，底层层面已经不能再发送数据)
 * </pre>
 * 
 * @author ronry
 * 
 */
public class SocketCloseTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		OneClientServer server = new OneClientServer();

		startClient();

		server.nextClient();

		// 等待一会(放大问题)，也能读取到原先已经发送的数据
		Thread.sleep(10000);
		InputStream in = server.getInput();

		while (in.read() != -1) {
			System.out.println("read");
		}
		System.out.println("READ end");
		Thread.sleep(3000);
		// 即使客户端已经关闭了，但是服务器端还可以write(只能是小数据，但是即使可以调用write，最终也是发送不出去的，操作系统底层会有错误)
		server.getOutput().write(2);
		// 即使flush了也没问题，说明flush也只是刷新缓冲区而已，而不会让socket立即发送数据包？
		server.getOutput().flush();

		BufferedInputStream fileIn = new BufferedInputStream(
				new FileInputStream(
						"/home/ronry/documents/ihome/authorization.pdf"));

		byte[] buffer = new byte[1024];
		while (fileIn.read(buffer) != -1) {
			// 对于比较大的数据，因为一次性充满了缓冲区，所以需要立即发送，则会立即收到异常
			server.getOutput().write(buffer);
			buffer = new byte[1024 * 100];
		}
	}

	private static void startClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					SimpleClient client = new SimpleClient();
					client.getOutput().write(1);
					client.close();

					System.out.println("client closed");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
