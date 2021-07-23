package com.toys.acb.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TypeDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: type")
    public static final Type type = new Type();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: type.id")
    public static final SqlColumn<Long> id = type.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: type.name")
    public static final SqlColumn<String> name = type.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: type.kind")
    public static final SqlColumn<Integer> kind = type.kind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: type.user_id")
    public static final SqlColumn<Long> userId = type.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: type.status")
    public static final SqlColumn<Integer> status = type.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: type")
    public static final class Type extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> kind = column("kind", JDBCType.INTEGER);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public Type() {
            super("type");
        }
    }
}