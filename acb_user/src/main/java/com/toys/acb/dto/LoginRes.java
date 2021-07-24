package com.toys.acb.dto;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.entity.SysUser;
import lombok.Data;

@Data
public class LoginRes {
    private SysUser user;
    private Boolean success;
    private String message;
    private Integer code;

    public LoginRes error(ResultCode resultCode) {
        setSuccess(false);
        setCode(resultCode.getCode());
        setMessage(resultCode.getMessage());
        return this;
    }

    public LoginRes code(Integer code) {
        setCode(code);
        return this;
    }

    public LoginRes user(SysUser user) {
        setUser(user);
        return this;
    }

    public LoginRes success(Boolean success) {
        setSuccess(success);
        return this;
    }

    public LoginRes message(String msg) {
        setMessage(msg);
        return this;
    }
}
