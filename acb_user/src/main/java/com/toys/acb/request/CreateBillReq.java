package com.toys.acb.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class CreateBillReq {
    @PositiveOrZero
    private Long typeId;

    @Digits(integer = 6, fraction = 6)
    private BigDecimal cost;

    private String note;
}
