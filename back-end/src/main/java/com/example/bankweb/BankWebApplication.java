package com.example.bankweb;

import com.example.bankweb.Request.AuthenticationRequest;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.repository.IUserRepository;
import com.example.bankweb.service.abstracts.ICreditService;
import com.example.bankweb.service.abstracts.IUserService;
import com.example.bankweb.service.concretes.AuthenticationService;
import com.example.bankweb.service.concretes.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//http://localhost:2023/swagger-ui/index.html#/
public class BankWebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankWebApplication.class, args);
    }

    @Autowired
    SmsService smsService;
    @Autowired
    IUserService customerService;
    @Autowired
    IUserRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ICreditService creditService;
    @Override
    public void run(String... args) throws Exception {

    }
}
