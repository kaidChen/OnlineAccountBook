package com.toys.acb.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BillListOrderByType {
    private String desc;
    private BigDecimal sum;
    private List<BillOfSameType> list;

    public static BillListOrderByType createFromList(List<BillDto> billList) {
        BillListOrderByType billListOrderByType = new BillListOrderByType();

        return billListOrderByType;
    }
}

@Data
class BillOfSameType {
    private String desc;
    private BigDecimal sum;
    private List<BillDto> list;
}
