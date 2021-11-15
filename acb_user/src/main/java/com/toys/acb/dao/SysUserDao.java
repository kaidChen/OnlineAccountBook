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

    public Integer updateSysUser(SysUser record) {
       try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Integer rows = sysUserMapper.updateByPrimaryKeySelective(record);
           sqlSession.commit();
           return rows;
        }
    }

    public Integer updatePassword(Long recId, String newPW) {
        UpdateStatementProvider stmt = update(sysUser)
                .set(password).equalTo(newPW)
                .where(id, isEqualTo(recId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Integer rows = sysUserMapper.update(stmt);
            sqlSession.commit();
            return rows;
        }
    }

    public SysUser getSysUserByUsername(String recUsername) {
        SelectStatementProvider stmt = select(sysUser.allColumns())
                .from(sysUser)
                .where(username, isEqualTo(recUsername))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Optional<SysUser> sysUserOpt = sysUserMapper.selectOne(stmt);
            return sysUserOpt.orElse(null);
        }
    }
}
