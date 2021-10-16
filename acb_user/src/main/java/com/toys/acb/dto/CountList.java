package com.toys.acb.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CountList<T> {
    private BigDecimal cost;
    private List<T> list;
}
