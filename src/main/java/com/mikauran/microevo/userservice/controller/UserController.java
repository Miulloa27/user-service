package com.mikauran.microevo.userservice.controller;

import com.mikauran.microevo.userservice.entity.UserMicro;
import com.mikauran.microevo.userservice.entity.vo.ResponseTemplateVO;
import com.mikauran.microevo.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    public static final String USER_SERVICE = "User-Service";


    @PostMapping("/")
    public UserMicro saveUser(@RequestBody UserMicro user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info(">>>>>>>>>> INSIDE getUserWithDepartment of UserController <<<<<<<<<<<<<");
        return userService.getUserWithDepartment(userId);
    }

}
