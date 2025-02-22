package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.User;
import com.example.cryptotrading.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UserService {
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long Id) {
        return userRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long Id, User user) {
        User userToUpdate = userRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userToUpdate.getName());
        user.setSurname(userToUpdate.getSurname());
        user.setPassword(userToUpdate.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}