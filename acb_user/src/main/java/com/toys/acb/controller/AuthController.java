package com.toys.acb.controller;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.LoginForm;
import com.toys.acb.dto.PasswordForm;
import com.toys.acb.dto.Result;
import com.toys.acb.entity.SysUser;
import com.toys.acb.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }

        if (!userDetails.isEnabled()) {
            return Result.error(ResultCode.USER_ACCOUNT_LOCKED);
        }

        Long userId = authService.login(username);
        if (userId == null) {
            return Result.error(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }

        request.getSession().setAttribute("userId", userId);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("用户登录成功：{}", username);
        return Result.ok().message("登录成功");
    }

    @ApiOperation("注册账号")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/signup")
    public Result signup(@RequestBody SysUser sysUser) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }
        int rows = authService.signup(sysUser);
        if (rows < 0) {
            LOGGER.info("注册账号失败，管理员id：{}", userId);
            return Result.error().message("创建用户失败");
        }
        LOGGER.info("注册账号成功，管理员id：{}，用户id：{}", userId, sysUser.getId());
        return Result.ok().message("注册成功");
    }

    @ApiOperation("修改自己的密码")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    @PutMapping("update_pw")
    public Result updatePassword(@RequestBody @Valid PasswordForm passwordForm) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        String oldPW = passwordForm.getOldPassword();
        String newPW = passwordForm.getNewPassword();
        int rows = authService.updatePassword(oldPW, newPW, userId);
        if (rows < 0) {
            if(rows == -2) {
                LOGGER.info("修改密码失败，用户不存在：user_id={}", userId);
                return Result.error(ResultCode.USER_ACCOUNT_NOT_EXIST);
            }

            if (rows == -3) {
                LOGGER.info("修改密码失败，密码错误：user_id={}", userId);
                return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
            }

            LOGGER.info("修改密码失败：user_id={}", userId);
            return Result.error(ResultCode.SYSTEM_EXCEPTION);
        }

        LOGGER.info("修改密码成功，用户id：{}", userId);
        return Result.ok().message("修改成功");
    }

    @ApiOperation("管理员修改用户的密码")
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/update_userpw")
    public Result updateUserPassword(@RequestBody @Valid PasswordForm passwordForm) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error(ResultCode.USER_NOT_LOGIN);
        }

        String username = passwordForm.getUsername();
        String password = passwordForm.getNewPassword();
        int rows = authService.updateUserPassword(username, password);
        if (rows < 0) {
            LOGGER.info("修改用户密码失败：username={}, admin_id={}", username, userId);
            return Result.error().message("修改用户密码失败");
        }

        LOGGER.info("修改用户密码成功：username={}, admin_id={}", username, userId);
        return Result.ok().message("修改用户密码成功");
    }
}
