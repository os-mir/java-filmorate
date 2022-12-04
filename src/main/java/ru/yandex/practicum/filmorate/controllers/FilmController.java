package ru.yandex.practicum.filmorate.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ExceptionExisting;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Data
public class FilmController {
    private List<Film> films = new ArrayList<>();
    int id = 0;

    @GetMapping("/films")
    public List<Film> allUsers() {
        return films;
    }

    @PostMapping(value = "/films")
    public Film createUser(@RequestBody Film film) {
        try {
            validationFilm(film);
            film.setId(++id);
            films.add(film);
        } catch (ValidationException exp) {
            throw new ValidationException(exp.getMessage());
        }
        return film;
    }


    @PutMapping(value = "/films")
    public Film updateUser(@RequestBody Film film) {
        boolean exist = false;
        try {
            for (Film filmExisted : films) {
                if (filmExisted.getId() == film.getId()) {
                    exist = true;
                    try {
                        validationFilm(film);

                        filmExisted.setName(film.getName());
                        filmExisted.setDescription(film.getDescription());
                        filmExisted.setDuration(film.getDuration());
                        filmExisted.setReleaseDate(film.getReleaseDate());
                    } catch (ValidationException exp) {
                        log.debug(exp.getMessage());
                        throw new ValidationException(exp.getMessage());
                    }
                    break;
                }
            }

            if (!exist) {
                throw new ExceptionExisting("Нет такого фильма");
            }
        } catch (ExceptionExisting err) {
            throw new ExceptionExisting(err.getMessage());
        }
        return film;
    }

    public static void validationFilm(Film film) {
        if (film.getName().isEmpty()) {
            throw new ValidationException("Название не может быть пустым");
        } else if (film.getDescription().length() > 200) {
            throw new ValidationException("Слишком длинное название");
        } else if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Тогда ещё не снимали кино");
        } else if (film.getDuration() <= 0) {
            throw new ValidationException("продолжительность фильма должна быть положительной");
        }
    }
}
