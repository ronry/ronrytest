package com.ronrytest.protobuf;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.core.CollectionFactory;

import com.alibaba.citrus.util.StringUtil;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.ronrytest.protobuf.convertor.BooleanConvertor;
import com.ronrytest.protobuf.convertor.DateConvertor;
import com.ronrytest.protobuf.convertor.DoubleConvertor;
import com.ronrytest.protobuf.convertor.FloatConvertor;
import com.ronrytest.protobuf.convertor.IntegerConvertor;
import com.ronrytest.protobuf.convertor.JDKTypeConvertor;
import com.ronrytest.protobuf.convertor.ListConvertor;
import com.ronrytest.protobuf.convertor.LongConvertor;
import com.ronrytest.protobuf.convertor.MapConvertor;
import com.ronrytest.protobuf.convertor.StringConvertor;
import com.ronrytest.protobuf.lib.Map.MapEntity;

public class ProtoBeanCovertor {

    private static final Logger                       logger                       = LoggerFactory.getLogger(ProtoBeanCovertor.class);

    private static Map<Class<?>, JDKTypeConvertor<?>> jdkTypeConvertorMap = new HashMap<Class<?>, JDKTypeConvertor<?>>();

    static {

        jdkTypeConvertorMap.put(Boolean.class, new BooleanConvertor());
        jdkTypeConvertorMap.put(boolean.class, new BooleanConvertor());
        jdkTypeConvertorMap.put(double.class, new DoubleConvertor());
        jdkTypeConvertorMap.put(Double.class, new DoubleConvertor());
        jdkTypeConvertorMap.put(float.class, new FloatConvertor());
        jdkTypeConvertorMap.put(Float.class, new FloatConvertor());
        jdkTypeConvertorMap.put(int.class, new IntegerConvertor());
        jdkTypeConvertorMap.put(Integer.class, new IntegerConvertor());
        jdkTypeConvertorMap.put(Long.class, new LongConvertor());
        jdkTypeConvertorMap.put(long.class, new LongConvertor());
        jdkTypeConvertorMap.put(String.class, new StringConvertor());
        jdkTypeConvertorMap.put(Date.class, new DateConvertor());
        jdkTypeConvertorMap.put(List.class, new ListConvertor());
        jdkTypeConvertorMap.put(Map.class, new MapConvertor());

    }

    private static Class<?> getMessageBuilder(Class<?> beanClass) {

        Class<?> builderClass = null;

        if (builderClass == null) {
            try {
                builderClass = Class.forName(beanClass.getPackage().getName() + "." + beanClass.getSimpleName()
                                             + "Protos$" + beanClass.getSimpleName());
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return builderClass;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Message covert2Message(Object javaBean) throws Exception {

        Class<?> beanClass = javaBean.getClass();

        JDKTypeConvertor convertor = jdkTypeConvertorMap.get(beanClass);

        if (convertor != null) {
            return convertor.convert(javaBean);
        }

        Method method = getMessageBuilder(beanClass).getMethod("newBuilder");
        Builder builder = (Builder) method.invoke(null);
        BeanWrapper bean = new BeanWrapperImpl(builder);

        bean.registerCustomEditor(GeneratedMessage.class, new PropertyEditorSupport() {

            private Object value = null;

            public void setAsText(String text) throws java.lang.IllegalArgumentException {
                setValue(text);
            }

            public void setValue(Object value) {
                this.value = value;
            }

            public Object getValue() {

                if (value == null) {
                    return null;
                }

                JDKTypeConvertor convertor = jdkTypeConvertorMap.get(value.getClass());

                if (convertor == null) {
                    return null;
                }

                return convertor.convert(value);
            }
        });

        bean.registerCustomEditor(Iterable.class, new PropertyEditorSupport() {

            private List<Message> list;

            public void setValue(Object value) {

                this.list = new ArrayList();

                if (value instanceof java.util.Map) {

                    java.util.Map mapValue = (java.util.Map) value;

                    for (Object obj : mapValue.entrySet()) {
                        java.util.Map.Entry entry = (java.util.Map.Entry) obj;

                        MapEntity.Builder builder = MapEntity.newBuilder();
                        try {
                            builder.setKey(covert2Message(entry.getKey()).toByteString());
                            builder.setValue(covert2Message(entry.getValue()).toByteString());

                            list.add(builder.build());
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                } else if (value instanceof Iterable) {

                    for (Object obj : (Iterable) value) {
                        try {
                            list.add(covert2Message(obj));
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                } else if (value.getClass().isArray()) {

                    for (int i = 0; i < Array.getLength(value); i++) {
                        try {
                            list.add(i, covert2Message(Array.get(value, i)));
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                }
            }

            public Object getValue() {
                return list;
            }

        });

        for (Field field : javaBean.getClass().getDeclaredFields()) {
            String propertyName = field.getName();
            field.setAccessible(true);
            Object value = field.get(javaBean);

            if (value != null) {
                Class<?> fClass = field.getType();
                if (fClass.isArray() || Collection.class.isAssignableFrom(fClass)
                    || CollectionFactory.isApproximableMapType(fClass)) {

                    PropertyValue pv = new PropertyValue(propertyName, value);
                    Field ff = pv.getClass().getDeclaredField("resolvedDescriptor");
                    ff.setAccessible(true);

                    Method writeMethod = builder.getClass().getDeclaredMethod("addAll"
                                                                                      + StringUtil.toPascalCase(propertyName),
                                                                              Iterable.class);

                    ff.set(pv, new PropertyDescriptor(propertyName, null, writeMethod));
                    bean.setPropertyValue(pv);

                } else if (bean.isWritableProperty(propertyName)) {
                    bean.setPropertyValue(propertyName, value);
                }
            }
        }

        return builder.build();
    }

    public static void main(String[] args) throws Exception {

        Bean bean = new Bean();

        bean.setId(111);
        bean.setEmail("sdfsd");
        bean.setLastLoginTime(new java.util.Date());
        bean.setZhiwu(Arrays.asList("xxx", "yyyy"));
        bean.setGonghao(new int[] { 1, 2 });

        Map<String, String> shouru = new HashMap<String, String>();
        shouru.put("1", "a");
        shouru.put("2", "b");
        bean.setShouru(shouru);

        Message message = covert2Message(bean);

        System.out.println(message.toByteString());

    }
}
