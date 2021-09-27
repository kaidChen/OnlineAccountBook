package com.toys.acb.model;

import lombok.Data;

@Data
public class BillType {
    private Long id;
    private Long userId;
    private String name;
    private Integer kind;
    private Integer status;
}
