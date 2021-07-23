package com.toys.acb.mapper;

import static com.toys.acb.mapper.BillDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.toys.acb.entity.Bill;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface BillMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    BasicColumn[] selectList = BasicColumn.columnList(id, cost, time, note, typeId, userId, cycle, source);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Bill> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BillResult")
    Optional<Bill> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BillResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cost", property="cost", jdbcType=JdbcType.DECIMAL),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="cycle", property="cycle", jdbcType=JdbcType.BIGINT),
        @Result(column="source", property="source", jdbcType=JdbcType.INTEGER)
    })
    List<Bill> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int insert(Bill record) {
        return MyBatis3Utils.insert(this::insert, record, bill, c ->
            c.map(cost).toProperty("cost")
            .map(time).toProperty("time")
            .map(note).toProperty("note")
            .map(typeId).toProperty("typeId")
            .map(userId).toProperty("userId")
            .map(cycle).toProperty("cycle")
            .map(source).toProperty("source")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int insertSelective(Bill record) {
        return MyBatis3Utils.insert(this::insert, record, bill, c ->
            c.map(cost).toPropertyWhenPresent("cost", record::getCost)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(note).toPropertyWhenPresent("note", record::getNote)
            .map(typeId).toPropertyWhenPresent("typeId", record::getTypeId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(cycle).toPropertyWhenPresent("cycle", record::getCycle)
            .map(source).toPropertyWhenPresent("source", record::getSource)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default Optional<Bill> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default List<Bill> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default List<Bill> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default Optional<Bill> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    static UpdateDSL<UpdateModel> updateAllColumns(Bill record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cost).equalTo(record::getCost)
                .set(time).equalTo(record::getTime)
                .set(note).equalTo(record::getNote)
                .set(typeId).equalTo(record::getTypeId)
                .set(userId).equalTo(record::getUserId)
                .set(cycle).equalTo(record::getCycle)
                .set(source).equalTo(record::getSource);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Bill record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cost).equalToWhenPresent(record::getCost)
                .set(time).equalToWhenPresent(record::getTime)
                .set(note).equalToWhenPresent(record::getNote)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(cycle).equalToWhenPresent(record::getCycle)
                .set(source).equalToWhenPresent(record::getSource);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int updateByPrimaryKey(Bill record) {
        return update(c ->
            c.set(cost).equalTo(record::getCost)
            .set(time).equalTo(record::getTime)
            .set(note).equalTo(record::getNote)
            .set(typeId).equalTo(record::getTypeId)
            .set(userId).equalTo(record::getUserId)
            .set(cycle).equalTo(record::getCycle)
            .set(source).equalTo(record::getSource)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1159104+08:00", comments="Source Table: bill")
    default int updateByPrimaryKeySelective(Bill record) {
        return update(c ->
            c.set(cost).equalToWhenPresent(record::getCost)
            .set(time).equalToWhenPresent(record::getTime)
            .set(note).equalToWhenPresent(record::getNote)
            .set(typeId).equalToWhenPresent(record::getTypeId)
            .set(userId).equalToWhenPresent(record::getUserId)
            .set(cycle).equalToWhenPresent(record::getCycle)
            .set(source).equalToWhenPresent(record::getSource)
            .where(id, isEqualTo(record::getId))
        );
    }
}