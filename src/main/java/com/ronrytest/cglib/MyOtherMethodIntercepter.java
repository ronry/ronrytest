package com.ronrytest.cglib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyOtherMethodIntercepter implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Class<?> clazz = obj.getClass();
        Constructor<?>[] constructors = clazz.getConstructors();
        Object o = constructors[1].newInstance("lvjinliang");
        System.out.println("MyOtherMethodIntercepter is invoke");
        // proxy.invoke(o, args); rught invoke on o
        // proxy.invokeSuper(o, args); rught invoke on o
        // proxy.invoke(obj, args); error invoke
        return proxy.invokeSuper(obj, args);
    }
}
