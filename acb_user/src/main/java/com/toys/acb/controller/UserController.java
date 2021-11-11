package com.toys.acb.controller;

import com.toys.acb.constant.DbCode;
import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.*;
import com.toys.acb.request.CreateBillReq;
import com.toys.acb.request.CreateBillTypeReq;
import com.toys.acb.request.UpdateBillReq;
import com.toys.acb.request.UpdateBillTypeReq;
import com.toys.acb.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final static String SessionAttributeUser = "user";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @ApiOperation("按年-月查询账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("bill_list")
    public Result<MonthBillList> getMonthlyBillList(
            @RequestParam("year") @Pattern(regexp = "[0-9]{4}", message = "年份为4位数字") Integer year,
            @RequestParam("month") @Range(min = 1, max = 12, message = "1~12") Integer month
    ) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<MonthBillList>().error(ResultCode.USER_NOT_LOGIN);
        }

        LocalDate now = LocalDate.now();

        if (year == null) {
            year = now.getYear();
        }

        if (month == null) {
            month = now.getMonthValue();
        }

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        List<BillDto> billList = userService.getBillListOrderByTimeDesc(user.getId(), startDate, endDate);
        if (billList == null) {
            LOGGER.error("getBillListOrderByTimeDesc fail, {}, {}, {}", user.getId(), startDate, endDate);
            return new Result<MonthBillList>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        MonthBillList monthlyList = MonthBillList.newFromList(year, month, billList);

        return new Result<MonthBillList>().ok().data(monthlyList);
    }

    @ApiOperation("按类型归类账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("")
    public Result<BillListOrderByType> getBillOrderByType(
            @RequestParam("year") @Pattern(regexp = "[0-9]{4}", message = "年份为4位数字") Integer year,
            @RequestParam("month") @Range(min = 1, max = 12, message = "1~12") Integer month
    ) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<BillListOrderByType>().error(ResultCode.USER_NOT_LOGIN);
        }

        LocalDate now = LocalDate.now();

        if (year == null) {
            year = now.getYear();
        }

        if (month == null) {
            month = now.getMonthValue();
        }

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        List<BillDto> billList = userService.getBillListOrderByType(user.getId(), startDate, endDate);
        if (billList == null) {
            LOGGER.error("getBillListOrderByType fail, {}, {}, {}", user.getId(), startDate, endDate);
            return new Result<BillListOrderByType>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        Map<Long, BillTypeDto> map = new HashMap<>();
        List<BillTypeDto> typeList = userService.getBillTypeListByUserId(user.getId());
        if (typeList == null) {
            LOGGER.error("getBillTypeListByUserId, {}", user.getId());
            return new Result<BillListOrderByType>().error(ResultCode.SYSTEM_EXCEPTION);
        }
        typeList.forEach(rec -> map.put(rec.getId(), rec));

        BillListOrderByType result = BillListOrderByType.createFromList(billList, map);

        return new Result<BillListOrderByType>().ok().data(result);
    }

    @ApiOperation("根据id查询账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("bill")
    public Result<BillDto> getBill(@RequestParam("id") @PositiveOrZero Long id) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);
        if (user == null) {
            return new Result<BillDto>().error(ResultCode.USER_NOT_LOGIN);
        }

        BillDto bill = userService.getBillById(user.getId(), id);
        if (bill == null) {
            LOGGER.error("getBillById fail, {}, {}", user.getId(), id);
            return new Result<BillDto>().error(ResultCode.DB_DATA_NOT_EXISTS);
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
        billDto.setStatus(DbCode.BillStatusValid);
        billDto.setNote(req.getNote());
        Integer rows = userService.createBill(billDto);
        if (rows == null) {
            LOGGER.error("createBill fail, {}", billDto);
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
        billDto.setNote(req.getNote());
        Integer rows = userService.updateBill(billDto);
        if (rows == null) {
            LOGGER.error("updateBill fail, {}", billDto);
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

        Integer rows = userService.deleteBillById(user.getId(), id);
        if (rows == null) {
            LOGGER.error("deleteBillById, {}, {}", user.getId(), id);
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

        List<BillTypeDto> billTypeList = userService.getBillTypeListByUserId(user.getId());
        if (billTypeList == null) {
            LOGGER.error("getBillTypeListByUserId fail, {}", user.getId());
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
        Integer rows = userService.updateBillType(billTypeDto);
        if (rows == null) {
            LOGGER.error("updateBillType fail, {}", billTypeDto);
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
            LOGGER.error("createBillType fail, {}", billTypeDto);
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

        Integer rows = userService.deleteBillTypeById(user.getId(), id);
        if (rows == null) {
            LOGGER.error("deleteBillTypeById fail, {}, {}", user.getId(), id);
            return new Result<Integer>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("用户:{},删除账单类型:{}", user.getUsername(), id);
        return new Result<Integer>().ok().message("delete bill type success");
    }
}
