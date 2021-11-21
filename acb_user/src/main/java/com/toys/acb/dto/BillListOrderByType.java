package com.toys.acb.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class BillListOrderByType {
    private String desc;
    private BigDecimal income;
    private BigDecimal outcome;
    private List<BillOfSameType> list = new ArrayList<>();

    public static BillListOrderByType createFromList(List<BillDto> billList, Map<Long, BillTypeDto> map) {
        if (billList == null || billList.isEmpty()) {
            return null;
        }

        BillListOrderByType billListOrderByType = new BillListOrderByType();
        BillOfSameType sameType = new BillOfSameType();
        billListOrderByType.getList().add(sameType);
        Long typeIdPtr = null;
        for (BillDto dto : billList) {
            if (typeIdPtr == null) {
                typeIdPtr = dto.getTypeId();
                sameType.setDto(map.getOrDefault(typeIdPtr, null));
            } else if(!typeIdPtr.equals(dto.getTypeId())){
                typeIdPtr = dto.getTypeId();
                sameType = new BillOfSameType();
                sameType.setDto(map.getOrDefault(typeIdPtr, null));
                billListOrderByType.getList().add(sameType);
            }

            sameType.getList().add(dto);
        }

        return billListOrderByType;
    }
}

@Data
class BillOfSameType {
    private BillTypeDto dto;
    private BigDecimal sum;
    private List<BillDto> list = new ArrayList<>();
}
