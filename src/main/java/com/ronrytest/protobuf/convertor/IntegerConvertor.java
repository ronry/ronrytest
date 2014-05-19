package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Int.IntegerEntity;

public class IntegerConvertor implements JDKTypeConvertor<Integer> {

    @Override
    public Message convert(Integer t) {
        IntegerEntity.Builder builder = IntegerEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
