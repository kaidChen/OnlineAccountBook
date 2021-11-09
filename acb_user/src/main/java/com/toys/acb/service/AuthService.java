package com.toys.acb.service;

import com.toys.acb.dto.SysUserDto;

public interface AuthService {
    SysUserDto login(String username);

    Integer updatePassword(Long userId, String newPassword);

    Boolean matchPassword(String rawWord, String encodedWord);
}
