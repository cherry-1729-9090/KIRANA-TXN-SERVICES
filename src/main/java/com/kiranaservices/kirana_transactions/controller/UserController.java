package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.dto.UserDTO;
import com.kiranaservices.kirana_transactions.model.User;
import com.kiranaservices.kirana_transactions.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/get/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }

    @PatchMapping("/update")
    public User updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{userId}")
    public User deleteUserByUserId(@PathVariable String userId) {
        return userService.deleteUserByUserId(userId);
    }

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("Hello from User Controller");
        return "Hello from user controller";
    }
}
