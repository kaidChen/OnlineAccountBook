package com.toys.ACB.controller;

import com.toys.ACB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("bill_list")
//    public PageInfo<BillDetail> getBillDetailList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
//        List<BillDetail> billDetailList = commonUserService.getAllBillList(page, size);
//        return new PageInfo<>(billDetailList);
//    }
}
