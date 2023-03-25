package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmControllerTest {

    @Test
    void shouldReturnThatFilmIsNotExist() {
        FilmController fc = new FilmController();
        String error;
        fc.createFilm(new Film(1, "qq1", "qqqq", LocalDate.of(1977, 12, 12), 22));

        fc.createFilm(new Film(2, "qq2", "qqqq", LocalDate.of(1977, 12, 12), 22));
        try {

            fc.updateFilm(new Film(3, "qq3", "qqqq", LocalDate.of(1977, 12, 12), 22));
            error = "";
        } catch (NotFoundException exp) {
            error = exp.getMessage();
        }
        assertEquals("Нет такого фильма", error);
    }
}