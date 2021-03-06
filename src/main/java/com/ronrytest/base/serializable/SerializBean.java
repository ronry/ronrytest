package com.ronrytest.base.serializable;

import java.io.Serializable;

public class SerializBean implements Serializable {

    private static final long serialVersionUID = 4342883927543454758L;

    private String            name;
    private int               age;

    public SerializBean(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
