package com.toys.ACB.service.impl;

import com.toys.ACB.Util.DBUtils;
import com.toys.ACB.entity.SysRole;
import com.toys.ACB.entity.SysUser;
import com.toys.ACB.mapper.SysRoleMapper;
import com.toys.ACB.mapper.SysUserMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
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

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static com.toys.ACB.mapper.SysUserDynamicSqlSupport.sysUser;
import static com.toys.ACB.mapper.SysRoleDynamicSqlSupport.sysRole;
import static com.toys.ACB.mapper.SysUserRoleDynamicSqlSupport.sysUserRole;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username)  {
        if (username == null || username.equals("")) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        DBUtils.startSqlSession();
        SysUserMapper sysUserMapper = DBUtils.getMapper(SysUserMapper.class);
        SysRoleMapper sysRoleMapper = DBUtils.getMapper(SysRoleMapper.class);

        Optional<SysUser> sysUserOptional = sysUserMapper.selectOne(
                select(sysUser.id, sysUser.username, sysUser.password)
                        .from(sysUser)
                        .where(sysUser.username, isEqualTo(username))
                        .build()
                        .render(RenderingStrategies.MYBATIS3)
        );
        if (sysUserOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("用户不存在：%s", username));
        }

        SysUser curSysUser = sysUserOptional.get();

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
        DBUtils.close();

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : sysRoleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
            authorities.add(authority);
        }

        return new User(curSysUser.getUsername(), curSysUser.getPassword(), authorities);
    }
}
