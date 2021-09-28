package com.toys.acb.dao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUserPo {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime loginAt;
}
