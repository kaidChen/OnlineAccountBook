package com.toys.acb.service;

import com.toys.acb.entity.SysUser;

public interface AuthService {
    int signup(SysUser sysUser);
    SysUser signin(String username, String password);
    int updatePassword(String oldPW, String newPW, Long userId);
    int updateUserPassword(String username, String password);
}
