package com.toys.acb.service;

import com.github.pagehelper.PageInfo;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.SysUser;
import com.toys.acb.entity.Type;

import java.util.List;

public interface UserService {
    PageInfo<BillDetail> getCurrentBillList(Integer page, Integer size, Long userId);

//    PageInfo<BillDetail> getBillListByCycle(Integer page, Integer size, Long cycle, Long userId);
//
//    PageInfo<BillDetail> getBillListByTypeId(Integer page, Integer size, Long typeId, Long userId);

    PageInfo<BillDetail> getBillListWithCond(Integer page, Integer size, Long userId, Long cycle, Long typeId);

    int addBill(Bill bill);

    int updateBill(Bill bill);

    int deleteBill(Long bid, Long userId);

    List<Type> getTypeList(Long userId);

    int addType(Type type);

    int updateType(Type type);

    int deleteType(Long tid, Long userId);

    SysUser getUserByUserId(Long userId);

    int updateNickname(String nickname, Long userId);

}
