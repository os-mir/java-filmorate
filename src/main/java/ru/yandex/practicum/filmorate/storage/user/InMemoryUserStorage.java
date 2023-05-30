package ru.yandex.practicum.filmorate.storage.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryUserStorage implements UserStorage{
    private List<User> users = new ArrayList<>();
    private int id = 0;

    @Override
    public List<User> allUsers(){
         return users;}

    @Override
    public User createUser(User user) {

        user.setId(++id);
        users.add(user);

        return user;
    }

    @Override
    public User updateUser(User user) {
        int userIndex=isExist(user);

        if (userIndex!=-1) {

            users.set(userIndex,user);
        }

        return user;
    }

    @Override
    public User deleteUser(User user) {
        if (isExist(user)!=-1){
        users.remove(users);
            return user;
        };

        return null;
    }

    @Override
    public int isExist(User user){
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
