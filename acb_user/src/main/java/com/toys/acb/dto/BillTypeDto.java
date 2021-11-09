package com.toys.acb.dto;

import com.toys.acb.entity.BillType;
import lombok.Data;

@Data
public class BillTypeDto {
    private Long id;
    private Long userId;
    private String name;
    private Integer kind;
    private Integer status;

    public BillTypeDto parseFromPo(BillType billTypePo) {
        if (billTypePo != null) {
            setId(billTypePo.getId());
            setUserId(billTypePo.getUserId());
            setName(billTypePo.getName());
            setKind(billTypePo.getKind());
            setStatus(billTypePo.getStatus());
        }
        return this;
    }

    public BillType parseToPo() {
        BillType billType = new BillType();
        billType.setId(id);
        billType.setUserId(userId);
        billType.setName(name);
        billType.setKind(kind);
        billType.setStatus(status);
        return billType;
    }
}
