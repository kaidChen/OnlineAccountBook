package com.toys.acb.component;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component("sqlSessionBuilder")
public class SqlSessionBuilder {
    private final SqlSessionFactory sqlSessionFactory;

    public SqlSessionBuilder() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("MybatisConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
