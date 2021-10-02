package com.toys.acb.service;

import com.toys.acb.dto.SysUserDto;
import com.toys.acb.entity.SysUser;

public interface AuthService {
    SysUserDto login(SysUserDto sysUser);

    Integer updatePassword(SysUserDto sysUser);
}
