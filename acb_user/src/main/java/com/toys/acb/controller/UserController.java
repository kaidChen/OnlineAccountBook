package com.toys.acb.controller;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.Result;
import com.toys.acb.dto.SysUserDto;
import com.toys.acb.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final static String SessionAttributeUser = "user";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @ApiOperation("获取当前的账单列表")
    @GetMapping("bill_list")
    public Result getBillList() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }


        return null;
    }

    @ApiOperation("获取账单列表")
    @GetMapping("type_list")
    public Result getTypeList() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }


        return null;
    }

    
}
