package com.toys.acb.dto;

import lombok.Data;

@Data
public class BillTypeDto {
    private Long id;
    private Long userId;
    private String name;
    private Integer kind;
    private Integer status;
}
