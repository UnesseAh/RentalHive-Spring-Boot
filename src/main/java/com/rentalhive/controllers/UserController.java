package com.rentalhive.controllers;

import com.rentalhive.handlers.response.ResponseMessage;
import com.rentalhive.models.dto.UserDTO;
import com.rentalhive.models.entities.User;
import com.rentalhive.repositories.UserRepository;
import com.rentalhive.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> getAllTutorials() {List<UserDTO> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(),users,HttpStatus.OK.toString()), HttpStatus.OK);

    }
    @PostMapping("/users")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO user_dto_created = userService.saveUser(userDTO);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED.value(), user_dto_created,HttpStatus.CREATED.toString()), HttpStatus.CREATED);
    }

//    @GetMapping("/tutorials/{id}")
//    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
//        Tutorial tutorial = tutorialRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
//
//        return new ResponseEntity<>(tutorial, HttpStatus.OK);
//    }
//
//    @PostMapping("/tutorials")
//    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
//        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
//        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/tutorials/{id}")
//    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//        Tutorial _tutorial = tutorialRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
//
//        _tutorial.setTitle(tutorial.getTitle());
//        _tutorial.setDescription(tutorial.getDescription());
//        _tutorial.setPublished(tutorial.isPublished());
//
//        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/tutorials/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//        tutorialRepository.deleteById(id);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/tutorials")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//        tutorialRepository.deleteAll();
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/tutorials/published")
//    public ResponseEntity<List<Tutorial>> findByPublished() {
//        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
//
//        if (tutorials.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(tutorials, HttpStatus.OK);
//    }
}
