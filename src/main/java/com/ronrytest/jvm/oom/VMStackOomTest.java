package com.ronrytest.jvm.oom;

/**
 * <pre>
 * run with jvm param: -Xss2m
 * 因为jvm栈的总内存是有限的，所以创建越多的线程就有越多的线程去瓜分栈，就越会OOM
 * </pre>
 * @author ronry
 *
 */
public class VMStackOomTest {
	
	public void dontStop(){
		while(true){}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread=new Thread(new Runnable() {
				
				@Override
				public void run() {
					dontStop();					
				}
			});
			
			thread.start();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		VMStackOomTest test=new VMStackOomTest();
		test.stackLeakByThread();
	}

}
