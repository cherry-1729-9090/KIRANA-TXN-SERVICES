package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.dto.UserDTO;
import com.kiranaservices.kirana_transactions.model.User;
import com.kiranaservices.kirana_transactions.service.IUserService;
import com.kiranaservices.kirana_transactions.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        String token = jwtUtil.generateToken(user.getEmail()); // Assuming the email is used for token generation
        return ResponseEntity.ok("JWT Token: " + token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok("JWT Token: " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
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
