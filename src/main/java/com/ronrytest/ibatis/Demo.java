package com.ronrytest.ibatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext application = new ClassPathXmlApplicationContext("com/ronrytest/ibatis/context.xml");
        IbatisUserInfoDAO dao = (IbatisUserInfoDAO) application.getBean("userDAO");

        User user = new User("root6", "12345");
        dao.insert(user);
        // dao.insertWithParamMap(user);

        // error,the param type must same with parameterMap
        // dao.insertWithErrorType();

        System.out.println("bybye");
    }
}
