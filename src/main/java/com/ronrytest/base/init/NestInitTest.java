package com.ronrytest.base.init;

public class NestInitTest {

    private String    name;
    private NestClass nest;

    public NestInitTest(String name){
        System.out.println("init NestInitTest ...");
        this.nest = new NestClass(this);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNestName() {
        return nest.getName();
    }

    public static void main(String[] args) {
        System.out.println(new NestInitTest("ronry").getNestName());
    }

    class NestClass {

        private String       name;
        private NestInitTest supers;

        NestClass(NestInitTest supers){
            System.out.println("init NestClass ...");
            this.name = supers.getName();
            this.supers = supers;
        }

        public String getName() {
            return name;
        }

        public String getSupersName() {
            return supers.getName();
        }

    }

}
