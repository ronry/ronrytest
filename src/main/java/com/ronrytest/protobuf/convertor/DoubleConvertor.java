package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Double.DoubleEntity;

public class DoubleConvertor implements JDKTypeConvertor<Double> {

    @Override
    public Message convert(Double t) {
        DoubleEntity.Builder builder = DoubleEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
