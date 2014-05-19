package com.ronrytest.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IbatisUserInfoDAO extends SqlMapClientDaoSupport {

    public void insertWithParamMap(User user) {
        this.getSqlMapClientTemplate().insert("insert-withparammap", user);
    }

    public void insertWithErrorType() {
        User2 user = new User2("root2", "12345");
        this.getSqlMapClientTemplate().insert("insert-withparammap", user);
    }

    public void insert(User user) {
        this.getSqlMapClientTemplate().insert("insert", user);
    }

}
