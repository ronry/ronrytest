package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Float.FloatEntity;

public class FloatConvertor implements JDKTypeConvertor<Float> {

    @Override
    public Message convert(Float t) {
        FloatEntity.Builder builder = FloatEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
