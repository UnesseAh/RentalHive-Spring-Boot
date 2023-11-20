package com.rentalhive;

import com.rentalhive.factory.seeders.UserSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RentalHiveSpringBootApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalHiveSpringBootApiApplication.class, args);
    }


}
