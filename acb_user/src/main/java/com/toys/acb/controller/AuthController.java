package com.toys.acb.controller;

import com.toys.acb.component.SessionUtil;
import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.Result;
import com.toys.acb.dto.SysUserDto;
import com.toys.acb.request.LoginReq;
import com.toys.acb.request.UpdatePasswordReq;
import com.toys.acb.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class AuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final static String SessionAttributeUser = "user";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionUtil sessionUtil;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Object> login(@RequestBody @Valid LoginReq req) {
        String username = req.getUsername();
        String password = req.getPassword();

        SysUserDto userDto = authService.login(username);

        if (userDto == null) {
            return new Result<>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        UserDetails userDetails = userDto.createUserDetails();

        if (!authService.matchPassword(password, userDetails.getPassword())) {
            return new Result<>().error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        if (!userDetails.isAccountNonLocked()) {
            return new Result<>().error(ResultCode.USER_ACCOUNT_LOCKED);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        sessionUtil.login(username, request.getSession());

        request.getSession().setAttribute(SessionAttributeUser, userDto);

        LOGGER.info("用户登录成功：{}, 时间: {}", username, LocalDateTime.now());
        return new Result<>().ok().message("登录成功");
    }

    @ApiOperation("修改自己的密码")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    @PutMapping("update/password")
    public Result<Object> updatePassword(@RequestBody @Valid UpdatePasswordReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return new Result<>().error(ResultCode.USER_NOT_LOGIN);
        }

        String oldPW = req.getOldPassword();
        String newPW = req.getNewPassword();

        if (!authService.matchPassword(oldPW, user.getPassword())) {
            return new Result<>().error(ResultCode.USER_CREDENTIALS_ERROR);
        }

        Integer rows = authService.updatePassword(user.getId(), newPW);
        if (rows == null) {
            return new Result<>().error(ResultCode.SYSTEM_EXCEPTION);
        }

        sessionUtil.deleteSession(user.getUsername());

        LOGGER.info("修改密码成功，用户：{}", user.getUsername());
        return new Result<>().ok().message("修改成功，请重新登录").data(rows);
    }

    // todo 实现管理员注册用户的功能
    @ApiOperation("注册账号")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/signup")
    public Result<Object> signup() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return new Result<>().error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }

    // todo 实现管理员更改用户信息的功能
    @ApiOperation("管理员修改用户信息")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/update/user")
    public Result<Object> updateUserPassword() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return new Result<>().error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }
}
