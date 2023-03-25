package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class User {
    private int id;
    @Email(message = "Не верный формат почты")
    private String email;
    @NotNull(message="Логин не может быть пустым или с пробелами")
    @NotBlank(message="Логин не может быть пустым или с пробелами")
    private String login;
    private String name;
    @PastOrPresent(message="Дата рождения не может быть в будущем")
    private LocalDate birthday;


    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name=name;
        if (this.name == null) {
            this.name=this.login;
          }


        this.birthday = birthday;
    }

}
