package com.ronrytest.mongo.util;

import com.mongodb.BasicDBList;

public class DBObjectArrayProxy extends BasicDBList {

	private static final long serialVersionUID = -6537024697431747964L;

	public static DBObjectArrayProxy create(Object[] array) {
        if(array == null) {
            throw new RuntimeException("Passed object is null");
        }

        DBObjectArrayProxy dbObjectArrayProxy = new DBObjectArrayProxy();

        for(Object o : array) {
            dbObjectArrayProxy.add(BaseProxy.passValue(o));
        }

        return dbObjectArrayProxy;
    }
}

