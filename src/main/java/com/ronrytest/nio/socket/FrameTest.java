package com.ronrytest.nio.socket;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(8888);

		startClient();

		Socket client = server.accept();

		Framer framer = new SerializableFramer();

		framer.sendAFrame(client.getOutputStream(), "xxxx");
		framer.sendAFrame(client.getOutputStream(), new Bean("ronry", "男", 90));

		// 服务器端先关闭应该没问题的吧??
		server.close();
	}

	private static void startClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Socket toServer = new Socket("127.0.0.1", 8888);

					Framer framer = new SerializableFramer();
					System.out.println(framer.receiveAFrame(
							toServer.getInputStream(), String.class));
					System.out.println(framer.receiveAFrame(
							toServer.getInputStream(), String.class));

					toServer.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	public static class Bean implements Serializable {

		private static final long serialVersionUID = 6015380034427585423L;
		private String name;
		private String sex;
		private int age;

		public Bean(String name, String sex, int age) {
			super();
			this.name = name;
			this.sex = sex;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Bean [name=" + name + ", sex=" + sex + ", age=" + age + "]";
		}

	}

}
