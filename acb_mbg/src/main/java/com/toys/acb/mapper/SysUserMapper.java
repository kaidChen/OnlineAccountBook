package com.toys.acb.mapper;

import static com.toys.acb.mapper.SysUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.toys.acb.entity.SysUser;
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
public interface SysUserMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, nickname, password, status, cycle);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysUser> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysUserResult")
    Optional<SysUser> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysUserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="cycle", property="cycle", jdbcType=JdbcType.BIGINT)
    })
    List<SysUser> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int insert(SysUser record) {
        return MyBatis3Utils.insert(this::insert, record, sysUser, c ->
            c.map(username).toProperty("username")
            .map(nickname).toProperty("nickname")
            .map(password).toProperty("password")
            .map(status).toProperty("status")
            .map(cycle).toProperty("cycle")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int insertSelective(SysUser record) {
        return MyBatis3Utils.insert(this::insert, record, sysUser, c ->
            c.map(username).toPropertyWhenPresent("username", record::getUsername)
            .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
            .map(password).toPropertyWhenPresent("password", record::getPassword)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(cycle).toPropertyWhenPresent("cycle", record::getCycle)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default Optional<SysUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default List<SysUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default List<SysUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default Optional<SysUser> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    static UpdateDSL<UpdateModel> updateAllColumns(SysUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalTo(record::getUsername)
                .set(nickname).equalTo(record::getNickname)
                .set(password).equalTo(record::getPassword)
                .set(status).equalTo(record::getStatus)
                .set(cycle).equalTo(record::getCycle);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalToWhenPresent(record::getUsername)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(cycle).equalToWhenPresent(record::getCycle);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int updateByPrimaryKey(SysUser record) {
        return update(c ->
            c.set(username).equalTo(record::getUsername)
            .set(nickname).equalTo(record::getNickname)
            .set(password).equalTo(record::getPassword)
            .set(status).equalTo(record::getStatus)
            .set(cycle).equalTo(record::getCycle)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.1315296+08:00", comments="Source Table: sys_user")
    default int updateByPrimaryKeySelective(SysUser record) {
        return update(c ->
            c.set(username).equalToWhenPresent(record::getUsername)
            .set(nickname).equalToWhenPresent(record::getNickname)
            .set(password).equalToWhenPresent(record::getPassword)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(cycle).equalToWhenPresent(record::getCycle)
            .where(id, isEqualTo(record::getId))
        );
    }
}