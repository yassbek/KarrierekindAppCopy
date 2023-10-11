package com.karrierekind.karrierekindapp.controller;

import com.karrierekind.karrierekindapp.entity.SurveyQuestion;
import com.karrierekind.karrierekindapp.entity.User;
import com.karrierekind.karrierekindapp.model.CredentialsDto;
import com.karrierekind.karrierekindapp.model.RegistrationRequest;
import com.karrierekind.karrierekindapp.repository.UserRepository;
import com.karrierekind.karrierekindapp.service.SurveyQuestionService;
import com.karrierekind.karrierekindapp.service.UserService;
import com.karrierekind.karrierekindapp.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyQuestionService surveyQuestionService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User registeredUser = userService.registerUser(registrationRequest);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/survey-questions")
    public ResponseEntity<SurveyQuestion> createQuestion(@RequestBody SurveyQuestion question) {
        SurveyQuestion savedQuestion = surveyQuestionService.createQuestion(question);
        return ResponseEntity.status(201).body(savedQuestion);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users/check-email")
    public ResponseEntity<Map<String, Object>> checkUserExists(@RequestParam String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if(userOptional.isPresent()) {
            // User exists
            response.put("status", 409);
            response.put("message", "User already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 status code
        } else {
            // User doesn't exist
            response.put("status", 404);
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 status code
        }
    }



    // You can add more endpoints as required, e.g., update user, get user by ID, etc.
    @PostMapping("/checkCredentials")
    public ResponseEntity<Map<String, Boolean>> checkCredentials(@RequestBody CredentialsDto credentials) {
        boolean isValid = userService.areCredentialsValid(credentials.getEmail(), credentials.getPassword());
        Map<String, Boolean> response = Collections.singletonMap("valid", isValid);
        return ResponseEntity.ok(response);
    }
}




