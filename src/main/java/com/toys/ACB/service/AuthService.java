package com.toys.ACB.service;

import com.toys.ACB.entity.SysUser;


public interface AuthService {
    SysUser register(String username, String password) throws Exception;
    SysUser login(String username, String password) throws Exception;
    Boolean logout(String username) throws Exception;
}
