package com.toys.acb.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysUserDto {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime loginAt;
    private List<SysRoleDto> roleList;
}

