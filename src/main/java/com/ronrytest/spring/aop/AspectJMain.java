package com.ronrytest.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ronrytest.beans.SimpleBean;

public class AspectJMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                                                                        "com/ronrytest/spring/aop/aspectjaoptestcontexts.xml");

        SimpleBean targetBean = (SimpleBean) context.getBean("simpleBeanTarget");
        targetBean.execute();
        targetBean.process("orangle");
    }

}
