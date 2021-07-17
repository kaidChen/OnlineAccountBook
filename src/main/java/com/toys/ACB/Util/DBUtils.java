package com.toys.ACB.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> localSqlSession;

    static {
        init();
    }

    private static void init() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("MybatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            localSqlSession = new ThreadLocal<>();
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败，无法创建SqlSessionFactory：", e);
        }
    }

    public static void startSqlSession() {
        localSqlSession.set(sqlSessionFactory.openSession());
    }

    public static SqlSession getSqlSession() {
        return localSqlSession.get();
    }

    public static void commit() {
        localSqlSession.get().commit();
    }

    public static void rollback() {
        localSqlSession.get().rollback();
    }

    public static void close() {
        try {
            localSqlSession.get().close();
        } finally {
            localSqlSession.remove();
        }
    }

    public static <T> T getMapper(Class<T> type) {
        return localSqlSession.get().getMapper(type);
    }
}
