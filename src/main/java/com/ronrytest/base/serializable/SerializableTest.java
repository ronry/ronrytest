package com.ronrytest.base.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SerializableTest {

    /**
     * @param args
     * @throws Exception
     * @throws
     */
    public static void main(String[] args) throws Exception {
        // ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/home/ronry/seri.txt"));
        // os.writeObject(new SerializBean("ronry", 28));
        // os.close();
        // System.out.println("ok");

        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("/home/ronry/seri.txt"));
        // 序列化和反序列化的全类名必须一致，同时serialVersionUID也必须一致
        // SerializBean bean = (SerializBean) oi.readObject();
        // System.out.println(bean.getName());
    }

}
