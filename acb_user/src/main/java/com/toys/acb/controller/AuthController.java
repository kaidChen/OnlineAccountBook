package com.toys.acb.controller;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.LoginForm;
import com.toys.acb.dto.Result;
import com.toys.acb.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginForm loginForm){
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if (!password.equals("123")) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setId(1L);
        sysUser.setCycle(1L);
        sysUser.setNickname("nick");

        HttpSession session = request.getSession();
        session.setAttribute("user", sysUser);

        return Result.ok();
    }

    @PostMapping("/signup")
    public Result signup() {
        return Result.error();
    }
}
