package com.toys.acb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    private List<SysRoleDto> roles;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime loginAt;

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
            if (!roleList.isEmpty()) {
                setRoles(roleList);
            }
        }
    }
}

