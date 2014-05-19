package com.ronrytest.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ronrytest.beans.SimpleBean;
import com.ronrytest.beans.interfacer.SimpleInterface;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/ronrytest/spring/aop/aoptestcontexts.xml");

        SimpleBean targetBean = (SimpleBean) context.getBean("simpleBeanTarget");
        targetBean.execute();
        targetBean.process("orangle");

        SimpleBean advisorBean = (SimpleBean) context.getBean("advisorBean");
        advisorBean.execute();
        advisorBean.process("orangle");

        SimpleBean multiAdviceBean = (SimpleBean) context.getBean("multiAdvicesBean");
        multiAdviceBean.execute();

        SimpleBean simpleBean = (SimpleBean) context.getBean("simpleBean");
        simpleBean.process("apple");

        SimpleInterface introductionBean = (SimpleInterface) context.getBean("introductionBean");
        introductionBean.invoke();

        SimpleBean throwBean = (SimpleBean) context.getBean("throwBean");
        throwBean.execute();
        throwBean.executeException();
    }

}
