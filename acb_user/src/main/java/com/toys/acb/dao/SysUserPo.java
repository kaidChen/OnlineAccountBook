package com.toys.acb.dao;

import com.toys.acb.entity.SysUser;
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

    public SysUserPo(){
    }

    public SysUserPo(SysUser sysUser) {
        setId(sysUser.getId());
        setUsername(sysUser.getUsername());
        setNickname(sysUser.getNickname());
        setPassword(sysUser.getPassword());
        setStatus(sysUser.getStatus());
        setCreatedAt(sysUser.getCreatedAt());
        setUpdatedAt(sysUser.getUpdatedAt());
        setLoginAt(sysUser.getLoginAt());
    }

    public SysUser parseToDbEntity() {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUsername(username);
        sysUser.setNickname(nickname);
        sysUser.setPassword(password);
        sysUser.setStatus(status);
        sysUser.setCreatedAt(createdAt);
        sysUser.setUpdatedAt(updatedAt);
        sysUser.setLoginAt(loginAt);
        return sysUser;
    }
}
