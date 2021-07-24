package com.toys.acb.service;

import com.toys.acb.dto.LoginRes;
import com.toys.acb.entity.SysUser;

public interface AuthService {
    Integer signup(SysUser sysUser);
    Long login(String username);
    Integer updatePassword(String oldPW, String newPW, Long userId);
    Integer updateUserPassword(String username, String password);
}
