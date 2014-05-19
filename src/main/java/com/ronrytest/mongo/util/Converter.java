package com.ronrytest.mongo.util;

import com.mongodb.DBObject;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Map;

public class Converter {


    public static DBObject toDBObject(Object obj) {
        Object val = BaseProxy.passValue(obj);
        if (val instanceof DBObject) {
            return (DBObject) val;
        } else {
            throw new RuntimeException("Converted object is not instance of DBObject");
        }
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T toObject(Class<T> clazz, DBObject source) {
        T newObject = (T) BeanEnhancer.create(clazz, source);
        try {
            T copyObject = clazz.newInstance();
            // todo maybe other way of solving this?
            // if no complex entities with polymorphism are used, it should be safe to just "return newObject" and skip bean copying
            PropertyUtils.copyProperties(copyObject, newObject);
            return copyObject;
        } catch (Exception e) {
            return newObject;
        }
    }

}

