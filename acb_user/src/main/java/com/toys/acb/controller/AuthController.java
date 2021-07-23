package com.toys.acb.controller;

import com.toys.acb.dto.Result;
import com.toys.acb.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AuthController {
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        HttpSession session = request.getSession();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
//        sysUser.setPassword(password);
        sysUser.setId(1L);
        sysUser.setCycle(1L);
        sysUser.setNickname("nick");

//        session.setAttribute("username", username);
//        session.setAttribute("password", password);
        session.setAttribute("user", sysUser);

        return Result.ok();
    }
}
