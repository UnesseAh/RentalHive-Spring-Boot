package com.rentalhive.factory.fakers;

import com.github.javafaker.Faker;
import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFaker {

    private Faker facker;


    public UserFaker() {
        this.facker = new Faker();
    }
    public User makeUser(){
        return new User().builder()
                .email(facker.internet().emailAddress())
                .name(facker.name().fullName())
                .phoneNumber(facker.phoneNumber().phoneNumber())
                .role(facker.options().option(Role.Client, Role.Manager, Role.Agent))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
