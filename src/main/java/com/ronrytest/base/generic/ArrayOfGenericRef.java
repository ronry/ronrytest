/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.base.generic;

/**
 * 类ArrayOfGenericRef.java的实现描述：TODO 类实现描述
 * 
 * @author ronry 2011-6-6 下午12:18:17
 */
public class ArrayOfGenericRef {

    static Generic<Integer>[] array;

    public static void main(String[] args) {
        ArrayOfGenericRef ref = new ArrayOfGenericRef();
        array = (Generic<Integer>[]) new Generic[10];
        System.out.println(array.getClass().getSimpleName());
        array[0] = ref.new Generic<Integer>();
        System.out.println(array);
    }

    class Generic<T> {
    }
}
