package com.toys.acb.dto;

import com.toys.acb.dao.SysRolePo;
import lombok.Data;

@Data
public class SysRoleDto {
    private Long id;
    private Long userId;
    private String code;
    private String name;

    public void parseFromPo(SysRolePo role) {
        if (role != null) {
            setId(role.getId());
            setUserId(role.getUserId());
            setCode(role.getCode());
            setName(role.getName());
        }
    }
}
