package com.ronrytest.base.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NullValueTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add(null);

        System.out.println(list);

        Map<String, String> map = new HashMap<String, String>();
        map.put(null, null);

        System.out.println(map);
    }

}
