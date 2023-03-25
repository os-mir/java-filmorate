package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated
@Data
public class FilmController {
    private List<Film> films = new ArrayList<>();
    private int id = 0;

    @GetMapping("/films")
    public List<Film> allFilms() {

        return films;
    }

    @PostMapping(value = "/films")
    public Film createFilm(@Valid @RequestBody Film film) {

        film.setId(++id);
        films.add(film);

        return film;
    }


    @PutMapping(value = "/films")
    public Film updateFilm(@Valid @RequestBody Film film) {

        int filmIndex = isExist(film);

        if (filmIndex != -1) {

            films.set(filmIndex, film);
        }
        return film;
    }


    int isExist(Film film) {
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
