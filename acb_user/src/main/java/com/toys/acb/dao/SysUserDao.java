package com.toys.acb.dao;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.entity.SysUser;
import com.toys.acb.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.toys.acb.mapper.SysUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Component
public class SysUserDao {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    public Integer createSysUser(SysUserPo record) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            return sysUserMapper.insertSelective(record.parseToDbEntity());
        }
    }

    public Integer deleteSysUser(SysUserPo record) {
        DeleteStatementProvider stmt = deleteFrom(sysUser)
                .where(id, isEqualTo(record::getId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            return sysUserMapper.delete(stmt);
        }
    }

    public Integer updateSysUser(SysUserPo record) {
        UpdateStatementProvider stmt = update(sysUser)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(loginAt).equalToWhenPresent(record::getLoginAt)
                .where(id, isEqualTo(record::getId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            return sysUserMapper.update(stmt);
        }
    }

    public SysUserPo getSysUser(SysUserPo record) {
        SelectStatementProvider stmt = select(sysUser.allColumns())
                .from(sysUser)
                .where(username, isEqualTo(record::getUsername))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Optional<SysUser> sysUserOpt = sysUserMapper.selectOne(stmt);
            if (sysUserOpt.isEmpty()) {
                return null;
            }
            SysUser dbRec = sysUserOpt.get();
            return new SysUserPo(dbRec);
        }
    }
}
