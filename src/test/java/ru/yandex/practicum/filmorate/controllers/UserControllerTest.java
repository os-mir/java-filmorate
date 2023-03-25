package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    @Test
    void shouldReturnThatUserIsNotExist() {


        UserController uc = new UserController();
        String error;
        uc.createUser(new User(1, "qqq@qqq.qq", "QQQ", "QQ QQQ", LocalDate.of(2023, 12, 12)));
        uc.createUser(new User(2, "qqq@qqq.qq", "QQQ", "QQ QQQ", LocalDate.of(2023, 12, 12)));
        try {
            uc.updateUser(new User(3, "qqq@qqq.qq", "QQQ", "QQ QQQ", LocalDate.of(2023, 12, 12)));
            error = "";
        } catch (NotFoundException exp) {
            error = exp.getMessage();
        }
        assertEquals("Нет такого пользователя", error);
    }
}