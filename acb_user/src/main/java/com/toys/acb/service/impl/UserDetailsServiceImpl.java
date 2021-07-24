package com.toys.acb.service.impl;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.constant.DbCode;
import com.toys.acb.entity.SysRole;
import com.toys.acb.entity.SysUser;
import com.toys.acb.mapper.SysRoleMapper;
import com.toys.acb.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toys.acb.mapper.SysRoleDynamicSqlSupport.sysRole;
import static com.toys.acb.mapper.SysUserDynamicSqlSupport.sysUser;
import static com.toys.acb.mapper.SysUserRoleDynamicSqlSupport.sysUserRole;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);

            Optional<SysUser> sysUserOptional = sysUserMapper.selectOne(
                    select(sysUser.id, sysUser.username, sysUser.password, sysUser.status)
                            .from(sysUser)
                            .where(sysUser.username, isEqualTo(username))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );
            SysUser curSysUser = sysUserOptional.orElse(null);
            if (curSysUser == null) {
                throw new UsernameNotFoundException(String.format("用户不存在：%s", username));
            }

            List<SysRole> sysRoleList = sysRoleMapper.selectMany(
                    select(sysRole.code)
                            .from(sysUserRole)
                            .leftJoin(sysRole)
                            .on(sysRole.id, equalTo(sysUserRole.roleId))
                            .leftJoin(sysUser)
                            .on(sysUser.id, equalTo(sysUserRole.userId))
                            .where(sysUser.username, isEqualTo(username))
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            );

            List<GrantedAuthority> authorities = new ArrayList<>();
            for (SysRole role : sysRoleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            }

            return new User(curSysUser.getUsername(), curSysUser.getPassword(), curSysUser.getStatus().equals(DbCode.USER_STATUS_UNLOCKED.getCode()),
                    true, true, true, authorities);
        }
    }
}
