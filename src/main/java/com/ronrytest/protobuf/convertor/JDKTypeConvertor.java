package com.ronrytest.protobuf.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.Message;

/**
 * 将jdk内置的一些数据类型对象装换成Message对象的装换器
 * 
 * @author ronry 2013-7-12 下午2:03:19
 */
public interface JDKTypeConvertor<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(JDKTypeConvertor.class);

    public Message convert(T t);
}
