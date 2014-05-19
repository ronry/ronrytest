package com.ronrytest.protobuf.convertor;

import java.util.Map;

import com.google.protobuf.Message;
import com.ronrytest.protobuf.ProtoBeanCovertor;
import com.ronrytest.protobuf.lib.List.ListEntity;
import com.ronrytest.protobuf.lib.Map.MapEntity;

public class MapConvertor implements JDKTypeConvertor<Map<?, ?>> {

    @Override
    public Message convert(Map<?, ?> t) {

        ListEntity.Builder builder = ListEntity.newBuilder();

        for (Map.Entry<?, ?> entry : t.entrySet()) {

            MapEntity.Builder mapBuilder = MapEntity.newBuilder();
            try {
                mapBuilder.setKey(ProtoBeanCovertor.covert2Message(entry.getKey()).toByteString());
                mapBuilder.setValue(ProtoBeanCovertor.covert2Message(entry.getValue()).toByteString());

                builder.addValue(builder.build().toByteString());
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return builder.build();
    }

}
