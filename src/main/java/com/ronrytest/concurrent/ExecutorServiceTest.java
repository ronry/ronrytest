/**
 * 
 */
package com.ronrytest.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ronry
 * 
 */
public class ExecutorServiceTest {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		Future<Object> f = executor.submit(new ErrorTask());

		try {
			System.out.println(f.get());
		} catch (InterruptedException e) {
			System.out.println("111");
			System.out.println(e);
		} catch (ExecutionException e) {
			System.out.println("222");
			System.out.println(e);
		}

		List<Task<Object>> tasks = new ArrayList<Task<Object>>();

		// tasks.add(new SimpleTask());
		tasks.add(new ErrorTask());

		List<Future<Object>> result = null;

		try {
			result = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Future<Object> future : result) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				System.out.println("111");
				System.out.println(e);
			} catch (ExecutionException e) {
				System.out.println("222");
				System.out.println(e);
			}
		}

		executor.shutdownNow();

	}

	public static interface Task<T> extends Callable<T> {

	}

	public static class SimpleTask implements Task<Integer> {

		@Override
		public Integer call() throws Exception {
			System.out.println("SimpleTask begin");

			Thread.sleep(3000);

			System.out.println("SimpleTask end");

			return Integer.MAX_VALUE;
		}

	}

	public static class ErrorTask implements Task<Object> {

		@Override
		public Object call() throws Exception {
			System.out.println("ErrorTask begin");

			throw new Exception("xxxxxxxxxx");

		}
	}

}
