package com.toys.acb.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BillTypeDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source Table: bill_type")
    public static final BillType billType = new BillType();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source field: bill_type.id")
    public static final SqlColumn<Long> id = billType.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source field: bill_type.user_id")
    public static final SqlColumn<Long> userId = billType.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source field: bill_type.name")
    public static final SqlColumn<String> name = billType.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source field: bill_type.kind")
    public static final SqlColumn<Integer> kind = billType.kind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source field: bill_type.status")
    public static final SqlColumn<Integer> status = billType.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7902928+08:00", comments="Source Table: bill_type")
    public static final class BillType extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> kind = column("kind", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public BillType() {
            super("bill_type");
        }
    }
}