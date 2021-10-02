package com.toys.acb.service.impl;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.dao.SysRoleDao;
import com.toys.acb.dao.SysUserDao;
import com.toys.acb.dao.SysUserPo;
import com.toys.acb.dto.SysUserDto;
import com.toys.acb.entity.SysUser;
import com.toys.acb.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private SqlSessionBuilder sqlSessionBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysUserDto login(SysUserDto sysUser) {
        try {
            SysUserPo user = new SysUserPo();
            user.setPassword(sysUser.getPassword());
            user.setUsername(sysUser.getUsername());
            SysUserPo userRec = sysUserDao.getSysUser(user);
            if (userRec != null) {
                boolean match = passwordEncoder.matches(sysUser.getPassword(), user.getPassword());
                if (match) {
                    SysUserDto userDto = new SysUserDto();
                    userDto
                }
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updatePassword(SysUserDto sysUser) {
        try {

        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
        return null;
    }
}
