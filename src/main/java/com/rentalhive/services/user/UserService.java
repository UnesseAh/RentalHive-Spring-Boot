package com.rentalhive.services.user;

import com.rentalhive.models.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id,User user);
}
