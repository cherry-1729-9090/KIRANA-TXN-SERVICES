package com.kiranaservices.kirana_transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KiranaTransactionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(KiranaTransactionsApplication.class, args);
    }
}
