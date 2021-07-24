package com.toys.acb.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BillDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    public static final Bill bill = new Bill();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.id")
    public static final SqlColumn<Long> id = bill.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.cost")
    public static final SqlColumn<BigDecimal> cost = bill.cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.time")
    public static final SqlColumn<LocalDateTime> time = bill.time;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.note")
    public static final SqlColumn<String> note = bill.note;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.type_id")
    public static final SqlColumn<Long> typeId = bill.typeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.user_id")
    public static final SqlColumn<Long> userId = bill.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.cycle")
    public static final SqlColumn<Long> cycle = bill.cycle;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source field: bill.source")
    public static final SqlColumn<Integer> source = bill.source;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    public static final class Bill extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> cost = column("cost", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> time = column("time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<Long> typeId = column("type_id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> cycle = column("cycle", JDBCType.BIGINT);

        public final SqlColumn<Integer> source = column("source", JDBCType.INTEGER);

        public Bill() {
            super("bill");
        }
    }
}