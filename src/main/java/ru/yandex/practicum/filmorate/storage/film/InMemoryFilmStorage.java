package ru.yandex.practicum.filmorate.storage.film;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@Validated
@Data
@Component
public class InMemoryFilmStorage implements FilmStorage{
    private List<Film> films = new ArrayList<>();
    private int id = 0;

    @Override
    public  List<Film> allFilms() {

        return films;
    }

    @Override
    public Film createFilm(Film film) {
        film.setId(++id);
        films.add(film);

        return film;
    }

    @Override
    public Film updateFilm(Film film) {

        int filmIndex = isExist(film);

        if (filmIndex != -1) {

            films.set(filmIndex, film);
        }
        return film;
    }

    @Override
    public Film deleteFilm(Film film) {
        if (isExist(film)!=-1){
            films.remove(film);
            return film;
        };

        return null;
    }

    @Override
    public int isExist(Film film) {

            boolean exist = false;

            for (Film flm : films) {
                if (flm.getId() == film.getId()) {
                    exist = true;
                    return films.indexOf(flm);
                }
            }
            if (!exist) throw new NotFoundException("Нет такого фильма");

            return -1;
        }



}
