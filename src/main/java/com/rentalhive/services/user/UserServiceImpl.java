package com.rentalhive.services.user;

import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.models.entities.User;
import com.rentalhive.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }
    @Override
    public UserDTO saveUser(@Validated UserDTO userDTO) {
        try {
            User user = userRepository.save(userDTO.toUser());
            return UserDTO.fromUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }
    @Override
    public UserDTO updateUser(UserDTO user) {
        return null;
    }
    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        try{
            List<UserDTO> users = new ArrayList<>();
            userRepository.findAll().forEach(user -> users.add(UserDTO.fromUser(user)));
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all users", e);
        }
    }
}
