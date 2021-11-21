package com.toys.acb.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class CreateBillReq {
    private long typeId;

    @Digits(integer = 6, fraction = 6)
    private BigDecimal cost;

    private String note;
}
