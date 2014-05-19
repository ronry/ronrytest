package com.ronrytest.mongo.util;

import com.mongodb.DBObject;
import net.sf.cglib.proxy.Enhancer;

public class BeanEnhancer {
    public static Object create(Class clazz, final DBObject source) {
        if(clazz == null || source == null) {
            throw new RuntimeException("One of parameters is null");
        }

        final BeanDescription beanDescription = BeanDescription.describe(clazz);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallbackType(BeanMethodInterceptor.class);
        enhancer.setUseFactory(false);
        
        enhancer.setCallback(new BeanMethodInterceptor() {
            @Override
            public DBObject getSource() {
                return source;
            }

            @Override
            public BeanDescription getBeanDescription() {
                return beanDescription;
            }
        });

        

        return enhancer.create();

    }
}
