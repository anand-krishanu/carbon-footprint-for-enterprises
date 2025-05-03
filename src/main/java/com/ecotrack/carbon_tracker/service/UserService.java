package com.ecotrack.carbon_tracker.service;

import com.ecotrack.carbon_tracker.entity.User;
import com.ecotrack.carbon_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUser () {
        return userRepository.findAll();
    }

    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

    }

    public User updateUser(Long id, User newUser) {
        User user = userRepository.findById(id).get();
        user.setUsername(newUser.getUsername());
        user.setRole(newUser.getRole());

        if(user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser (long id) {
        userRepository.deleteById(id);
    }
}
