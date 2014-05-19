package com.ronrytest.cglib;

import net.sf.cglib.proxy.FixedValue;

public class MyFixedValue implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("MyFixedValue.loadObject");
        return "MyFixedValue";
    }

}
