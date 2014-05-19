/**
 * 
 */
package com.ronrytest.nio.socket;

import java.io.InputStream;

/**
 * <pre>
 * 本示例主要是为了说明InputSream.read(byte[] bs)的行为：
 * 该方法将“尝试”从输入流中制定大小的（由bs长度指定）数据到bs数组中，当有几种情况需要特别注意：
 * 1) 如果读到了-1 (代表流已经结束)，则会立即返回不管已经读取了多少数据
 * 2) 已经读到了部分数据（还没有塞满bs），但是一直没有再能读到更多的数据也没能读到-1,则会先返回当前已经读到的数据;
 * 	  (这种情况一般是因为服务器端的数据被分包了，已经读了部分数据包，但还有数据包没有到达)。
 * 	  因为有这种情况存在，所以对于某些依赖数据长度的协议(或者一定得多少字节之后再处理的解决方案)，需要确保读取的字节数确实已经到了指定的长度
 * 3) 如果没有读到任何数据，同时也没有读到-1，则会一直等待直到至少有1个字节的数据可以读取
 * </pre>
 * 
 * @author ronry
 * 
 */
public class SocketReadTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		OneClientServer server = new OneClientServer();
		startClient();

		server.nextClient();

		server.getOutput().write(12);
	}

	private static void startClient() throws Exception {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					SimpleClient client = new SimpleClient();

					InputStream in = client.getInput();

					byte[] data = new byte[8];
					int size = 0;
					// 只有服务器端在close了socket之后才会读到-1，否则一直循环或者阻塞
					// 虽然一次试着读取8个字节，但是该方法并不保证一定能读取到8个字节，如果暂时没有更多的数据可以读取
					// (不代表消息已经完了，只有读到-1才代表消息完了)，则会立即返回已经读取的数据
					while ((size = in.read(data)) != -1) {
						System.out.println(size);
						for (int i = 0; i < data.length; i++) {
							System.out.println(data[i]);
						}
					}

					System.out.println("end");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

}
