package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Data
public class UserController {
    private List<User> users = new ArrayList<>();
    private int id = 0;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public List<User> allUsers() {
        return users;
    }

    @PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user) {

            user.setId(++id);
            users.add(user);

        return user;
    }


    @PutMapping(value = "/users")
    public User updateUser(@Valid @RequestBody User user) {
        int userIndex=isExist(user);

               if (userIndex!=-1) {

                   users.set(userIndex,user);
                                   }

        return user;
    }


    int isExist(User user){
        boolean exist = false;

        for (User usr : users) {
            if (usr.getId() == user.getId()) {
                exist = true;
                return users.indexOf(usr);
            }
        }
        if (!exist) {
            throw new NotFoundException("Нет такого пользователя");
        }

            return -1;
    }
}
