package com.ronrytest.concurrent;

import java.util.concurrent.TimeUnit;

public class RethrowableTaskTest {

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {

            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            public Throwable getException() {
                return t;
            }

        }

        class StopTask implements Runnable {

            private Thread thread;

            public StopTask(Thread thread){
                this.thread = thread;
            }

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("interrupt rThread.........");
                thread.interrupt();
            }

        }

        RethrowableTask task = new RethrowableTask();
        final Thread rThread = new Thread(task);
        rThread.start();

        new Thread(new StopTask(rThread)).start();

        // ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(2);
        // exec.schedule(new Runnable() {
        //
        // @Override
        // public void run() {
        // System.out.println("interrupt rThread.........");
        // rThread.interrupt();
        // }
        // }, timeout, unit);

        rThread.join(4000);

        System.out.println(task.getException());
    }

    public static void main(String[] args) throws InterruptedException {
        timeRun(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    // System.out.println("do task.....");
                }
            }
        }, 1, TimeUnit.SECONDS);
    }
}
