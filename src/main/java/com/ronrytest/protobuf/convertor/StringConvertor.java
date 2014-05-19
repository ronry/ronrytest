package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.String.StringEntity;

public class StringConvertor implements JDKTypeConvertor<String> {

    @Override
    public Message convert(String t) {
        StringEntity.Builder builder = StringEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
