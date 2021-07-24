package com.toys.acb.controller;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.BillDetail;
import com.toys.acb.dto.Result;
import com.toys.acb.entity.*;
import com.toys.acb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    private SysUser getUser() {
        return (SysUser) request.getSession().getAttribute("user");
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/test")
    public SysUser test() {
//        String username = (String) request.getSession().getAttribute("username");
//        String password = (String) request.getSession().getAttribute("password");
//        return username + ": " + password;

        return getUser();
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/bill/list")
    public Result getBillList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (page <= 0 || size <= 0 || size > 50) {
            return Result.error(ResultCode.PARAM_NOT_VALID);
        }

        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        List<BillDetail> allBillList = userService.getAllBillList(page, size, sysUser.getId());
        return Result.ok().addDate("bill_list", allBillList);
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping("/bill")
    public Result addBill(@RequestBody Bill bill) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(sysUser.getId());
        int rows = userService.addBill(bill);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @PreAuthorize("hasRole('user')")
    @PutMapping("bill")
    public Result updateBill(@RequestBody Bill bill) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        bill.setUserId(sysUser.getId());
        int rows = userService.updateBill(bill);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @PreAuthorize("hasRole('user')")
    @DeleteMapping("/bill")
    public Result deleteBill(@RequestParam("id") Long id) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        int rows = userService.deleteBill(id, sysUser.getId());
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/type/list")
    public Result getTypeList() {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        List<Type> allTypes = userService.getAllTypes(sysUser.getId());
        return Result.ok().addDate("type_list", allTypes);
    }

    @PreAuthorize("hasRole('user')")
    @PostMapping("/type")
    public Result addType(@RequestBody Type type) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        type.setUserId(sysUser.getId());
        int rows = userService.addType(type);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @PreAuthorize("hasRole('user')")
    @PutMapping("/type")
    public Result updateType(@RequestBody Type type) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        type.setUserId(sysUser.getId());
        int rows = userService.updateType(type);
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }

    @PreAuthorize("hasRole('user')")
    @DeleteMapping("/type")
    public Result deleteType(@RequestParam("id") Long id) {
        SysUser sysUser = getUser();
        if (sysUser == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        int rows = userService.deleteType(id, sysUser.getId());
        if (rows < 0) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }
        return Result.ok().message(String.format("增添%d条数据", rows));
    }
}
