package com.toys.acb.dto;

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
    private BillDto billType;
}

