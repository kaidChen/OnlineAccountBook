package com.toys.acb.controller;

import com.github.pagehelper.PageInfo;
import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.dto.Result;
import com.toys.acb.entity.Bill;
import com.toys.acb.entity.SysUser;
import com.toys.acb.entity.Type;
import com.toys.acb.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户当前周预算周期的所有账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/bill/list")
    public Result getBillList(@RequestParam(value = "page", defaultValue = "1") @Min(1) @Max(Integer.MAX_VALUE) Integer page,
                              @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(50) Integer size) {
        if (page <= 0 || size <= 0 || size > 50) {
            return Result.error(ResultCode.PARAM_NOT_VALID);
        }
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        PageInfo<BillDetail> billPage = userService.getCurrentBillList(page, size, userId);
        return Result.ok().addDate("page_info", billPage);
    }

    @ApiOperation("获取用户指定周期的所有账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/bill/cycle_list")
    public Result getBillListByCycle(@RequestParam(value = "page", defaultValue = "1") @Min(1) @Max(Integer.MAX_VALUE) Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(50) Integer size,
                                     @RequestParam("cycle") Long cycle) {
        if (page <= 0 || size <= 0 || size > 50 || cycle <= 0) {
            return Result.error(ResultCode.PARAM_NOT_VALID);
        }
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        PageInfo<BillDetail> billPage = userService.getBillListByCycle(page, size, cycle, userId);
        return Result.ok().addDate("page_info", billPage);
    }

    @ApiOperation("获取用户指定类型的账单")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/bill/type_list")
    public Result getBillListByTypeId(@RequestParam(value = "page", defaultValue = "1") @Min(1) @Max(Integer.MAX_VALUE) Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(50) Integer size,
                                      @RequestParam("id") Long id) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        PageInfo<BillDetail> billPage = userService.getBillListByTypeId(page, size, id, userId);
        return Result.ok().addDate("page_info", billPage);
    }

    @ApiOperation("添加账单")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("/bill")
    public Result addBill(@RequestBody @Valid Bill bill) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(userId);
        int rows = userService.addBill(bill);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("更新账单")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("bill")
    public Result updateBill(@RequestBody @Valid Bill bill) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(userId);
        int rows = userService.updateBill(bill);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("删除账单")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("/bill")
    public Result deleteBill(@RequestParam("id") Long id) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        int rows = userService.deleteBill(id, userId);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("获取账单类型列表")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/type/list")
    public Result getTypeList() {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        List<Type> allTypes = userService.getTypeList(userId);
        return Result.ok().addDate("type_list", allTypes);
    }

    @ApiOperation("添加账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("/type")
    public Result addType(@RequestBody @Valid Type type) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        type.setUserId(userId);
        int rows = userService.addType(type);
        if (rows < 0) {
            if (rows == -2) {
                return Result.error().message("类型数量已达上限");
            }
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("更新账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("/type")
    public Result updateType(@RequestBody @Valid Type type) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        type.setUserId(userId);
        int rows = userService.updateType(type);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("删除账单类型")
    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("/type")
    public Result deleteType(@RequestParam("id") @Min(1) @Max(Long.MAX_VALUE) Long id) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        int rows = userService.deleteType(id, userId);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @ApiOperation("获取用户信息")
    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/user")
    public Result getUserInfo() {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        SysUser user = userService.getUserByUserId(userId);
        if (user == null) {
            return Result.error().message("获取用户信息失败");
        }
        return Result.ok().addDate("user", user);
    }
}
