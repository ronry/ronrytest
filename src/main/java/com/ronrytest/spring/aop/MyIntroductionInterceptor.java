package com.ronrytest.spring.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import com.ronrytest.beans.interfacer.SimpleInterface;

public class MyIntroductionInterceptor extends DelegatingIntroductionInterceptor implements SimpleInterface {

    private static final long serialVersionUID = 1L;

    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable {
        // TODO Auto-generated method stub
        invoke();
        return null;
    }

    @Override
    public void invoke() {
        System.out.println("MyIntroductionInterceptor invoked");
    }

}
