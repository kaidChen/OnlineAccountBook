package com.toys.ACB.service;

import com.github.pagehelper.PageInfo;
import com.toys.ACB.dto.BillDetail;
import com.toys.ACB.entity.Bill;
import com.toys.ACB.entity.Type;

import java.util.List;

public interface UserService {
    PageInfo<BillDetail> getAllBillList(Integer page, Integer size, Long userId) throws Exception;

    int addBill(Bill bill) throws Exception;

    int updateBill(Bill bill) throws Exception;

    int deleteBill(Long bid, Long userId) throws Exception;

    List<Type> getAllTypes(Long userId) throws Exception;

    int addType(Type type) throws Exception;

    int updateType(Type type) throws Exception;

    int deleteType(Long tid, Long userId) throws Exception;

    int updateNickname(String nickname, Long userId) throws Exception;

    int updatePassword(String username, String password) throws Exception;
}
