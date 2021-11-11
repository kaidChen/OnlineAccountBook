package com.toys.acb.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateBillTypeReq {
    @PositiveOrZero
    private Long id;

    @Length(min = 1, max = 10, message = "名称长度1~10")
    private String name;
}
