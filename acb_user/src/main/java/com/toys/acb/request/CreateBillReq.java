package com.toys.acb.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class CreateBillReq {
    private long typeId;

    @Digits(integer = 6, fraction = 6)
    private BigDecimal cost;

    @Length(min = 1, max = 50, message = "备注长度1~50")
    private String note;
}
