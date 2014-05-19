package com.ronrytest.protobuf.convertor;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.lib.Long.LongEntity;

public class LongConvertor implements JDKTypeConvertor<Long> {

    @Override
    public Message convert(Long t) {
        LongEntity.Builder builder = LongEntity.newBuilder();
        builder.setValue(t);
        return builder.build();
    }

}
