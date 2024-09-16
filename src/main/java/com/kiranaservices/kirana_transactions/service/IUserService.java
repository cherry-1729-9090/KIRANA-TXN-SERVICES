package com.kiranaservices.kirana_transactions.service;

import com.kiranaservices.kirana_transactions.model.User;

public interface IUserService {
    User createUser(User user);
    User updateUser(User user);
    User deleteUserByUserId(String userId);

    User getUserByUserId(String userId);
}
