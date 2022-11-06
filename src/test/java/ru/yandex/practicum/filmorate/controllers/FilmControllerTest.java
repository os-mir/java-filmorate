package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private Film film;
    private FilmController fc;

    @BeforeEach
    void filmController() {
        fc = new FilmController();

    }

    @Test
    void shouldCreateNormalFilm() {
        film = new Film(1, "qqqqq", "QQ", LocalDate.of(1987, 12, 12), 120);
        try {
            fc.createUser(film);
        } catch (ValidationException exp) {
            exp.getMessage();
        }
        assertEquals(fc.getId(), 1);
    }

    @Test
    void shouldReturnExceptionName() {
        film = new Film(1, "", "QQ", LocalDate.of(1987, 12, 12), 120);
        String error;
        try {
            fc.createUser(film);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Название не может быть пустым", error);
    }

    @Test
    void shouldReturnExceptionLargeDescription() {
        StringBuilder descriptionBuild = new StringBuilder("q");
        for (int i = 0; i <= 201; i++) {
            descriptionBuild.append("q");
        }
        String description = descriptionBuild.toString();
        film = new Film(1, "qqq", description, LocalDate.of(1987, 12, 12), 120);
        String error;
        try {
            fc.createUser(film);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Слишком длинное название", error);
    }

    @Test
    void shouldReturnExceptionOldFilm() {
        film = new Film(1, "qqq", "qqqq", LocalDate.of(1877, 12, 12), 120);
        String error;
        try {
            fc.createUser(film);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("Тогда ещё не снимали кино", error);
    }

    @Test
    void shouldReturnExceptionDuration() {
        film = new Film(1, "qqq", "qqqq", LocalDate.of(1977, 12, 12), -120);
        String error;
        try {
            fc.createUser(film);
            error = "";
        } catch (ValidationException exp) {
            error = exp.getMessage();
        }
        assertEquals("продолжительность фильма должна быть положительной", error);
    }
}