package com.ronrytest.ibatis;

public class User2 {

    private String name;
    private String pwd;

    public User2(){

    }

    public User2(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
