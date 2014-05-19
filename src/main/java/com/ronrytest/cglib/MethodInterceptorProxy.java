package com.ronrytest.cglib;

import com.ronrytest.beans.SimpleBean;

import net.sf.cglib.proxy.Enhancer;

public class MethodInterceptorProxy {

    public static SimpleBean createMethodInterceptorProxy(String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleBean.class);
        enhancer.setCallback(new MyMethodInterceptor());
        return (SimpleBean) enhancer.create(new Class[] { String.class }, new Object[] { name });
    }

    public static SimpleBean createOtherMethodInterceptorProxy(String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleBean.class);
        enhancer.setCallback(new MyOtherMethodIntercepter());
        return (SimpleBean) enhancer.create(new Class[] { String.class }, new Object[] { name });
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleBean mipSimpleBean = MethodInterceptorProxy.createMethodInterceptorProxy("ronry");
        mipSimpleBean.execute();
        System.out.println(mipSimpleBean.getName());

        SimpleBean othMipSimpleBean = MethodInterceptorProxy.createOtherMethodInterceptorProxy("ronry");
        othMipSimpleBean.execute();
        System.out.println(othMipSimpleBean.getName());
        othMipSimpleBean.process("apple");
    }

}
