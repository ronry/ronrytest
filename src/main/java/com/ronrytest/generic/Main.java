package com.ronrytest.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型只是编译期的概念，虚拟机中并没有泛型
 * 
 * @author jinliang.lvjl 2014-5-29 上午11:18:04
 */
public class Main {

    public static void main(String[] args) {

        // 1 List<String>和List<Object>没有任何父子关系
        // List<?>可以指向任何的List,但是它是只读的:因为它表示的是我是某种类型（而且只能是这种类型）的集合，但是现在我不知道是什么类型（只有运行时知道），所以安全起见，编译期不允许写
        List<String> ls = new ArrayList<String>();
        // ls.add(new Object()); //Error

        // List<Object> lo = ls; //Error
        List<?> lWildcard = ls;

        List<Object> lo = new ArrayList<Object>();
        lo.add(new String());
        // ls = lo; //Error

        fromArrayToCollection(new String[] {}, lo); // OK
        // fromArrayToCollection(new Object[] {}, ls); // Error

        // fromArrayToCollection(ls, lo); // Error

    }

    /**
     * 数组里的类型必须是Collection里的类型的子类型;
     * 
     * @param a
     * @param c
     */
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // correct
        }
    }

    /**
     * 两个Collection必须是同一个类型的
     * 
     * @param a
     * @param c
     */
    static <T> void fromArrayToCollection(Collection<T> a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // correct
        }
    }

}
