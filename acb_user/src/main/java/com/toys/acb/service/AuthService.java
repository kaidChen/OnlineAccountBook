package com.toys.acb.service;

import com.toys.acb.dto.UserDto;
import com.toys.acb.entity.SysUser;

public interface AuthService {
    int signup(SysUser sysUser);

    UserDto login(String username);

    int updatePassword(String oldPW, String newPW, Long userId);

    int updateUserPassword(String username, String password);

    boolean verifyPassword(CharSequence rawPassword, String encodedPassword);
}
