package com.toys.acb.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDate;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BillDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7316256+08:00", comments="Source Table: bill")
    public static final Bill bill = new Bill();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7472344+08:00", comments="Source field: bill.id")
    public static final SqlColumn<Long> id = bill.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7477483+08:00", comments="Source field: bill.user_id")
    public static final SqlColumn<Long> userId = bill.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.74816+08:00", comments="Source field: bill.type_id")
    public static final SqlColumn<Long> typeId = bill.typeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source field: bill.created_at")
    public static final SqlColumn<LocalDate> createdAt = bill.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source field: bill.cost")
    public static final SqlColumn<BigDecimal> cost = bill.cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source field: bill.status")
    public static final SqlColumn<Integer> status = bill.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source field: bill.note")
    public static final SqlColumn<String> note = bill.note;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7316256+08:00", comments="Source Table: bill")
    public static final class Bill extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> typeId = column("type_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDate> createdAt = column("created_at", JDBCType.DATE);

        public final SqlColumn<BigDecimal> cost = column("cost", JDBCType.DECIMAL);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public Bill() {
            super("bill");
        }
    }
}