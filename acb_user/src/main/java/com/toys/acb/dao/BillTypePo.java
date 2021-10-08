package com.toys.acb.dao;

import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.entity.BillType;
import lombok.Data;

@Data
public class BillTypePo {
    private Long id;
    private Long userId;
    private String name;
    private Integer kind;
    private Integer status;

    public BillTypePo(){
    }

    public BillTypePo(BillType billType) {
        setId(billType.getId());
        setUserId(billType.getUserId());
        setName(billType.getName());
        setKind(billType.getKind());
        setStatus(billType.getStatus());
    }

    public BillTypePo(BillTypeDto billType) {
        setId(billType.getId());
        setUserId(billType.getUserId());
        setName(billType.getName());
        setKind(billType.getKind());
        setStatus(billType.getStatus());
    }

    public BillType parseToDbEntity() {
        BillType billType = new BillType();
        billType.setId(id);
        billType.setUserId(userId);
        billType.setName(name);
        billType.setKind(kind);
        billType.setStatus(status);

        return billType;
    }
}
