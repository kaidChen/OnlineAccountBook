package com.toys.acb.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SysUserDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    public static final SysUser sysUser = new SysUser();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.id")
    public static final SqlColumn<Long> id = sysUser.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.username")
    public static final SqlColumn<String> username = sysUser.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.nickname")
    public static final SqlColumn<String> nickname = sysUser.nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.password")
    public static final SqlColumn<String> password = sysUser.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.status")
    public static final SqlColumn<Integer> status = sysUser.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source field: sys_user.cycle")
    public static final SqlColumn<Long> cycle = sysUser.cycle;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    public static final class SysUser extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Long> cycle = column("cycle", JDBCType.BIGINT);

        public SysUser() {
            super("sys_user");
        }
    }
}