package com.ronrytest.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyMethodBeforeInterceptor implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MyMethodBeforeInterceptor invoked");
        // method.invoke(target, args); if invoke this,the method will be invoked twice
    }

}
