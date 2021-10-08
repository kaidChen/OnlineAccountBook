package com.toys.acb.controller;

import com.toys.acb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


}
