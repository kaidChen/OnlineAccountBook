package com.toys.acb.mapper;

import com.toys.acb.dto.BillDetail;
import com.toys.acb.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

@Mapper
public interface BillDetailMapper {
    @Results(id = "BillDetailResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "cost", property = "cost", jdbcType = JdbcType.DECIMAL),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "note", property = "note", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.BIGINT),
            @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.BIGINT),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type_id", property = "type", javaType = Type.class, one = @One(select = "selectTypeByTypeId")),
    })
    @Select({"select id, cost, time, note, cycle, type_id, user_id from bill where id=#{billId}"})
    BillDetail selectByPrimaryKey(@Param("billId") Long billId);

    @Select({"select name, kind, status from type where id=#{typeId}"})
    Type selectTypeByTypeId(@Param("typeId") Long typeId);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("BillDetailResult")
    List<BillDetail> selectMany(SelectStatementProvider selectStatementProvider);
}