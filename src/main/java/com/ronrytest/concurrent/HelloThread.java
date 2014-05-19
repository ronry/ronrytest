package com.ronrytest.concurrent;

public class HelloThread {
	
	static class HelloTest implements Runnable{

		@Override
		public void run() {
			System.out.println("hello thread");
		}
		
	}

	public static void main(String[] args) {
		new Thread(new HelloTest()).start();
	}

}
