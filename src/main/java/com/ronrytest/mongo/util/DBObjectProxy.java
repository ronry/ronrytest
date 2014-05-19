package com.ronrytest.mongo.util;

import com.mongodb.DBObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.bson.BSONObject;

import java.util.*;

public class DBObjectProxy extends BaseProxy implements DBObject  {

    private Object bean;
    private BeanDescription beanDescription;
    private Map<String, String> genericValues;

    public static DBObjectProxy create(Object object) {

        if(object == null) {
            throw new RuntimeException("Passed object is null");
        }

        DBObjectProxy dbObjectProxy = new DBObjectProxy();
        dbObjectProxy.bean = object;
        dbObjectProxy.beanDescription = BeanDescription.describe(object.getClass());

        return dbObjectProxy;
    }

    public void addGenericInfo(Map<String,String> values) {
        this.genericValues = values;    
    }

    public Object put(String key, Object v) {
        try {
            PropertyUtils.setProperty(bean, key, v);
            return v;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public void putAll(BSONObject o) {
        for(String key : o.keySet()) {
            put(key, o.get(key));
        }
    }

    public void putAll(Map m) {
        for (Map.Entry entry : (Set<Map.Entry>)m.entrySet()){
            put(entry.getKey().toString(), entry.getValue());
        }
    }

    public Object get(String key) {
        if(beanDescription.getPropertyDescriptor(key) == null) {
            if(genericValues != null && genericValues.containsKey(key)) {
                return genericValues.get(key);
            }
            return null;
        }
        
        Object val;
        try {
            val = PropertyUtils.getProperty(bean, key);
        } catch (Throwable t) {
            throw new RuntimeException("Can't get value from " + key , t);
        }

        return passValue(val, beanDescription.getPropertyDescriptor(key));
    }

    public Map toMap() {
        Map m = new HashMap();

        for (String key : beanDescription.getPropertyNames()) {
            m.put(key, get(key));
        }

        if(genericValues != null) {
            for (String key : genericValues.keySet()) {
                m.put(key, genericValues.get(key));
            }
        }
        
        return m;
    }

    public Object removeField(String key) {
        throw new RuntimeException("Unsupported method.");
    }

    public boolean containsKey(String s) {
        return genericValues != null && genericValues.containsKey(s) || beanDescription.getPropertyDescriptor(s) != null;

    }

    public boolean containsField(String s) {
        return containsKey(s);
    }

    public Set<String> keySet() {
        Set<String> set = new HashSet<String>();
        set.addAll(beanDescription.getPropertyNames());
        if(genericValues != null) {
            set.addAll(genericValues.keySet());
        }
        return set;
    }

    public void markAsPartialObject() {
        throw new RuntimeException("Method not implemented.");
    }

    public boolean isPartialObject() {
        return false;
    }
}