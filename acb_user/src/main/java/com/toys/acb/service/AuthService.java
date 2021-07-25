package com.toys.acb.service;

import com.toys.acb.entity.SysUser;

public interface AuthService {
    int signup(SysUser sysUser);

    Long login(String username);

    int updatePassword(String oldPW, String newPW, Long userId);

    int updateUserPassword(String username, String password);
}
