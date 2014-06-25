package com.ronrytest.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeterogeneousMap<T> {
    
    private Map<Class<? extends T>, List<? extends T>> map = new HashMap<Class<? extends T>, List<? extends T>>(); ;

    public void add(Class<? extends T> clazz, List<? extends T> value) {
        map.put(clazz, value);
    }

    public List<? extends T> get(Class<? extends T> clazz) {
        return map.get(clazz);
    }

    public static void main(String[] args) {
        HeterogeneousMap<Number> map = new HeterogeneousMap<Number>();

        map.add(Integer.class, Arrays.asList(1));
        map.add(Double.class, Arrays.asList(1.2));

        System.out.println(map.get(Integer.class));
    }

}
