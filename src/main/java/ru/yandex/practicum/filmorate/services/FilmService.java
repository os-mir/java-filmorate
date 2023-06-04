package ru.yandex.practicum.filmorate.services;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;
@Service
public class FilmService {
    private Map<Film, ArrayList<User>> likes = new HashMap<>();
    private ArrayList<User> currentFilmList;

    //берём список текущих лайков у фильма
    private List currentFilmLikesList(Film film) {
        currentFilmList.clear();
        currentFilmList = likes.get(film);
        return currentFilmList;
    }

    //проверяем на наличие лайка у фильма от этого пользователя
    private boolean checkListForUser(User user) {
        if (currentFilmList.contains(user)) {

            return true;
        }
        return false;
    }

    //добавление лайка
    public User addLike(Film film, User user) {
        currentFilmLikesList(film);
        if (checkListForUser(user)) {
            return null;
        } else {
            currentFilmList.add(user);
            likes.put(film, currentFilmList);
            return user;
        }
    }

    //удаление лайка
    public User deleteLike(Film film, User user) {
        currentFilmLikesList(film);
        if (checkListForUser(user)) {
            return null;
        } else {
            currentFilmList.remove(user);
            likes.put(film, currentFilmList);
            return user;
        }
    }


    //топ 10
    public List<SortItem> topTen() {
        List<SortItem> likeCount = new ArrayList<>();

        for (Film film : likes.keySet()) {
            likeCount.add(new SortItem(film, likes.get(film).size()));
        }
        SortingComparator sortingComparator = new SortingComparator();
        likeCount.sort(sortingComparator);
        List<SortItem> likes10 = new ArrayList<>();
        for(int i=0;i<10;i++){
            likes10.add(likeCount.get(i));
        }

        return likes10;
    }

    public class SortItem {
        public final Film film;
        public final Integer likesCount;

        public SortItem(Film film, Integer likesCount) {
            this.film = film;
            this.likesCount = likesCount;
        }
    }


    static class SortingComparator implements Comparator<SortItem> {
        @Override
        public int compare(SortItem item1, SortItem item2) {

            // сравниваем товары — более дорогой должен быть дальше в списке

            if (item1.likesCount > item2.likesCount) {
                return -1;

                // более дешёвый — ближе к началу списка
            } else if (item1.likesCount  < item2.likesCount) {
                return 1;

                // если стоимость равна, нужно вернуть 0
            } else {
                return 0;
            }
        }
    }
}