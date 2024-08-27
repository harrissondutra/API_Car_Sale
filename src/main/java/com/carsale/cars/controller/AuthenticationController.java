package com.carsale.cars.controller;

import com.carsale.cars.infra.security.TokenData;
import com.carsale.cars.model.User;
import com.carsale.cars.model.records.UserLogin;
import com.carsale.cars.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenData> login(@RequestBody @Valid UserLogin userLogin) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password());
            var authentication = authenticationManager.authenticate(authToken);
            var token = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenData(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new TokenData(e.getMessage()));
        }
    }
}
