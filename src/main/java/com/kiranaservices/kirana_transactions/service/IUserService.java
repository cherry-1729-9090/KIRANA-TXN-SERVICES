package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.dto.UserDTO;
import com.kiranaservices.kirana_transactions.model.User;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO);
    User deleteUserByUserId(String userId);
    User getUserByUserId(String userId);
}
