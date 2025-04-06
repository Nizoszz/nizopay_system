package com.system.nizopay.http.rest.controller;

import com.system.nizopay.core.service.UserService;
import com.system.nizopay.http.rest.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "Users", description = "User management")
public class UserController{
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.noContent().build();
    }
}
