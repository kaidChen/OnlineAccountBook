package com.toys.acb.entity;

import java.io.Serializable;
import javax.annotation.Generated;

public class SysUser implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.username")
    private String username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.nickname")
    private String nickname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.password")
    private String password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3887889+08:00", comments="Source field: sys_user.status")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source field: sys_user.cycle")
    private Long cycle;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source Table: sys_user")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.username")
    public String getUsername() {
        return username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.username")
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.nickname")
    public String getNickname() {
        return nickname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3818024+08:00", comments="Source field: sys_user.nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3827995+08:00", comments="Source field: sys_user.password")
    public String getPassword() {
        return password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3887889+08:00", comments="Source field: sys_user.password")
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.3887889+08:00", comments="Source field: sys_user.status")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source field: sys_user.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source field: sys_user.cycle")
    public Long getCycle() {
        return cycle;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source field: sys_user.cycle")
    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source Table: sys_user")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", status=").append(status);
        sb.append(", cycle=").append(cycle);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source Table: sys_user")
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUser other = (SysUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCycle() == null ? other.getCycle() == null : this.getCycle().equals(other.getCycle()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-21T21:12:45.389783+08:00", comments="Source Table: sys_user")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCycle() == null) ? 0 : getCycle().hashCode());
        return result;
    }
}