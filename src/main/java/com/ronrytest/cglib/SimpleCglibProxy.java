package com.ronrytest.cglib;

import com.ronrytest.beans.SimpleBean;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class SimpleCglibProxy {

    public static SimpleBean createNoOpProxy(String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleBean.class);
        enhancer.setCallback(NoOp.INSTANCE);
        return (SimpleBean) enhancer.create(new Class[] { String.class }, new Object[] { name });
    }

    public static SimpleBean createFixedValueProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleBean.class);
        enhancer.setCallback(new MyFixedValue());
        return (SimpleBean) enhancer.create(new Class[] { String.class }, new Object[] { "tt" });
    }

    public static void main(String[] args) {
        SimpleBean bean = SimpleCglibProxy.createNoOpProxy("ronry");
        bean.execute();
        System.out.println(bean.getName());

        SimpleBean fixedBean = SimpleCglibProxy.createFixedValueProxy();
        fixedBean.execute();
        System.out.println(fixedBean.getName());
    }
}
