package com.ronrytest.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PerfectStop {

    class InterruptableBlocker {

        private ReentrantLock lock = new ReentrantLock();

        public InterruptableBlocker(){
            System.out.println("InterruptableBlocker instanced");
            lock.lock();
        }

        public void doBlockTask() {
            System.out.println("do block task");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("lock is interrupted");
            }
            System.out.println("block task done");
        }

    }

    class PerfectStopTask implements Runnable {

        private InterruptableBlocker blocker = new InterruptableBlocker();
        private Thread               taskT   = null;
        private volatile boolean     stopped = false;

        public void stop() {
            stopped = true;
            taskT.interrupt();
        }

        @Override
        public void run() {
            taskT = Thread.currentThread();
            while (!stopped && !Thread.interrupted()) {
                System.out.println("begin to do block task");
                blocker.doBlockTask();
                System.out.println("block task ended");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        PerfectStop s = new PerfectStop();
        PerfectStopTask st = s.new PerfectStopTask();
        Thread tt = new Thread(st, "PerfectStopTaskThread");
        tt.start();

        TimeUnit.MILLISECONDS.sleep(5000);

        System.out.println("try to end task");
        st.stop();
        TimeUnit.MILLISECONDS.sleep(5000);

        System.out.println("end!!!!");
    }

}
