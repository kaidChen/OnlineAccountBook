package com.toys.acb.mapper;

import static com.toys.acb.mapper.SysUserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.toys.acb.entity.SysUserRole;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
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
public interface SysUserRoleMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    BasicColumn[] selectList = BasicColumn.columnList(userId, roleId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3788108+08:00", comments="Source Table: sys_user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3788108+08:00", comments="Source Table: sys_user_role")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3788108+08:00", comments="Source Table: sys_user_role")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SysUserRole> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3788108+08:00", comments="Source Table: sys_user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysUserRoleResult")
    Optional<SysUserRole> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3788108+08:00", comments="Source Table: sys_user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysUserRoleResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT, id=true)
    })
    List<SysUserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    default int deleteByPrimaryKey(Long userId_, Long roleId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
            .and(roleId, isEqualTo(roleId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    default int insert(SysUserRole record) {
        return MyBatis3Utils.insert(this::insert, record, sysUserRole, c ->
            c.map(userId).toProperty("userId")
            .map(roleId).toProperty("roleId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3798077+08:00", comments="Source Table: sys_user_role")
    default int insertSelective(SysUserRole record) {
        return MyBatis3Utils.insert(this::insert, record, sysUserRole, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    default Optional<SysUserRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    default List<SysUserRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    default List<SysUserRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysUserRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    static UpdateDSL<UpdateModel> updateAllColumns(SysUserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(roleId).equalTo(record::getRoleId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3808155+08:00", comments="Source Table: sys_user_role")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysUserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(roleId).equalToWhenPresent(record::getRoleId);
    }
}