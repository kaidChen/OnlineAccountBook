package com.toys.acb.service;

import com.github.pagehelper.PageInfo;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.Type;

import java.util.List;

public interface UserService {
    PageInfo<BillDetail> getAllBillList(Integer page, Integer size, Long userId);

    int addBill(Bill bill);

    int updateBill(Bill bill);

    int deleteBill(Long bid, Long userId);

    List<Type> getAllTypes(Long userId);

    int addType(Type type);

    int updateType(Type type);

    int deleteType(Long tid, Long userId);

    int updateNickname(String nickname, Long userId);

    int updatePassword(String username, String password, String newPW);
}
