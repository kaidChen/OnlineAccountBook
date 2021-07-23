package com.toys.acb.mapper;

import static com.toys.acb.mapper.TypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.toys.acb.entity.Type;
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
public interface TypeMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, kind, userId, status);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: type")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: type")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Type> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TypeResult")
    Optional<Type> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TypeResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="kind", property="kind", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Type> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int insert(Type record) {
        return MyBatis3Utils.insert(this::insert, record, type, c ->
            c.map(name).toProperty("name")
            .map(kind).toProperty("kind")
            .map(userId).toProperty("userId")
            .map(status).toProperty("status")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int insertSelective(Type record) {
        return MyBatis3Utils.insert(this::insert, record, type, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(kind).toPropertyWhenPresent("kind", record::getKind)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default Optional<Type> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default List<Type> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default List<Type> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default Optional<Type> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, type, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    static UpdateDSL<UpdateModel> updateAllColumns(Type record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(kind).equalTo(record::getKind)
                .set(userId).equalTo(record::getUserId)
                .set(status).equalTo(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Type record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(kind).equalToWhenPresent(record::getKind)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int updateByPrimaryKey(Type record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(kind).equalTo(record::getKind)
            .set(userId).equalTo(record::getUserId)
            .set(status).equalTo(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1471505+08:00", comments="Source Table: type")
    default int updateByPrimaryKeySelective(Type record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(kind).equalToWhenPresent(record::getKind)
            .set(userId).equalToWhenPresent(record::getUserId)
            .set(status).equalToWhenPresent(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }
}