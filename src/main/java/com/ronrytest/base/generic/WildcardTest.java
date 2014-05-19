package com.ronrytest.base.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WildcardTest {

    public static void main(String[] args) {
        // illega Map<String, ?> wildcardMap = new HashMap<String, ? extends Object>();

        Map<String, ? super Object> wildcardMap = new HashMap<String, Object>();
        wildcardMap.put("ss", "ss");

        List<? extends Object> ol = new ArrayList<Object>();
        // ol.add(new Object());can't complie

        ol.contains(new Object()); // 可以调用，因为contain()方法的参数是Object
    }
}
