package com.toys.acb.service;

import com.toys.acb.dto.BillDto;
import com.toys.acb.dto.BillTypeDto;
import com.toys.acb.dto.Condition;

import java.util.List;

public interface UserService {
    Integer createBill(BillDto billDto);


    Integer deleteBill(BillDto billDto);

    Integer updateBill(BillDto billDto);

    BillDto getBill(BillDto billDto);

    List<BillDto> getBillList(BillDto billDto, Condition cond);

    Integer createBillType(BillTypeDto billTypeDto);

    Integer updateBillType(BillTypeDto billTypeDto);

    BillTypeDto getBillType(BillTypeDto billTypeDto);

    List<BillTypeDto> getBillTypeList(BillTypeDto billTypeDto, Condition cond);
}
