package com.toys.acb.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginReq {
    @Length(min = 6, max = 20, message = "用户名长度[6~20]")
    private String username;
    @Length(min = 6, max = 30, message = "密码长度[6~30]")
    private String password;
}
