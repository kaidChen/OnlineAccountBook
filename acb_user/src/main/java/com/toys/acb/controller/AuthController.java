package com.toys.acb.controller;

import com.toys.acb.constant.ResultCode;
import com.toys.acb.dto.LoginForm;
import com.toys.acb.dto.Result;
import com.toys.acb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginForm loginForm){
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

        return Result.ok();
    }

    @PostMapping("/signup")
    public Result signup() {
        return Result.error();
    }
}
