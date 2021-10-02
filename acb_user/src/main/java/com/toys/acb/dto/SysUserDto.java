package com.toys.acb.dto;

import com.toys.acb.dao.SysRolePo;
import com.toys.acb.dao.SysUserPo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysUserDto {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String newPassw;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime loginAt;
    private List<SysRoleDto> roles;

    public void parseFromPo(SysUserPo user, List<SysRolePo> roles) {
        if (user != null) {
            setId(user.getId());
            setUsername(user.getUsername());
            setNickname(user.getNickname());
            setPassword(user.getPassword());
            setStatus(user.getStatus());
            setCreatedAt(user.getCreatedAt());
            setUpdatedAt(user.getUpdatedAt());
            setLoginAt(user.getLoginAt());
            setRoles(null);

            List<SysRoleDto> roleList = new ArrayList<>();
            for (SysRolePo role : roles) {
                SysRoleDto roleDto = new SysRoleDto();
                roleDto.parseFromPo(role);
                roleList.add(roleDto);
            }
            if (roleList != null) {
                setRoles(roleList);
            }
        }
    }
}

