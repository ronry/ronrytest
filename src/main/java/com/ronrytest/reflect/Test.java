package com.ronrytest.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ronrytest.beans.SimpleBean;

public class Test {


    public static void main(String[] args) throws Exception {

        // 局部变量属于使用侧，哪不到具体的实际参数的类型，只能拿到E
        List<String> list = new ArrayList<String>();

        Class<?> clazz2 = list.getClass();

        System.out.println(clazz2.getTypeParameters()[0].getName());

        Type cType = (Type) clazz2;
        System.out.println(cType.getClass() + "  " + cType);

        for (Method method : clazz2.getMethods()) {
            Type type = method.getGenericReturnType();
            System.out.println(type.getClass() + "  " + type);
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                System.out.println("ParameterizedType raw type " + pType.getRawType());
                Type[] ttypes = pType.getActualTypeArguments();
                for (Type ttype : ttypes) {
                    System.out.println(ttype.getClass() + "  " + ttype);
                }
                System.out.println("ParameterizedType end");
            }
        }

        String classType = "com.ronrytest.beans.SimpleBean";
        String filedName = "name";

        Object o = new SimpleBean("ronry");

        Class<?> clazz = Class.forName(classType);
        if (clazz.isInstance(o)) {
            System.out.println("yes");
            Field filed = clazz.getDeclaredField(filedName);
            filed.setAccessible(true);
            System.out.println(filed.get(o));
            System.out.println(filed.getType());

        } else {
            System.out.println("no");
        }
    }
}
