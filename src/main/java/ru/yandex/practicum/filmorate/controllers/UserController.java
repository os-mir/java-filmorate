package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ExceptionExisting;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
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
    public User createUser(@RequestBody User user) {
        try {
            validation(user);
            user.setId(++id);
            users.add(user);
        } catch (ValidationException exp) {
            log.debug(exp.getMessage());
            throw new ValidationException(exp.getMessage());
        }
        return user;
    }


    @PutMapping(value = "/users")
    public User updateUser(@RequestBody User user) {
        try {
            boolean exist = false;
            for (User usr : users) {
                if (usr.getId() == user.getId()) {
                    exist = true;
                    try {
                        validation(user);
                        usr.setLogin(user.getLogin());
                        usr.setName(user.getName());
                        usr.setBirthday(user.getBirthday());
                        usr.setEmail(user.getEmail());
                    } catch (ValidationException exp) {
                        log.debug(exp.getMessage());
                        throw new ValidationException(exp.getMessage());
                    }
                    break;
                }
            }
            if (exist == false) {
                throw new ExceptionExisting("Нет такого пользователя");
            }

        } catch (ExceptionExisting err) {
            log.info(err.getMessage());
            throw new ExceptionExisting(err.getMessage());
        }
        return user;
    }

    private static void validation(User user) {
        try {
            if (user.getEmail().isEmpty()) {
                throw new ValidationException("Наименование почты не быть пустым");
            } else if (!user.getEmail().contains("@")) {
                throw new ValidationException("Не верный формат почты");
            } else if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
                throw new ValidationException("Логин не может быть пустым или с пробелами");
            } else if (user.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("Дата рождения не может быть в будущем");
            }
            if (user.getName() == null) {
                user.setName(user.getLogin());
            }
        } catch (ValidationException exp) {
            log.debug(exp.getMessage());
            throw new ValidationException(exp.getMessage());
        }
    }
}
