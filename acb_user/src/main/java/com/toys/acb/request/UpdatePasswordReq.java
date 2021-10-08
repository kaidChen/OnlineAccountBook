package com.toys.acb.request;

import lombok.Data;

@Data
public class UpdatePasswordReq {
    private String oldPassword;
    private String newPassword;
}
