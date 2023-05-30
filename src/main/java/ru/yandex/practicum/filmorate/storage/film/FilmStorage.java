package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

public interface FilmStorage {
    //@GetMapping("/films")
    public List<Film> allFilms() ;

   // @PostMapping(value = "/films")
    public Film createFilm(@Valid @RequestBody Film film) ;


   // @PutMapping(value = "/films")
    public Film updateFilm(@Valid @RequestBody Film film) ;

   // @DeleteMapping (value = "/films")
    public Film deleteFilm(@Valid @RequestBody Film film) ;

    int isExist(Film film) ;
}
