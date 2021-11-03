package com.toys.acb.request;

import com.toys.acb.constant.DbCode;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class CreateBillReq {
    @PositiveOrZero
    private Long typeId;

    @Digits(integer = 6, fraction = 6)
    private BigDecimal cost;

    @Min(DbCode.BillStatusInvalid)
    @Max(DbCode.BillStatusValid)
    private Integer status;

    private String note;
}
