/**
 * 
 */
package com.ronrytest.concurrent;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author ronry
 *
 */
public class ThreadSafeWithImmutable {
	
	
	
	public static void main(String[] args) {
		Factorizer factorizer=new Factorizer();
		new Thread(new TheadTask(1,factorizer)).start();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		new Thread(new TheadTask(2,factorizer)).start();
	}
	
	public static class Factorizer{
		private volatile ImmutableCache cache=new ImmutableCache(null,null);
		
		public BigInteger[] service(BigInteger i){
			BigInteger[] result=cache.getFactors(i);
			
			if(result==null){
				result=new BigInteger[]{};
				cache=new ImmutableCache(i,result);
			}
			
			return result;
		}
	}
	
	public static class TheadTask implements Runnable{
		
		private int i;
		private Factorizer factorizer;
		
		public TheadTask(int i,Factorizer factorizer){
			this.i=i;
			this.factorizer=factorizer;
		}

		@Override
		public void run() {
			factorizer.service(BigInteger.valueOf(i));
		}
		
	}
	
	
	public static class ImmutableCache{
		private final BigInteger lastNumber;
		private final BigInteger[] lastFactors;
		
		public ImmutableCache(BigInteger i,BigInteger[] factors){
			System.out.println("create new cache object...");
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lastNumber=i;
			if(factors!=null){
				lastFactors=Arrays.copyOf(factors, factors.length);
			}else{
				lastFactors=null;
			}
			System.out.println("new cache object created");
		}
		
		public BigInteger[] getFactors(BigInteger i){
			System.out.println("getFators begin...");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(lastNumber==null||!lastNumber.equals(i)){
				return null;
			}else{
				return Arrays.copyOf(lastFactors, lastFactors.length);
			}
		}
	}

}
