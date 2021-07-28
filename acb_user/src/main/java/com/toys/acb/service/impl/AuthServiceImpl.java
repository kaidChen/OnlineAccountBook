package com.toys.acb.service.impl;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.dto.UserDto;
import com.toys.acb.entity.SysRole;
import com.toys.acb.entity.SysUser;
import com.toys.acb.entity.SysUserRole;
import com.toys.acb.mapper.SysRoleMapper;
import com.toys.acb.mapper.SysUserMapper;
import com.toys.acb.mapper.SysUserRoleMapper;
import com.toys.acb.service.AuthService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.SysRoleDynamicSqlSupport.sysRole;
import static com.toys.acb.mapper.SysUserDynamicSqlSupport.sysUser;
import static com.toys.acb.mapper.SysUserRoleDynamicSqlSupport.sysUserRole;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class AuthServiceImpl implements AuthService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int signup(SysUser sysUser) {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserRoleMapper sysUserRoleMapper = sqlSession.getMapper(SysUserRoleMapper.class);
            int rows = sysUserMapper.insertSelective(sysUser);
            rows += sysUserRoleMapper.insert(
                    (SysUserRole) insertInto(sysUserRole)
                            .set(sysUserRole.userId).toValue(sysUser.getId())
                            .set(sysUserRole.roleId).toValue(DbCode.ROLE_ADMIN.getCode().longValue())
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );
            LOGGER.info("create a new user: username={}", sysUser.getUsername());
            return rows;
        } catch (Exception e) {
            LOGGER.error("error at signup: err:{}", e.getMessage());
        }
        return -1;
    }

    @Override
    public UserDto login(String username) {
        SelectStatementProvider selectSysUser = select(sysUser.id, sysUser.password, sysUser.status)
                .from(sysUser)
                .where(sysUser.username, isEqualTo(username))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider selectRoleList = select(sysRole.code)
                .from(sysUserRole)
                .leftJoin(sysRole)
                .on(sysRole.id, equalTo(sysUserRole.roleId))
                .leftJoin(sysUser)
                .on(sysUser.id, equalTo(sysUserRole.userId))
                .where(sysUser.username, isEqualTo(username))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);

            Optional<SysUser> sysUserOptional = sysUserMapper.selectOne(selectSysUser);
            SysUser curSysUser = sysUserOptional.orElse(null);
            if (curSysUser == null) {
                throw new UsernameNotFoundException(String.format("用户不存在：%s", username));
            }

            List<SysRole> sysRoleList = sysRoleMapper.selectMany(selectRoleList);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (SysRole role : sysRoleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            }

            UserDto userDto = new UserDto(
                    new User(username, curSysUser.getPassword(),
                            curSysUser.getStatus().equals(DbCode.USER_STATUS_UNLOCKED.getCode()),
                            true, true, true, authorities),
                    curSysUser.getId()
            );

            LOGGER.info("login success：username={}", username);
            return userDto;
        } catch (Exception e) {
            LOGGER.error("error at login: username={}, err:{}", username, e.getMessage());
        }
        return null;
    }

    @Override
    public int updatePassword(String oldPW, String newPW, Long userId) {
        SelectStatementProvider render = select(sysUser.username, sysUser.password)
                .from(sysUser)
                .where(sysUser.id, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Optional<SysUser> sysUser = sysUserMapper.selectOne(render);
            SysUser user = sysUser.orElse(null);
            if (user == null) {
                LOGGER.info("user not exists: user_id={}", userId);
                return -2;
            }

            if (!passwordEncoder.matches(oldPW, user.getPassword())) {
                LOGGER.info("password errror：user_id={}", userId);
                return -3;
            }

            return updateUserPassword(user.getUsername(), newPW);
        } catch (Exception e) {
            LOGGER.error("error at updatePassword: user_id={}, err:{}", userId, e.getMessage());
        }
        return -1;
    }

    @Override
    public int updateUserPassword(String username, String password) {
        UpdateStatementProvider render = update(sysUser)
                .set(sysUser.password).equalTo(passwordEncoder.encode(password))
                .where(sysUser.username, isEqualTo(username))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        int rows = -1;

        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            rows = sysUserMapper.update(render);
            LOGGER.info("updateUserPassword: username={}", username);
        } catch (Exception e) {
            LOGGER.error("error at updateUserPassword: username={}, err:{}", username, e.getMessage());
        }
        return rows;
    }

    @Override
    public boolean verifyPassword(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
