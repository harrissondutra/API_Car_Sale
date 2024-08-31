package com.carsale.cars.controller;

import com.carsale.cars.infra.exceptions.ErrorResponse;
import com.carsale.cars.infra.security.SpringConfiguration;
import com.carsale.cars.model.User;
import com.carsale.cars.model.records.UserData;
import com.carsale.cars.model.records.UserLogin;
import com.carsale.cars.repository.UserRepository;
import com.carsale.cars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User API")
@RestController
@SecurityRequirement(name = SpringConfiguration.SECURITY)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Create a new user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserData.class)))
    @ApiResponse(responseCode = "400", description = "User not created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "User already exists",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserLogin userLogin) {

        try{
            if (userRepository.findByUsername(userLogin.username()) != null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("User already exists"));
            }
            var user = new User(userLogin.username(), passwordEncoder.encode(userLogin.password()));
            service.saveUser(user);
            return ResponseEntity.ok(new UserData(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ErrorResponse("User not created"));
        }
    }
}

