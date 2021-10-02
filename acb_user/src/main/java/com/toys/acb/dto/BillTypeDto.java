package com.toys.acb.dto;

import com.toys.acb.dao.BillTypePo;
import lombok.Data;

@Data
public class BillTypeDto {
    private Long id;
    private Long userId;
    private String name;
    private Integer kind;
    private Integer status;

    public void parseFromPo(BillTypePo billTypePo) {
        if (billTypePo != null) {
            setId(billTypePo.getId());
            setUserId(billTypePo.getUserId());
            setName(billTypePo.getName());
            setKind(billTypePo.getKind());
            setStatus(billTypePo.getStatus());
        }
    }
}
