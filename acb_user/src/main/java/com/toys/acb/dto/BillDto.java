package com.toys.acb.dto;

import com.toys.acb.dao.BillPo;
import com.toys.acb.dao.BillTypePo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillDto {
    private Long id;
    private Long userId;
    private Long typeId;
    private LocalDate createdAt;
    private BigDecimal cost;
    private Integer status;
    private String note;
    private BillTypeDto billType;

    public void parseFromPo(BillPo billPo, BillTypePo billTypePo) {
        if (billPo != null) {
            setId(billPo.getId());
            setUserId(billPo.getUserId());
            setTypeId(billPo.getTypeId());
            setCreatedAt(billPo.getCreatedAt());
            setCost(billPo.getCost());
            setStatus(billPo.getStatus());
            setNote(billPo.getNote());
            setBillType(null);
            if (billTypePo != null) {
                BillTypeDto billTypeDto = new BillTypeDto();
                billTypeDto.parseFromPo(billTypePo);
                setBillType(billTypeDto);
            }
        }
    }
}

