package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.controllers.vm.UserVM;
import com.rentalhive.models.entities.User;
import com.rentalhive.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody UserVM userVM){
        return ResponseMessage.created(
                userService.createUser(userVM.toUser()),
                "User created successfully");
    }
    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        List<User> users = userService.getAllUsers();
        if ( users == null ){
            return ResponseMessage.notFound("No users found");
        }
        return ResponseMessage.ok(users.stream().map(UserVM::fromUser).toList(),
                "Users retrieved successfully");
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity getUserById(@PathVariable Long userId){
        return ResponseMessage.ok(userService.getUserById(userId),
                "User retrieved successfully");
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId,@Valid @RequestBody UserVM userVM){
        return ResponseMessage.ok(userService.updateUser(userId,userVM.toUser()),
                "User updated successfully");
    }




}
