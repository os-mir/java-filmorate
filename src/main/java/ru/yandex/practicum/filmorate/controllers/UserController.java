package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Data
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserStorage userService = new InMemoryUserStorage();
    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.allUsers();

    }

    @PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
    }


    @PutMapping(value = "/users")
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/users")
    public User deleteUser(@Valid @RequestBody User user) {
        return userService.deleteUser(user);
    }


}
