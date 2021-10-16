package com.toys.acb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Condition {
    private LocalDate start;
    private LocalDate end;
    private Integer page;
    private Integer size;
    private Boolean needType;
}
