package com.carsale.cars.controller;

import com.carsale.cars.infra.security.SpringConfiguration;
import com.carsale.cars.model.User;
import com.carsale.cars.model.records.UserLogin;
import com.carsale.cars.repository.UserRepository;
import com.carsale.cars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserLogin userLogin) {

        var userDetails = userRepository.findByUsername(userLogin.username());

        if(userDetails == null){
            var user = User.builder()
                    .id(null)
                    .username(userLogin.username())
                    .password(passwordEncoder.encode(userLogin.password()))
                    .active(true)
                    .build();

            service.saveUser(user);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().body(null);
        }

    }
}
