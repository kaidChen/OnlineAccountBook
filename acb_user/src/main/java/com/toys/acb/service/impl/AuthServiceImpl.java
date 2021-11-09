package com.toys.acb.service.impl;

import com.toys.acb.dao.SysRoleDao;
import com.toys.acb.dao.SysUserDao;
import com.toys.acb.dto.SysUserDto;
import com.toys.acb.entity.SysRole;
import com.toys.acb.entity.SysUser;
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
    public SysUserDto login(String username) {
        try {
            SysUser user  = sysUserDao.getSysUserByUsername(username);
            if (user != null) {
                user.setLoginAt(LocalDateTime.now());
                sysUserDao.updateSysUser(user);

                List<SysRole> sysRoleList = sysRoleDao.getSysRoleListByUserId(user.getId());
                SysUserDto userDto = new SysUserDto();
                return userDto.parseFromPo(user, sysRoleList);
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer updatePassword(Long userId, String newPassword) {
        try {
            String encodePW = passwordEncoder.encode(newPassword);
            return sysUserDao.updatePassword(userId, encodePW);
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
