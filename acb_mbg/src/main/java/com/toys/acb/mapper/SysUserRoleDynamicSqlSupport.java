package com.toys.acb.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SysUserRoleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: sys_user_role")
    public static final SysUserRole sysUserRole = new SysUserRole();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: sys_user_role.user_id")
    public static final SqlColumn<Long> userId = sysUserRole.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: sys_user_role.role_id")
    public static final SqlColumn<Long> roleId = sysUserRole.roleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: sys_user_role")
    public static final class SysUserRole extends SqlTable {
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public SysUserRole() {
            super("sys_user_role");
        }
    }
}