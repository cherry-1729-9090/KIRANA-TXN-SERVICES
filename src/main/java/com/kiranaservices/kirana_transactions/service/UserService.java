package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.dto.UserDTO;
import com.kiranaservices.kirana_transactions.model.User;
import com.kiranaservices.kirana_transactions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            return null; // or throw an exception if you prefer
        }

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setPassword(userDTO.getPassword());
        user.setStoreName(userDTO.getStoreName());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = userRepository.findByUserId(userDTO.getUserId());
        if (user != null) {
            user.setUserName(userDTO.getUserName());
            user.setEmail(userDTO.getEmail());
            user.setMobileNumber(userDTO.getMobileNumber());
            user.setPassword(userDTO.getPassword());
            user.setStoreName(userDTO.getStoreName());
            return userRepository.save(user);
        }
        return null; // or throw an exception
    }

    @Override
    public User deleteUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            userRepository.delete(user);
            return user;
        }
        return null; // or throw an exception
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Return null if the user is not found or password is incorrect
    }
}
