package com.toys.acb.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordForm {
    private String username;
    private String oldPassword;
    @Length(min = 8, max = 20)
    private String newPassword;
}
