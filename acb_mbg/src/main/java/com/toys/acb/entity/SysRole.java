package com.toys.acb.entity;

import java.io.Serializable;
import javax.annotation.Generated;

public class SysRole implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.code")
    private String code;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source Table: sys_role")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.code")
    public String getCode() {
        return code;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.code")
    public void setCode(String code) {
        this.code = code;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source field: sys_role.name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source Table: sys_role")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source Table: sys_role")
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
        SysRole other = (SysRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-23T09:57:40.0846654+08:00", comments="Source Table: sys_role")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }
}