package com.kiranaservices.kirana_transactions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private String storeName;
}
