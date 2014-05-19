/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.ronrytest.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 类GroovyText.java的实现描述：TODO 类实现描述
 * 
 * @author ronry 2012-8-25 下午8:42:33
 */
public class GroovyText {

    public static class Bean {

        public Bean(String name){
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    static int         index = 10000;

    static Set<String> names = Collections.synchronizedSet(new HashSet<String>());

    synchronized static int nextIndext() {
        return index--;
    }

    public static void main(String[] args) throws Exception {
        ClassLoader parent = GroovyText.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);

        // run groovy
        GroovyShell gs = new GroovyShell(parent);

        long time2 = 0;
        long time3 = 0;

        Script script = gs.parse(new File("/home/ronry/baz.groovy"));
        long time = System.currentTimeMillis();
        // for (int i = 1; i <= 1; i++) {
        // script.setProperty("bean", new Bean("ronry" + i));
        // script.run();
        // }
        // time2 = System.currentTimeMillis() - time;

        Class<?> newClazz = loader.parseClass(new File("/home/ronry/baz2.groovy"));
        final GroovyObject obj = (GroovyObject) newClazz.newInstance();

        time = System.currentTimeMillis();
        for (int i = 1; i <= 1; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Bean bean = new Bean("xxx" + nextIndext());
                    String name = (String) obj.invokeMethod("foo", new Object[] { bean });
                    names.add(name);
                    // try {
                    // Thread.sleep(10000);
                    // } catch (InterruptedException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    if (!name.equals(bean.getName())) {
                        throw new RuntimeException(name + " " + bean.getName());
                    }
                }
            }).start();
        }
        time3 = System.currentTimeMillis() - time;

        // Thread.sleep(100000);
        // System.out.println(names.size());

        // Class<?> groovyClass = loader.parseClass(new File(
        // "/home/ronry/projects/offer/2.0/offer.core.parent/offer.core.biz/src/main/java/com/alibaba/china/offer/core/biz/permission/condition/baz3.groovy"));
        // IFoo foo = (IFoo) groovyClass.newInstance();
        // System.out.println(foo.foo(new Bean("yyyy")));

        // System.out.println(time2);
        // System.out.println(time3);
    }
}
