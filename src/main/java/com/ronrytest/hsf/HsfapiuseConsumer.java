package com.ronrytest.hsf;

import com.ronrytest.hsf.service.HelloWorldService;
import com.taobao.hsf.app.api.util.HSFApiConsumerBean;

public class HsfapiuseConsumer {

    public static void main(String[] args) {

        HSFApiConsumerBean consumerBean = new HSFApiConsumerBean();
        consumerBean.setInterfaceName("com.ronrytest.hsf.service.HelloWorldService");
        consumerBean.setVersion("1.0.kongming");
        consumerBean.setGroup("HSF");
        try {
            consumerBean.init();
            HelloWorldService helloWorldService = (HelloWorldService) consumerBean.getObject();
            helloWorldService.say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
