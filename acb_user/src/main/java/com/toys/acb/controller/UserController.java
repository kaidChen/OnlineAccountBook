package com.toys.acb.controller;

import com.toys.acb.constant.DbCode;
import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.*;
import com.toys.acb.entity.Bill;
import com.toys.acb.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final static String SessionAttributeUser = "user";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @ApiOperation("按条件查询账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("bill_list")
    public Result getBillListWithCondition(@RequestBody SearchCondition cond) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto searchBill = new BillDto();
        searchBill.setUserId(user.getId());
        searchBill.setTypeId(cond.getTypeId());
        searchBill.setStatus(cond.getStatus());

        List<BillDto> billList = userService.getBillList(searchBill, cond);

        if (billList == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        return Result.ok().data("bill_list", billList);
    }

    @ApiOperation("查询订单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("bill")
    public Result getBill(@RequestParam("id") Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto billDto = new BillDto();
        billDto.setId(id);
        billDto.setUserId(user.getId());

        BillDto bill = userService.getBill(billDto);
        if (bill == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().data("bill", bill);
    }

    @ApiOperation("创建订单")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("bill")
    public Result createBill(@RequestBody BillDto bill) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(user.getId());

        Integer rows = userService.createBill(bill);
        if (rows == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message("insert a new bill");
    }

    @ApiOperation("修改账单")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("bill")
    public Result updateBill(@RequestBody BillDto bill) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(user.getId());

        Integer rows = userService.updateBill(bill);
        if (rows == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message("update bill success");
    }

    @ApiOperation("删除账单")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("bill")
    public Result deleteBill(@RequestParam("id") Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto bill = new BillDto();
        bill.setUserId(user.getId());
        bill.setId(id);

        Integer rows = userService.deleteBill(bill);
        if (rows == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message("update bill success");
    }

    @ApiOperation("获取账单类型列表")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("type_list")
    public Result getTypeList() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        BillTypeDto billTypeDto = new BillTypeDto();
        billTypeDto.setUserId(user.getId());

        return null;
    }

    @ApiOperation("创建账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("type")
    public Result createType() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }

    @ApiOperation("删除订单类型")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("type")
    public Result deleteType() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }

    @ApiOperation("修改订单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("type")
    public Result updateType() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }
}
