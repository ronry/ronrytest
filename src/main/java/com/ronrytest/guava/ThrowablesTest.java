package com.ronrytest.guava;

import com.google.common.base.Throwables;

public class ThrowablesTest {

    public static void test(int arg) throws Exception {
        if (arg == 0) {
            throw new IllegalArgumentException();
        } else {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try {
            test(1);
        } catch (Exception e) {
            Throwables.propagateIfInstanceOf(e, IllegalArgumentException.class);
            System.out.println("RuntimeException is buhuo");
        }

        try {
            test(0);
        } catch (Exception e) {
            Throwables.propagateIfInstanceOf(e, IllegalArgumentException.class);
        }
    }

}
