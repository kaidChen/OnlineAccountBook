package com.toys.acb.service;

import com.toys.acb.dto.BillDto;
import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.dto.SearchCondition;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<BillDto> getBillListOrderByTimeDesc(Long userId, LocalDate start, LocalDate end);

    List<BillDto> getBillListByTypeId(Long userId, Long typeId, LocalDate start, LocalDate end);

    BillDto getBillById(Long userId, Long id);

    Integer updateBillType(BillTypeDto billTypeDto);

    Integer createBill(BillDto billDto);

    Integer updateBill(BillDto billDto);

    List<BillTypeDto> getBillTypeListByUserId(Long userId);

    Integer createBillType(BillTypeDto billTypeDto);

    Integer deleteBillById(Long userId, Long id);

    Integer deleteBillTypeById(Long userId, Long id);
}
