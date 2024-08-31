package com.carsale.cars.controller;

import com.carsale.cars.infra.exceptions.ErrorResponse;
import com.carsale.cars.infra.security.TokenData;
import com.carsale.cars.model.User;
import com.carsale.cars.model.records.UserLogin;
import com.carsale.cars.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Login successful",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TokenData.class)))
    @ApiResponse(responseCode = "400", description = "Invalid credentials",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @Operation(summary = "Login", description = "Default user: admin\n\n Default password: admin")
    public ResponseEntity<TokenData> login(@RequestBody @Valid UserLogin userLogin) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password());
            var authentication = authenticationManager.authenticate(authToken);
            var token = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenData(userLogin.username(), token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new TokenData(userLogin.username(), "User not found"));
        }
    }
}
