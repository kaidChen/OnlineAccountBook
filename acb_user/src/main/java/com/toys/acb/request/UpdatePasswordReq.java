package com.toys.acb.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdatePasswordReq {
    private String oldPassword;
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$",
            message = "8~30个字符，至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符"
    )
    private String newPassword;
}
