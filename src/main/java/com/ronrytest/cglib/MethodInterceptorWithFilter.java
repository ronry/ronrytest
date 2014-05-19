package com.ronrytest.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

import com.ronrytest.beans.SimpleBean;

public class MethodInterceptorWithFilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        if (method.getName().equals("execute")) {
            return 1;
        }
        return 0;
    }

    public static SimpleBean createProxyWithFilter(String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleBean.class);
        enhancer.setCallbacks(new Callback[] { new MyOtherMethodIntercepter(), new MyMethodInterceptor() });
        enhancer.setCallbackFilter(new MethodInterceptorWithFilter());
        return (SimpleBean) enhancer.create(new Class[] { String.class }, new Object[] { name });
    }

    public static void main(String[] args) {
        SimpleBean bean = createProxyWithFilter("ronry");
        bean.execute();
        bean.process("apple");
    }
}
