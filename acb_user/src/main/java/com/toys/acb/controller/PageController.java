package com.toys.acb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
