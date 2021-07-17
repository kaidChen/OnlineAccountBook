package com.toys.ACB.entity;

import java.io.Serializable;
import javax.annotation.Generated;

public class Type implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.kind")
    private Integer kind;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.user_id")
    private Long userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.status")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8073473+08:00", comments="Source Table: type")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.name")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.kind")
    public Integer getKind() {
        return kind;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.kind")
    public void setKind(Integer kind) {
        this.kind = kind;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.user_id")
    public Long getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.status")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8023275+08:00", comments="Source field: type.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8073473+08:00", comments="Source Table: type")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", kind=").append(kind);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8093988+08:00", comments="Source Table: type")
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
        Type other = (Type) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getKind() == null ? other.getKind() == null : this.getKind().equals(other.getKind()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-07-17T13:51:25.8093988+08:00", comments="Source Table: type")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getKind() == null) ? 0 : getKind().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}