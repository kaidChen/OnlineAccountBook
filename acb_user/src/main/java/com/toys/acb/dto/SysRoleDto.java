package com.toys.acb.dto;

import com.toys.acb.entity.SysRole;
import lombok.Data;

@Data
public class SysRoleDto {
    private Long id;
    private Long userId;
    private String code;
    private String name;

    public SysRoleDto parseFromPo(SysRole role) {
        if (role != null) {
            setId(role.getId());
            setUserId(role.getUserId());
            setCode(role.getCode());
            setName(role.getName());
        }
        return this;
    }
}
