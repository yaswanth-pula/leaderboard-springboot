package com.zmslabs.springboot.leaderboard.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String renderLoginForm() {
        logger.info("Displaying Login Form");
        return "custom-login";
    }

    @GetMapping("/access-denied")
    public String renderAccessDeniedPage() {
        logger.info("Displaying Access Denied Page");
        return "access-denied";
    }

}
