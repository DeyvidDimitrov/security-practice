package com.jwt.jwtdemo.controller;

import java.security.Principal;

import com.jwt.jwtdemo.config.RsaKeyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(RsaKeyProperties.class)
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello " + principal.getName() + "!";
    }
}
