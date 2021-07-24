package com.toys.acb.service.impl;

import com.toys.acb.entity.SysUser;
import com.toys.acb.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public int signup(SysUser sysUser) {
        return 0;
    }

    @Override
    public SysUser signin(String username, String password) {
        return null;
    }

    @Override
    public int updatePassword(String oldPW, String newPW, Long userId) {
        return 0;
    }

    @Override
    public int updateUserPassword(String username, String password) {
        return 0;
    }
}
