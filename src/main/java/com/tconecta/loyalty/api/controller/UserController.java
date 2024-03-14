package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.UserRequest;
import com.tconecta.loyalty.api.model.UserResponse;
import com.tconecta.loyalty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> userCreate (@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.userCreate(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getUserAll () {
        HttpStatus httpStatus = HttpStatus.OK;
        List<UserResponse> userResponseList = userService.getAllUser();
        if (userResponseList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(userResponseList, httpStatus);
    }

    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        HttpStatus httpStatus = HttpStatus.OK;
        UserResponse userResponse = userService.getUserById(userId);

        if (null == userResponse) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(userResponse, httpStatus);
    }

    @GetMapping(value = "/name/{userName}")
    public ResponseEntity<Object> getUserById(@PathVariable String userName) {
        HttpStatus httpStatus = HttpStatus.OK;
        UserResponse userResponse = userService.getUserByName(userName.trim().toUpperCase());

        if (null == userResponse) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(userResponse, httpStatus);
    }
}
