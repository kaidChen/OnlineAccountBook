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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7576817+08:00", comments="Source Table: bill")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, typeId, createdAt, cost, status, note);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7485595+08:00", comments="Source Table: bill")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.750097+08:00", comments="Source Table: bill")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Bill> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.750097+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BillResult")
    Optional<Bill> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.750097+08:00", comments="Source Table: bill")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BillResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.BIGINT),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.DATE),
        @Result(column="cost", property="cost", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<Bill> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7521465+08:00", comments="Source Table: bill")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7521465+08:00", comments="Source Table: bill")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7571636+08:00", comments="Source Table: bill")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7571636+08:00", comments="Source Table: bill")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7571636+08:00", comments="Source Table: bill")
    default int insert(Bill record) {
        return MyBatis3Utils.insert(this::insert, record, bill, c ->
            c.map(userId).toProperty("userId")
            .map(typeId).toProperty("typeId")
            .map(createdAt).toProperty("createdAt")
            .map(cost).toProperty("cost")
            .map(status).toProperty("status")
            .map(note).toProperty("note")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7571636+08:00", comments="Source Table: bill")
    default int insertSelective(Bill record) {
        return MyBatis3Utils.insert(this::insert, record, bill, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(typeId).toPropertyWhenPresent("typeId", record::getTypeId)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(cost).toPropertyWhenPresent("cost", record::getCost)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(note).toPropertyWhenPresent("note", record::getNote)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7576817+08:00", comments="Source Table: bill")
    default Optional<Bill> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7576817+08:00", comments="Source Table: bill")
    default List<Bill> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7576817+08:00", comments="Source Table: bill")
    default List<Bill> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7585776+08:00", comments="Source Table: bill")
    default Optional<Bill> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7585776+08:00", comments="Source Table: bill")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, bill, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7585776+08:00", comments="Source Table: bill")
    static UpdateDSL<UpdateModel> updateAllColumns(Bill record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(typeId).equalTo(record::getTypeId)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(cost).equalTo(record::getCost)
                .set(status).equalTo(record::getStatus)
                .set(note).equalTo(record::getNote);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7585776+08:00", comments="Source Table: bill")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Bill record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(cost).equalToWhenPresent(record::getCost)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(note).equalToWhenPresent(record::getNote);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7602561+08:00", comments="Source Table: bill")
    default int updateByPrimaryKey(Bill record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(typeId).equalTo(record::getTypeId)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(cost).equalTo(record::getCost)
            .set(status).equalTo(record::getStatus)
            .set(note).equalTo(record::getNote)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7612543+08:00", comments="Source Table: bill")
    default int updateByPrimaryKeySelective(Bill record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(typeId).equalToWhenPresent(record::getTypeId)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(cost).equalToWhenPresent(record::getCost)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(note).equalToWhenPresent(record::getNote)
            .where(id, isEqualTo(record::getId))
        );
    }
}