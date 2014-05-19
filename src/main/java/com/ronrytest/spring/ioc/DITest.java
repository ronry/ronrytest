package com.ronrytest.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ronrytest.beans.ComplexBean;

public class DITest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/ronrytest/spring/aop/ioctestcontexts.xml");

        ComplexBean complexBean = (ComplexBean) context.getBean("complexBean");
        System.out.println(complexBean.getName());
        System.out.println(complexBean.getSimpleBean().getName());
        System.out.println(complexBean.getOtherSimpleBean().getName());
    }

}
