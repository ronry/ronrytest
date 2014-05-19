/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.beans;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类ComplexBean.java的实现描述：TODO 类实现描述
 * 
 * @author ronry 2010-12-21 下午10:31:49
 */
public class ComplexBean {

    private String          name;

    private SimpleBean      simpleBean;

    @Autowired
    private OtherSimpleBean otherSimpleBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public void setSimpleBean(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }

    public OtherSimpleBean getOtherSimpleBean() {
        return otherSimpleBean;
    }

}
