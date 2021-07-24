package com.toys.acb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @ApiOperation("登录界面")
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @ApiOperation("主界面")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
