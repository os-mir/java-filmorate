package ru.yandex.practicum.filmorate.services;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmService {
    private Map<Film, ArrayList<User>> likes= new HashMap<>();
    private List<User> currentFilmList;

    //берём список текущих лайков у фильма
    private List currentFilmLikesList(Film film){
        currentFilmList.clear();
        currentFilmList =likes.get(film);
        return currentFilmList;
    }
    //проверяем на наличие лайка у фильма от этого пользователя
    private boolean checkListForUser(User user){
        if (currentFilmList.contains(user)) {

            return true;
        }
        return false;
    }

    //добавление лайка
    public User addLike(Film film, User user){
        currentFilmLikesList(film);
        if (checkListForUser(user)){
            return null;
        }
        else{
            currentFilmList.add(user);
            return user;}
    }
    //удаление лайка
    public User deleteLike(Film film, User user){
        currentFilmLikesList(film);
        if (checkListForUser(user)){
            return null;
        }
        else{
            currentFilmList.remove(user);
            return user;}
    }
    //топ 10


}
