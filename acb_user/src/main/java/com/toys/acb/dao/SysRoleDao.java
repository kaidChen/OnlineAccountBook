package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.entity.SysRole;
import com.toys.acb.mapper.SysRoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.SysRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class SysRoleDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createSysRole(SysRolePo record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            return sysRoleMapper.insertSelective(record.parseToDbEntity());
        }
    }

    public Integer deleteSysRole(SysRolePo record) {
        DeleteStatementProvider stmt = deleteFrom(sysRole)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            return sysRoleMapper.delete(stmt);
        }
    }

    public Integer updateSysRole(SysRolePo record) {
        UpdateStatementProvider stmt = update(sysRole)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            return sysRoleMapper.update(stmt);
        }
    }

    public SysRolePo getSysRole(SysRolePo record) {
        SelectStatementProvider stmt = select(sysRole.allColumns())
                .from(sysRole)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            Optional<SysRole> sysRoleOpt = sysRoleMapper.selectOne(stmt);
            if (sysRoleOpt.isEmpty()) {
                return null;
            }
            SysRole dbrec = sysRoleOpt.get();
            return new SysRolePo(dbrec);
        }
    }

    public List<SysRolePo> getSysRoleList(SysRolePo record) {
        SelectStatementProvider stmt = select(sysRole.allColumns())
                .from(sysRole)
                .where(id, isEqualTo(record::getId),
                        and(userId, isEqualTo(record::getUserId)),
                        and(code, isEqualToWhenPresent(record::getCode)),
                        and(name, isEqualToWhenPresent(record::getName)))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = sysRoleMapper.selectMany(stmt);
            List<SysRolePo> sysRoleList = new ArrayList<>();
            sysRoles.forEach(rec -> sysRoleList.add(new SysRolePo(rec)));
            return sysRoleList;
        }
    }
}
