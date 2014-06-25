package com.ronrytest.hsf;

import com.ronrytest.hsf.service.impl.HelloWorldServiceImpl;
import com.taobao.hsf.app.api.util.HSFApiProviderBean;

public class HsfapiuseProvider {

    public static void main(String[] args) throws Exception {
        HSFApiProviderBean providerBean = new HSFApiProviderBean();
        providerBean.setServiceInterface("com.ronrytest.hsf.service.HelloWorldService");
        providerBean.setServiceVersion("1.0.kongming");
        providerBean.setTarget(new HelloWorldServiceImpl()); // 这个是服务的实现类
        providerBean.init();
    }

}
