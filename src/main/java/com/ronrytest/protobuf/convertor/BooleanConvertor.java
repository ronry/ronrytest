package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Boolean.BooleanEntity;

public class BooleanConvertor implements JDKTypeConvertor<Boolean> {

    @Override
    public Message convert(Boolean t) {
        BooleanEntity.Builder builder = BooleanEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
