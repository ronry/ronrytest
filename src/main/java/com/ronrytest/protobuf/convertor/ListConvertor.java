package com.ronrytest.protobuf.convertor;

import java.util.List;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.ProtoBeanCovertor;
import com.ronrytest.protobuf.lib.List.ListEntity;

public class ListConvertor implements JDKTypeConvertor<List<?>> {

    @Override
    public Message convert(List<?> t) {

        ListEntity.Builder builder = ListEntity.newBuilder();
        for (Object obj : t) {
            try {
                builder.addValue(ProtoBeanCovertor.covert2Message(obj).toByteString());
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return builder.build();
    }
}
