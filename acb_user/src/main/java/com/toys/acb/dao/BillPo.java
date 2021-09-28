package com.toys.acb.dao;

import com.toys.acb.entity.Bill;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillPo {
    private Long id;
    private Long userId;
    private Long typeId;
    private LocalDate createdAt;
    private BigDecimal cost;
    private Integer status;
    private String note;

    public BillPo() {
    }

    public BillPo(Bill bill) {
        setId(bill.getId());
        setUserId(bill.getUserId());
        setTypeId(bill.getTypeId());
        setCreatedAt(bill.getCreatedAt());
        setCost(bill.getCost());
        setStatus(bill.getStatus());
        setNote(bill.getNote());
    }

    public Bill parseToDbEntity() {
        Bill bill = new Bill();
        bill.setUserId(getUserId());
        bill.setTypeId(getTypeId());
        bill.setStatus(getStatus());
        bill.setCreatedAt(getCreatedAt());
        bill.setCost(getCost());
        bill.setNote(getNote());

        return bill;
    }
}
