package com.ronrytest.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class TypeTest {
    
    interface GeneircInteface<T> {     
        
        T method1(T obj);     
    }     
        
    interface CommonInteface {     
        
        Integer method2(Integer obj);     
    }     
        
    class BaseGeneircInteface<R> implements GeneircInteface<R> {     
        
        protected R result;     
        
        @Override    
        public R method1(R obj) {     
            return obj;     
        }     
        
    }     
        
    class GenericClass<T> implements GeneircInteface<List<T>>, CommonInteface {

        protected List<T> result;
        
        @Override    
        public List<T> method1(List<T> obj) {
            result = obj;     
            return result;     
        }     
        
        public Integer method2(Integer obj) {     
            return obj;     
        }     
        
        public <E extends Throwable> T method3(T obj) throws E {
            return obj;     
        }     
        
    }    

    private static void classGeneric() {
        TypeTest typeTest = new TypeTest();
        System.out.println("\n--------------------- classGeneric ---------------------");
        GenericClass<String> gc = typeTest.new GenericClass<String>();

        System.out.println("============== getInterfaces"); // 接口信息，会丢掉泛型信息
        Class<?>[] classes = gc.getClass().getInterfaces();
        for (Type t : classes) {
            System.out.println(t + " : " + getClass(t, 0));
        }

        System.out.println("============== getGenericInterfaces");
        Type[] gis = gc.getClass().getGenericInterfaces(); // 接口的泛型信息
        for (Type t : gis) {
            System.out.println(t + " : " + getClass(t, 0));
        }

        System.out.println("============== getGenericSuperclass");
        Type gps = gc.getClass().getGenericSuperclass(); // 父类的泛型信息
        System.out.println(gps + " : " + getClass(gps, 0));

        System.out.println("============== getTypeParameters");
        TypeVariable<?>[] gtr = gc.getClass().getTypeParameters();
        for (TypeVariable<?> t : gtr) {
            StringBuilder stb = new StringBuilder();
            for (Type tp : t.getBounds()) {
                stb.append(tp + " : ");
            }

            System.out.println(t + " : " + t.getName() + " upper bound : " + stb);
        }

    }

    private static void methodGeneric() throws Exception {
        System.out.println("\n--------------------- methodGeneric ---------------------");
        TypeTest typeTest = new TypeTest();
        GenericClass<?> gc = typeTest.new GenericClass<String>();
        Method method3 = gc.getClass().getDeclaredMethod("method3", new Class[] { Object.class });

        System.out.println("============== getParameterTypes");
        Class<?>[] classes = method3.getParameterTypes(); // E会被直接擦除到Object
        for (Type t : classes) {
            System.out.println(t + " : " + getClass(t, 0));
        }

        System.out.println("============== getGenericParameterTypes");
        Type[] gpt3 = method3.getGenericParameterTypes();
        for (Type t : gpt3) {
            System.out.println(t + " : " + getClass(t, 0));
        }

        System.out.println("============== getGenericExceptionTypes");
        Type[] get3 = method3.getGenericExceptionTypes();
        for (Type t : get3) {
            System.out.println(t + " : " + getClass(t, 0));
        }

        System.out.println("============== getType");
        Type gt3 = method3.getGenericReturnType();
        System.out.println(gt3 + " : " + getClass(gt3, 0));
    }

    private static void fieldGeneric() throws Exception {
        System.out.println("\n--------------------- fieldGeneric ---------------------");
        TypeTest typeTest = new TypeTest();
        GenericClass<?> gc = typeTest.new GenericClass<String>();
        Field field = gc.getClass().getDeclaredField("result");

        System.out.println("============== getGenericType");
        Type gt = field.getGenericType();
        System.out.println(gt + " : " + getClass(gt, 0));

        System.out.println("============== getType");
        Type ft = field.getType();
        System.out.println(ft + " : " + getClass(ft, 0));
    }

    private static Class<?> getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return (Class<?>) getClass(((TypeVariable<?>) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {// class本身也是type，强制转型
            return (Class<?>) type;
        }
    }
                  
    private static Class<?> getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class<?>) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return (Class<?>) getClass(((TypeVariable<?>) genericClass).getBounds()[0], 0);
        } else {
            return (Class<?>) genericClass;
        }
    }

    public static void main(String[] args) throws Exception {
        classGeneric();
        methodGeneric();
        fieldGeneric();
    }

}
