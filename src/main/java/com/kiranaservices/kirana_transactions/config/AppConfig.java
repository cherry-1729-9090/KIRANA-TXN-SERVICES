package com.kiranaservices.kirana_transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

//    @Bean
//    public DelegatingFilterProxy jwtAuthFilter() {
//        return new DelegatingFilterProxy("jwtAuthFilter");
//    }

}
