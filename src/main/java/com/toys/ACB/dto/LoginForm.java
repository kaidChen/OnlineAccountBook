package com.toys.ACB.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
@ToString
public class LoginForm {
    @NotNull(message = "用户名为空")
    private String username;
    @NotNull(message = "密码为空")
    private String password;
}
