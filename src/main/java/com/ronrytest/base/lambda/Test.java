package com.ronrytest.base.lambda;

/**
 * ����ʽ�ӿڿ���û��FunctionInterfaceע��
 * Created by jinliang on 22/11/2016.
 */
public class Test {

    static void testFIWithoutAnnotation(FIWithoutAnnotation f){
        f.test();
    }

    static void testCommonInterface(CommonInterface f){
        f.test();
    }

    interface FIWithoutAnnotation{
        void test();
    }

    interface CommonInterface{
        void test();

        void test2();
    }

    public static void main(String[] args) {
        Test.testFIWithoutAnnotation(()-> System.out.println("ok"));
        //Test.testCommonInterface(()-> System.out.println("ok")); ���ܱ���,��ΪtestCommonInterface����β��Ǻ���ʽ�ӿ�
    }
}
