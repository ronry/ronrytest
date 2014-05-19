package com.ronrytest.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * java 的类型共分5种，它们都有对应的实现类： <br/>
 * ----类型 ---------------->实例 ----------------->实现类 <br/>
 * 1 raw types-->如String, Number等--->Class <br/>
 * 2 primitive types--> int, float, double等基本类型-->Class <br/>
 * 3 parameterized types-->含泛型定义的类，如：List, Map-->ParameterizedType <br/>
 * 4 type variables -->类型变量，不确定其类型,如：V, <V extends Number> --> TypeVariable<D> <br/>
 * 5 array types-->含泛型定义的数组，数组内可以是parameterized types或type variables，如<K> K[]-->GenericArrayType <br/>
 * 另外还有一个实现类WildcardType，其表示形如 ?, ? extends Number一类的type
 * 
 * @author ronry 2011-4-8 下午11:31:36
 */
public class TypePojo<E> {

    private int                            primitive_type;
    private Map<String, ? extends Integer> parameterized_type;
    private E                              type_variable;
    private E[]                            array_type;

    public static void main(String[] args) {
        Class<?> pojoClass = TypePojo.class;
        try {
            // get primitive type
            System.out.println("get primitive type-->");
            Field primitive_type_field = pojoClass.getDeclaredField("primitive_type");
            Type primitive_type = primitive_type_field.getGenericType();
            System.out.println("Type Class: " + primitive_type.getClass() + " Type: " + primitive_type);
            // get parameterized type
            System.out.println("\nget parameterized type-->");
            Field parameterized_type_field = pojoClass.getDeclaredField("parameterized_type");
            Type parameterized_type = parameterized_type_field.getGenericType();
            System.out.println("Type Class: " + parameterized_type.getClass() + " Type: " + parameterized_type);
            // get WildcardType
            System.out.println("get actual types-->");
            ParameterizedType real_parameterized_type = (ParameterizedType) parameterized_type;
            Type[] actualTypes = real_parameterized_type.getActualTypeArguments();
            for (Type type : actualTypes) {
                System.out.println("Type Class: " + type.getClass() + " Type: " + type);
            }
            // get type variables
            System.out.println("\nget type variables-->");
            Field type_variable_field = pojoClass.getDeclaredField("type_variable");
            Type type_variable = type_variable_field.getGenericType();
            System.out.println("Type Class: " + type_variable.getClass() + " Type: " + type_variable);
            // get array type
            System.out.println("\nget array type-->");
            Field array_type_field = pojoClass.getDeclaredField("array_type");
            Type array_type = array_type_field.getGenericType();
            System.out.println("Type Class: " + array_type.getClass() + " Type: " + array_type);

            System.out.println("is int primitive : " + int.class.isPrimitive());
            System.out.println("is String primitive : " + String.class.isPrimitive());
            System.out.println("is List primitive : " + List.class.isPrimitive());
            System.out.println("is Test primitive : " + Test.class.isPrimitive());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
