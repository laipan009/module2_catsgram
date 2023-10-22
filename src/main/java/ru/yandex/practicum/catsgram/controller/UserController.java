package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class UserController {
    private final Map<String, User> users = new HashMap<>();

    @GetMapping("/users")
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @PostMapping("/users")
    public void create(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Invalid Email");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("User Already Exist");
        }
        users.put(user.getEmail(), user);
    }

    @PutMapping("/users")
    public void update(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Invalid Email");
        }
        users.put(user.getEmail(), user);
    }
}