package com.ronrytest.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class MyPointcut extends StaticMethodMatcherPointcut {

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {

            public boolean matches(Class clazz) {
                if (clazz.getName().equals("com.ronrytest.beans.SimpleBean")) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        if (method.getName().equals("execute")) {
            return true;
        }
        return false;
    }

}
