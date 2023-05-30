package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserStorage {
   // @GetMapping("/users")
    public List<User> allUsers();

    //@PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user) ;

    //@PutMapping(value = "/users")
    public User updateUser(@Valid @RequestBody User user) ;

    //@DeleteMapping(value = "/users")
    public User deleteUser(@Valid @RequestBody User user) ;

    int isExist(User user);
}
