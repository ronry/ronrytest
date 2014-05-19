package com.ronrytest.reflect;

import java.lang.reflect.Field;

import com.ronrytest.beans.SimpleBean;

public class Test {

    public static void main(String[] args) throws Exception {
        String classType = "com.ronrytest.beans.SimpleBean";
        String filedName = "name";

        Object o = new SimpleBean("ronry");

        Class<?> clazz = Class.forName(classType);
        if (clazz.isInstance(o)) {
            System.out.println("yes");
            Field filed = clazz.getDeclaredField(filedName);
            filed.setAccessible(true);
            System.out.println(filed.get(o));
            System.out.println(filed.getType());

        } else {
            System.out.println("no");
        }
    }
}
