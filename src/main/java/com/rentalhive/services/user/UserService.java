package com.rentalhive.services.user;

import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.models.entities.User;

import java.util.List;

public interface UserService {

    public UserDTO getUserById(Long id);
    public UserDTO saveUser(UserDTO user);
    public UserDTO updateUser(UserDTO user);
    public boolean deleteUser(Long id);
    public List<UserDTO> getAllUsers();
}
