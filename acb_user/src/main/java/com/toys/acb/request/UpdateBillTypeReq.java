package com.toys.acb.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateBillTypeReq {
    private long id;

    @Length(min = 1, max = 10, message = "名称长度1~10")
    private String name;
}
