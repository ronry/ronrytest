package com.ronrytest.protobuf.convertor;

import java.util.Date;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Date.DateEntity;

public class DateConvertor implements JDKTypeConvertor<Date> {

    @Override
    public Message convert(Date t) {
        DateEntity.Builder dateEntity = DateEntity.newBuilder();
        dateEntity.setTime(t.getTime());
        return dateEntity.build();
    }

}
