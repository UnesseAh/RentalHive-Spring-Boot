package com.rentalhive.factory.seeders;

import com.rentalhive.factory.fakers.UserFaker;
import com.rentalhive.models.entities.User;
import com.rentalhive.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {
    UserRepository userRepository;
    UserFaker userFaker;
    public UserSeeder(UserRepository userRepository, UserFaker userFaker) {
        this.userRepository = userRepository;
        this.userFaker = userFaker;
    }
    public void seed(Integer count){
        for (int i = 0; i < count; i++) {
            userRepository.save(userFaker.makeUser());
        }

    }
}
