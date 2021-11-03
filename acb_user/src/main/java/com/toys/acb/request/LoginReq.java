package com.toys.acb.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginReq {
    @Size(min = 6, max = 20, message = "用户名长度[6~20]")
    private String username;
    @Size(min = 6, max = 30, message = "密码长度[6~30]")
    private String password;
}
