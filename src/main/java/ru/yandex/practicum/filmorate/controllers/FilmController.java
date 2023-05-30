package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated
@Data
public class FilmController {
    //private List<Film> films = new ArrayList<>();
    //private int id = 0;
    private final FilmStorage filmService = new InMemoryFilmStorage();

    @GetMapping("/films")
    public List<Film> allFilms() {

        return filmService.allFilms();
    }

    @PostMapping(value = "/films")
    public Film createFilm(@Valid @RequestBody Film film) {

        return  filmService.createFilm(film);
    }


    @PutMapping(value = "/films")
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmService.updateFilm(film);
    }




}
