package com.toys.acb.request;

import com.toys.acb.constant.DbCode;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateBillTypeReq {
    @Size(min = 1, max = 10, message = "名称长度1~10")
    private String name;

    @Min(DbCode.BillTypeStatusInvalid)
    @Max(DbCode.BillTypeStatusValid)
    private Integer kind;
}
