package com.finance.dashboard.finance.management.system.controllers;

import com.finance.dashboard.finance.management.system.dtos.LoginRequest;
import com.finance.dashboard.finance.management.system.dtos.LoginResponse;
import com.finance.dashboard.finance.management.system.dtos.RegisteRequestDto;
import com.finance.dashboard.finance.management.system.servies.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisteRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
