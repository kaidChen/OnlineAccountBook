package com.toys.acb.service.impl;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.entity.SysUser;
import com.toys.acb.mapper.SysUserMapper;
import com.toys.acb.service.AuthService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.toys.acb.mapper.SysUserDynamicSqlSupport.sysUser;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Override
    public Integer signup(SysUser sysUser) {
        return 0;
    }

    @Override
    public Long login(String username) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            Optional<SysUser> sysUserOptional = sysUserMapper.selectOne(
                    select(sysUser.id)
                            .from(sysUser)
                            .where(sysUser.username, isEqualTo(username))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );

            SysUser user = sysUserOptional.orElse(null);
            if (user == null) {
                LOGGER.info("用户不存在：{}", username);
                return null;
            }

            LOGGER.info("用户登录成功：username={}", username);
            return user.getId();
        } catch (Exception e) {
            LOGGER.error("error at login: username={}, err:{}", username, e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updatePassword(String oldPW, String newPW, Long userId) {
        return 0;
    }

    @Override
    public Integer updateUserPassword(String username, String password) {
        return 0;
    }
}
