package com.toys.acb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.toys.acb.constant.DbCode;
import com.toys.acb.entity.SysRole;
import com.toys.acb.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysUserDto {
    private Long id;
    private String username;
    private String nickname;
    private String password;
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

    public SysUserDto parseFromPo(SysUser user, List<SysRole> roles) {
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
            for (SysRole role : roles) {
                SysRoleDto roleDto = new SysRoleDto();
                roleList.add(roleDto.parseFromPo(role));
            }
            if (!roleList.isEmpty()) {
                setRoles(roleList);
            }
        }

        return this;
    }

    public UserDetails createUserDetails() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (SysRoleDto role : getRoles()) {
            GrantedAuthority simple = new SimpleGrantedAuthority(role.getCode());
            authorities.add(simple);
        }

        return new User(getUsername(),
                getPassword(),
                true,
                true,
                true,
                getStatus().equals(DbCode.SysUserStatusUnlocked),
                authorities
        );
    }

    public SysUserDto cloneWithoutSensitivity() {
        SysUserDto user = new SysUserDto();
        user.setUsername(getUsername());
        user.setNickname(getNickname());
        user.setStatus(getStatus());
        user.setRoles(getRoles());
        return user;
    }
}

