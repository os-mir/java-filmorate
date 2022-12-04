package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private User user;
    private UserController uc;


    @BeforeEach
    void userController() {
        uc = new UserController();
    }

    @Test
    void shouldCreateNormalUser() {
        user = new User(1, "qqq@qqq.qq", "QQQ", "QQ QQQ", LocalDate.of(1987, 12, 12));
        try {
            uc.createUser(user);
        } catch (ValidationException exp) {
            exp.getMessage();
        }
        assertEquals(uc.getId(), 1);
    }

    @Test
    void shouldReturnExceptionEmail() {
        user = new User(1, "qqqqqq.qq", "QQQ", "QQ QQQ", LocalDate.of(1987, 12, 12));
        String error;
        try {
            uc.createUser(user);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Не верный формат почты", error);
    }

    @Test
    void shouldReturnExceptionEmailEmpty() {
        user = new User(1, "", "QQ QQQ", "", LocalDate.of(1987, 12, 12));
        String error;
        try {
            uc.createUser(user);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Наименование почты не быть пустым", error);
    }

    @Test
    void shouldReturnExceptionLogin() {
        user = new User(1, "qqq@qqq.qq", "QQQ QQ", "QQ QQQ", LocalDate.of(1987, 12, 12));
        String error1;
        String error2;
        try {
            uc.createUser(user);
            error1 = "";
        } catch (ValidationException exp) {
            error1 = exp.getMessage();
        }
        user = new User(1, "qqq@qqq.qq", "", "QQ QQQ", LocalDate.of(1987, 12, 12));
        try {
            uc.createUser(user);
            error2 = "";
        } catch (ValidationException exp) {
            error2 = exp.getMessage();
        }
        assertEquals("Логин не может быть пустым или с пробелами", error1);
        assertEquals("Логин не может быть пустым или с пробелами", error2);
    }

    @Test
    void shouldReturnExceptionDateInFuture() {
        user = new User(1, "qqq@qqq.qq", "QQQ", "QQ QQQ", LocalDate.of(2023, 12, 12));

        String error;
        try {
            uc.createUser(user);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Дата рождения не может быть в будущем", error);
    }
}