package com.ronrytest.advance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TooManySessionTest {

    class BusyServer {

        private volatile int   waitedRequestCount;
        private List<Runnable> threadProcessors = Collections.synchronizedList(new ArrayList<Runnable>());
        private long           beginTime        = System.currentTimeMillis();

        public BusyServer(){
            for (int i = 0; i < 20; i++) {
                threadProcessors.add(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {

                        }
                    }
                });
            }
        }

        public void server(Request request) {
            if (waitedRequestCount > 20000) {
                System.out.println("crush!!!!!!");
                System.out.println("current request count is " + waitedRequestCount);
                System.out.println("time used :" + (System.currentTimeMillis() - beginTime));
                System.exit(0);
            }
            if (!threadProcessors.isEmpty()) {
                System.out.println("server request no : " + request.getNo());
                Runnable processor = threadProcessors.remove(0);
                new Thread(processor).start();
                threadProcessors.add(processor);
            } else {
                waitedRequestCount++;
            }
        }
    }

    class Request {

        private int no;

        public Request(int no){
            this.no = no;
        }

        public int getNo() {
            return no;
        }
    }

    class RequestSender implements Runnable {

        private BusyServer server;
        private int        no;

        public RequestSender(BusyServer server, int no){
            this.server = server;
            this.no = no;
        }

        @Override
        public void run() {
            server.server(new Request(no));
        }

    }
    
    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        TooManySessionTest test = new TooManySessionTest();
        BusyServer server = test.new BusyServer();
        for (int no = 1;; no++) {
            new Thread(test.new RequestSender(server, no)).start();
            Thread.sleep(0);
        }
    }
}
