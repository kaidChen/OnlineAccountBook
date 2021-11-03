package com.toys.acb.service.impl;

import com.toys.acb.component.SqlSessionBuilder;
import com.toys.acb.dao.SysRoleDao;
import com.toys.acb.dao.SysRolePo;
import com.toys.acb.dao.SysUserDao;
import com.toys.acb.dao.SysUserPo;
import com.toys.acb.dto.SysUserDto;
import com.toys.acb.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

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
            user.setUsername(sysUser.getUsername());
            user = sysUserDao.getSysUser(user);
            if (user != null) {
                user.setLoginAt(LocalDateTime.now());

                SysRolePo roles = new SysRolePo();
                roles.setUserId(user.getId());
                List<SysRolePo> sysRoleList = sysRoleDao.getSysRoleList(roles);

                SysUserDto userDto = new SysUserDto();
                userDto.parseFromPo(user, sysRoleList);

                sysUserDao.updateSysUser(user);

                return userDto;
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updatePassword(SysUserDto sysUser) {
        try {
            String encodePW = passwordEncoder.encode(sysUser.getPassword());
            SysUserPo userForUpdate = new SysUserPo();
            userForUpdate.setId(sysUser.getId());
            userForUpdate.setPassword(encodePW);
            return sysUserDao.updateSysUser(userForUpdate);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean matchPassword(String rawWord, String encodedWord) {
        return passwordEncoder.matches(rawWord, encodedWord);
    }
}
