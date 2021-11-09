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

    public List<SysRole> getSysRoleListByUserId(Long sysUserId) {
        SelectStatementProvider stmt = select(sysRole.allColumns())
                .from(sysRole)
                .where(userId, isEqualTo(sysUserId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
           return sysRoleMapper.selectMany(stmt);
        }
    }
}
