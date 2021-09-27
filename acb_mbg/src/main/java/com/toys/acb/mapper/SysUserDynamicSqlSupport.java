package com.toys.acb.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SysUserDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7668601+08:00", comments="Source Table: sys_user")
    public static final SysUser sysUser = new SysUser();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7703776+08:00", comments="Source field: sys_user.id")
    public static final SqlColumn<Long> id = sysUser.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7703776+08:00", comments="Source field: sys_user.username")
    public static final SqlColumn<String> username = sysUser.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7703776+08:00", comments="Source field: sys_user.nickname")
    public static final SqlColumn<String> nickname = sysUser.nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.77137+08:00", comments="Source field: sys_user.password")
    public static final SqlColumn<String> password = sysUser.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7723654+08:00", comments="Source field: sys_user.status")
    public static final SqlColumn<Integer> status = sysUser.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7723654+08:00", comments="Source field: sys_user.created_at")
    public static final SqlColumn<LocalDateTime> createdAt = sysUser.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7729524+08:00", comments="Source field: sys_user.updated_at")
    public static final SqlColumn<LocalDateTime> updatedAt = sysUser.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7729524+08:00", comments="Source field: sys_user.login_at")
    public static final SqlColumn<LocalDateTime> loginAt = sysUser.loginAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.767857+08:00", comments="Source Table: sys_user")
    public static final class SysUser extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> loginAt = column("login_at", JDBCType.TIMESTAMP);

        public SysUser() {
            super("sys_user");
        }
    }
}