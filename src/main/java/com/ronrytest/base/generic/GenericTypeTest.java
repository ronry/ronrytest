package com.ronrytest.base.generic;

public class GenericTypeTest<T> {

    private T type;

    public GenericTypeTest(){
    }

    public GenericTypeTest(T type){
        this.type = type;
    }

    public boolean test() {
        if (type instanceof String) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GenericTypeTest<String> stringType = new GenericTypeTest<String>();
        GenericTypeTest<String> stringType2 = new GenericTypeTest<String>("string");
        System.out.println(stringType.test());
        System.out.println(stringType2.test());
    }
}
