package com.kiranaservices.kirana_transactions.controller;

import com.kiranaservices.kirana_transactions.dto.UserDTO;
import com.kiranaservices.kirana_transactions.model.User;
import com.kiranaservices.kirana_transactions.service.IUserService;
import com.kiranaservices.kirana_transactions.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        // Check if a user with the given email already exists
        User existingUser = userService.getUserByEmailAndPassword(userDTO.getEmail(), null);
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists! Please try to login.");
        }

        // If the user doesn't exist, create a new user
        User newUser = userService.createUser(userDTO);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user.");
        }

        // Generate a JWT token for the newly registered user
        String token = jwtUtil.generateToken(newUser.getEmail());

        return ResponseEntity.ok("JWT Token: " + token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String userName, @RequestParam String email,@RequestParam String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for update");
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable String userId) {
        User deletedUser = userService.deleteUserByUserId(userId);
        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for deletion");
        }
    }

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("Hello from User Controller");
        return "Hello from user controller";
    }
}
