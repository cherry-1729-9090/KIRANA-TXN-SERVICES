package com.kiranaservices.kirana_transactions.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter

@Document(collation = "user")
public class User {

    @Id
    private String userId;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private String storeName;
}
