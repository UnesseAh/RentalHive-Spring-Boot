package com.rentalhive.services.user;

import com.rentalhive.handlers.exceptionHandler.ResourceNotFoundException;
import com.rentalhive.controllers.vm.UserVM;
import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;
import com.rentalhive.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        user.setRole(Role.Client);
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id: " + id + " not found"));
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    @Override
    public User updateUser(Long id,User user) {
        getUserById(id);
        user.setId(id);
        return userRepository.save(user);
    }

}
