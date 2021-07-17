package com.toys.ACB.controller;

import com.toys.ACB.dto.LoginForm;
import com.toys.ACB.entity.SysUser;
import com.toys.ACB.response.Result;
import com.toys.ACB.response.ResultCode;
import com.toys.ACB.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping("/error")
    public void logonError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        try {
            SysUser user = authService.login(username, password);
            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            return Result.ok().data(map);
        } catch (BadCredentialsException e) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        } catch (DisabledException e) {
            return Result.error(ResultCode.USER_ACCOUNT_DISABLE);
        } catch (Exception e) {
            return Result.error().message(e.getMessage());
        }
    }
}
