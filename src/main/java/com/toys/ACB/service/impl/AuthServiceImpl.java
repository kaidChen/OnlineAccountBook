package com.toys.ACB.service.impl;

import com.toys.ACB.component.SqlSessionBuilder;
import com.toys.ACB.entity.SysUser;
import com.toys.ACB.mapper.SysUserMapper;
import com.toys.ACB.service.AuthService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static com.toys.ACB.mapper.SysUserDynamicSqlSupport.sysUser;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Override
    public SysUser register(String username, String password) {
        return null;
    }

    @Override
    public SysUser login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }

        if (!userDetails.isEnabled()) {
            throw new DisabledException("账号不可用");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        SysUser selectUser;
        try (SqlSession sqlSession = sqlSessionBuilder.getSqlSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            selectUser = sysUserMapper.selectOne(
                    select(sysUser.id, sysUser.cycle, sysUser.nickname)
                            .from(sysUser)
                            .build()
                            .render(RenderingStrategies.MYBATIS3)
            ).orElseThrow();

            selectUser.setId(null);
            return selectUser;
        }
    }

    @Override
    public Boolean logout(String username) {
        return null;
    }
}
