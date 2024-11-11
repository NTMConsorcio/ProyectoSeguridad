package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.authentication.LoginResponse;
import com.ntm.appseguridad.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated Usuario request) {
        return authService.attemptLogin(request.getCuenta(), request.getClave());
    }
}
