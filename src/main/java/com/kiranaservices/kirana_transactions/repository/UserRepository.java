package com.kiranaservices.kirana_transactions.repository;

import com.kiranaservices.kirana_transactions.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);
    User findByEmail(String email);
}
