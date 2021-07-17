package com.toys.ACB.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SysRoleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.6984327+08:00", comments="Source Table: sys_role")
    public static final SysRole sysRole = new SysRole();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.6984327+08:00", comments="Source field: sys_role.id")
    public static final SqlColumn<Long> id = sysRole.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.6984327+08:00", comments="Source field: sys_role.code")
    public static final SqlColumn<String> code = sysRole.code;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.6984327+08:00", comments="Source field: sys_role.name")
    public static final SqlColumn<String> name = sysRole.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.6984327+08:00", comments="Source Table: sys_role")
    public static final class SysRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public SysRole() {
            super("sys_role");
        }
    }
}