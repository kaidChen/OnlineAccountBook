package com.toys.acb.service;

import com.toys.acb.dto.SysUserDto;
import com.toys.acb.entity.SysUser;

public interface AuthService {
    int signup(SysUser sysUser);

    SysUserDto login(String username);

    int updatePassword(String oldPW, String newPW, Long userId);

    int updateUserPassword(String username, String password);

    boolean verifyPassword(CharSequence rawPassword, String encodedPassword);
}
