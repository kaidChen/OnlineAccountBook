package com.toys.acb.dao;

import com.toys.acb.entity.SysRole;
import lombok.Data;

@Data
public class SysRolePo {
    private Long id;
    private Long userId;
    private String code;
    private String name;

    public SysRolePo(){
    }

    public SysRolePo(SysRole sysRole) {
        setId(sysRole.getId());
        setUserId(sysRole.getUserId());
        setCode(sysRole.getCode());
        setName(sysRole.getName());
    }

    public SysRole parseToDbEntity() {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setUserId(userId);
        sysRole.setCode(code);
        sysRole.setName(name);
        return sysRole;
    }
}
