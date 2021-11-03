package com.toys.acb.controller;

import com.toys.acb.constant.DbCode;
import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.*;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.BillType;
import com.toys.acb.request.CreateBillReq;
import com.toys.acb.request.CreateBillTypeReq;
import com.toys.acb.request.UpdateBillReq;
import com.toys.acb.request.UpdateBillTypeReq;
import com.toys.acb.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
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
    public Result<ResultList<ResultList<BillDto>>> getBillListWithCondition(@RequestBody SearchCondition cond) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<ResultList<ResultList<BillDto>>>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto searchBill = new BillDto();
        searchBill.setUserId(user.getId());
        searchBill.setTypeId(cond.getTypeId());
        searchBill.setStatus(cond.getStatus());

        List<BillDto> billList = userService.getBillList(searchBill, cond);

        if (billList == null) {
            return new Result<ResultList<ResultList<BillDto>>>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        return new Result<ResultList<ResultList<BillDto>>>().ok().data(null);
    }

    @ApiOperation("根据id查询订单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("bill")
    public Result<BillDto> getBill(@RequestParam("id") @PositiveOrZero Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<BillDto>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto billDto = new BillDto();
        billDto.setId(id);
        billDto.setUserId(user.getId());

        BillDto bill = userService.getBill(billDto);
        if (bill == null) {
            return new Result<BillDto>().error(ResultCode.SYSTEM_EXCEPTION);
        }
        return new Result<BillDto>().ok().data(bill);
    }

    @ApiOperation("创建账单")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("bill")
    public Result<Integer> createBill(@RequestBody @Valid CreateBillReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto billDto = new BillDto();
        billDto.setUserId(user.getId());
        billDto.setTypeId(req.getTypeId());
        billDto.setCost(req.getCost());
        billDto.setStatus(req.getStatus());
        billDto.setNote(req.getNote());

        Integer rows = userService.createBill(billDto);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},创建账单:{}", user.getUsername(), billDto);
        return new Result<Integer>().ok().message("insert a new bill").data(rows);
    }

    @ApiOperation("修改账单")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("bill")
    public Result<Integer> updateBill(@RequestBody @Valid UpdateBillReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto billDto = new BillDto();
        billDto.setUserId(user.getId());
        billDto.setId(req.getId());
        billDto.setTypeId(req.getTypeId());
        billDto.setCost(req.getCost());
        billDto.setStatus(req.getStatus());
        billDto.setNote(req.getNote());

        Integer rows = userService.updateBill(billDto);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},修改账单:{}", user.getUsername(), billDto);
        return new Result<Integer>().ok().message("update bill success").data(rows);
    }

    @ApiOperation("删除账单")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("bill")
    public Result<Integer> deleteBill(@RequestParam("id") @PositiveOrZero Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto bill = new BillDto();
        bill.setUserId(user.getId());
        bill.setId(id);

        Integer rows = userService.deleteBill(bill);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},删除账单id:{}", user.getUsername(), id);
        return new Result<Integer>().ok().message("update bill success").data(rows);
    }

    @ApiOperation("获取账单类型列表")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("type_list")
    public Result<List<BillTypeDto>> getTypeList() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<List<BillTypeDto>>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillTypeDto billTypeDto = new BillTypeDto();
        billTypeDto.setUserId(user.getId());
        billTypeDto.setStatus(DbCode.BillTypeStatusValid);

        List<BillTypeDto> billTypeList = userService.getBillTypeList(billTypeDto);
        if (billTypeList == null) {
            return new Result<List<BillTypeDto>>().error(ResultCode.SYSTEM_EXCEPTION);
        }
        return new Result<List<BillTypeDto>>().ok().data(billTypeList);
    }

    @ApiOperation("修改账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("type")
    public Result<Integer> updateType(@RequestBody @Valid UpdateBillTypeReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillTypeDto billTypeDto = new BillTypeDto();
        billTypeDto.setId(req.getId());
        billTypeDto.setUserId(user.getId());
        billTypeDto.setName(req.getName());
        billTypeDto.setKind(req.getKind());
        Integer rows = userService.updateBillType(billTypeDto);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},修改账单类型:{}", user.getUsername(), billTypeDto);
        return new Result<Integer>().ok().message("update bill type success").data(rows);
    }

    @ApiOperation("创建账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("type")
    public Result<Integer> createType(@RequestBody @Valid CreateBillTypeReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillTypeDto billTypeDto = new BillTypeDto();
        billTypeDto.setUserId(user.getId());
        billTypeDto.setStatus(DbCode.BillTypeStatusValid);
        billTypeDto.setName(req.getName());
        billTypeDto.setKind(req.getKind());

        Integer rows = userService.createBillType(billTypeDto);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},创建账单类型:{}", user.getUsername(), billTypeDto);
        return new Result<Integer>().ok().message("create bill type success");
    }

    @ApiOperation("删除账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("type")
    public Result<Integer> deleteType(@RequestParam("id") @PositiveOrZero Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<Integer>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillTypeDto billTypeDto = new BillTypeDto();
        billTypeDto.setUserId(user.getId());
        billTypeDto.setId(id);
        billTypeDto.setStatus(DbCode.BillTypeStatusInvalid);

        Integer rows = userService.updateBillType(billTypeDto);
        if (rows == null) {
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},删除账单类型:{}", user.getUsername(), id);
        return new Result<Integer>().ok().message("delete bill type success");
    }
}
