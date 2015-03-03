package com.ronrytest.nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <pre>
 * 会发送RST包的场景
 * 1. 主动关闭方发起关闭时tcp的接收缓冲区里还有数据为消费
 * 
 * 
 * 不会发送RST包的场景：
 * 1. 主动关闭方关闭时，应用程序已经消费了接收缓冲区中的所有的数据 (此时可能被关闭发还有数据要发送的，只是还没发或者还没有到达主动关闭方)
 * </pre>
 * 
 * @author jinliang.lvjl 2015年3月3日 下午12:21:45
 */
public class UngracefulCloseConnectionWirhRstTest {

    private static ServerSocket serverSocket;

    public static void startServer() {
        try {
            serverSocket = new ServerSocket(9090, 1);
            while (true) {
                final Socket client = serverSocket.accept();

                // 消费数据的线程：如果关闭之前先消费了已经收到的数据，则不会发送RST，而是执行正常的关闭流程
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // try {
                        // InputStream in = client.getInputStream();
                        // char[] cs = IOUtils.toCharArray(in);
                        // System.out.println(cs.length);
                        // } catch (Exception e) {
                        // e.printStackTrace();
                        // }
                    }
                }).start();


                // 调整这个时间
                // 如调整为100，则是客户端发送数据的时候失败
                // 如果调整为较大的值，比如1000,则是客户端在读数据的时候失败
                Thread.sleep(500);
                // 因为关闭的时候应用程序没有消费tcp的接收栈中的数据，所以执行的不是正常的关闭流程，而是直接发送RST
                client.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void startClient() throws InterruptedException {
        try {
            Socket client = new Socket("127.0.0.1", 9090);
            // client.setSoTimeout(5000);
            System.out.println(client);
            InputStream ins = client.getInputStream();
            int i = 0;
            // System.out.println(ins.read());
            while (i++ < 10000000) {
                client.getOutputStream().write("a".getBytes());
            }
            // 不执行close，服务端就没有办法读到流的结束符。如果模拟的场景是服务端消费数据的，则必须执行这个关闭。执行了这个关闭就是客户端发起了关闭流程
            // client.getOutputStream().close();
            Thread.sleep(15000);
            System.out.println(ins.read());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {

            @Override
            public void run() {
                startServer();
            }
        }).start();

        Thread.sleep(1000);

        startClient();

        // serverSocket.close();
    }

}
