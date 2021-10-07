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
    public Result login(@RequestBody @Valid LoginReq req) {
        String username = req.getUsername();
        String password = req.getPassword();

        SysUserDto userDto = new SysUserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto = authService.login(userDto);

        if (userDto == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        UserDetails userDetails = userDto.createUserDetails();

        if (!authService.matchPassword(password, userDetails.getPassword())) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        if (!userDetails.isAccountNonLocked()) {
            return Result.error(ResultCode.USER_ACCOUNT_LOCKED);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        sessionUtil.login(username, request.getSession());

        request.getSession().setAttribute(SessionAttributeUser, userDto);

        LOGGER.info("用户登录成功：{}", username);
        return Result.ok().message("登录成功");
    }

    @ApiOperation("注册账号")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/signup")
    public Result signup() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }

    @ApiOperation("修改自己的密码")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    @PutMapping("update_password")
    public Result updatePassword(@RequestBody @Valid UpdatePasswordReq req) {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        String oldPW = req.getOldPassword();
        String newPW = req.getNewPassword();

        if (!authService.matchPassword(oldPW, user.getPassword())) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }

        SysUserDto newUser = new SysUserDto();
        newUser.setId(user.getId());
        newUser.setPassword(newPW);
        Integer rows = authService.updatePassword(newUser);
        if (rows == null) {
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        request.getSession().invalidate();

        LOGGER.info("修改密码成功，用户：{}", user.getUsername());
        return Result.ok().message("修改成功，请重新登录");
    }

    @ApiOperation("管理员修改用户信息")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/update_user")
    public Result updateUserPassword() {
        SysUserDto user = (SysUserDto) request.getSession().getAttribute(SessionAttributeUser);

        if (user == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        return null;
    }
}
