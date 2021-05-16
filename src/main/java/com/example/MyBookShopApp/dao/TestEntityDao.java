package com.example.MyBookShopApp.dao;

import com.example.MyBookShopApp.entity.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDao extends AbstractHibernateDao<TestEntity> {
    public TestEntityDao(){
        super();
        setClazz(TestEntity.class);
    }
}
