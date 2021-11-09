package com.toys.acb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.BillType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillDto {
    private Long id;
    private Long userId;
    private Long typeId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdAt;
    private BigDecimal cost;
    private Integer status;
    private String note;
    private BillTypeDto billType;

    public BillDto parseFromPo(Bill billPo, BillType billTypePo) {
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
                setBillType(billTypeDto.parseFromPo(billTypePo));
            }
        }
        return this;
    }

    public Bill parseToPo() {
        Bill bill = new Bill();
        bill.setId(id);
        bill.setUserId(userId);
        bill.setTypeId(typeId);
        bill.setCreatedAt(createdAt);
        bill.setCost(cost);
        bill.setStatus(status);
        bill.setNote(note);
        return bill;
    }
}

