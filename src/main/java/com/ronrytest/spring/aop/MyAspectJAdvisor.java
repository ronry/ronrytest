package com.ronrytest.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspectJAdvisor {

    @Before("execution(* *(..))")
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("MyAspectJAdvisor.beforeAdvice()");
    }
}
