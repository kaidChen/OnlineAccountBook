package com.toys.acb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;

public class Bill implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.user_id")
    private Long userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.type_id")
    private Long typeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.created_at")
    private LocalDate createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.cost")
    private BigDecimal cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.status")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.note")
    private String note;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7296034+08:00", comments="Source Table: bill")
    private static final long serialVersionUID = 1L;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.user_id")
    public Long getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.type_id")
    public Long getTypeId() {
        return typeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.type_id")
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.created_at")
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.created_at")
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.cost")
    public BigDecimal getCost() {
        return cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.cost")
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.status")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.note")
    public String getNote() {
        return note;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7215081+08:00", comments="Source field: bill.note")
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7316256+08:00", comments="Source Table: bill")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", typeId=").append(typeId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", cost=").append(cost);
        sb.append(", status=").append(status);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7316256+08:00", comments="Source Table: bill")
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
        Bill other = (Bill) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-09-28T01:05:17.7316256+08:00", comments="Source Table: bill")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }
}