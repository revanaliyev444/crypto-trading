package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.entity.User;
import com.example.cryptotrading.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("v1/users")
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping({"/Id"})
    public ResponseEntity<?> getAllUsersById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @DeleteMapping({"/Id"})
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}