package com.toys.acb.mapper;

import static com.toys.acb.mapper.BillTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.toys.acb.entity.BillType;
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
public interface BillTypeMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, name, kind, status);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<BillType> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BillTypeResult")
    Optional<BillType> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BillTypeResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<BillType> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int insert(BillType record) {
        return MyBatis3Utils.insert(this::insert, record, billType, c ->
            c.map(userId).toProperty("userId")
            .map(name).toProperty("name")
            .map(kind).toProperty("kind")
            .map(status).toProperty("status")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int insertSelective(BillType record) {
        return MyBatis3Utils.insert(this::insert, record, billType, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(kind).toPropertyWhenPresent("kind", record::getKind)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default Optional<BillType> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default List<BillType> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default List<BillType> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default Optional<BillType> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, billType, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    static UpdateDSL<UpdateModel> updateAllColumns(BillType record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(name).equalTo(record::getName)
                .set(kind).equalTo(record::getKind)
                .set(status).equalTo(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BillType record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(name).equalToWhenPresent(record::getName)
                .set(kind).equalToWhenPresent(record::getKind)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int updateByPrimaryKey(BillType record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(name).equalTo(record::getName)
            .set(kind).equalTo(record::getKind)
            .set(status).equalTo(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7923393+08:00", comments="Source Table: bill_type")
    default int updateByPrimaryKeySelective(BillType record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(name).equalToWhenPresent(record::getName)
            .set(kind).equalToWhenPresent(record::getKind)
            .set(status).equalToWhenPresent(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }
}