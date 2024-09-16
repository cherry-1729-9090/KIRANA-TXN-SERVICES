package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.model.User;
import com.kiranaservices.kirana_transactions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return createdUser;
    }

    @GetMapping("/get/:id")
    public User getUser(@RequestParam String userId){
        User user = userService.getUserByUserId(userId);
        return user;
    }

    @PatchMapping("/update")
    public User updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        return updatedUser;
    }

    @DeleteMapping("/delete/:id")
    public User deleteUserByUserId(@RequestParam String userId){
        User deletedUser = userService.deleteUserByUserId(userId);
        return deletedUser;
    }

}
