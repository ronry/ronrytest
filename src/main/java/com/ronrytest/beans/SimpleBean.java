/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.beans;

/**
 * ��SimpleBean.java��ʵ��������TODO ��ʵ������
 * 
 * @author ronry 2010-12-1 ����10:00:32
 */
public class SimpleBean extends ParentBean {

    private String name;

    public SimpleBean(){
    }

    public SimpleBean(String name){
        super();
        this.name = name;
    }

    public void execute() {
        System.out.println(name + " is executed");
    }

    public void executeException() {
        throw new RuntimeException();
    }

    public String getName() {
        return name;
    }

    public void process(String o) {
        System.out.println(o + " is processed by " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

}
