package com.toys.acb.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginForm {
    @Length(min = 3, max = 16, message = "用户名长度不合规")
    private String username;
    @Length(min = 3, max = 20, message = "密码长度不合规")
    private String password;
}
