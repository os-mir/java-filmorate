package ru.yandex.practicum.filmorate.model;
import ru.yandex.practicum.filmorate.annotations.DateCheck;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Film {
    private int id;
    @NotNull(message = "Название не может быть пустым")
    @NotBlank(message = "Название не может быть пустым")
    private String name;
    @Size(max = 200, message = "Слишком длинное название")
    private String description;
    @DateCheck(message = "Тогда ещё не снимали кино")
    private LocalDate releaseDate;
    @PositiveOrZero(message = "продолжительность фильма должна быть положительной")
    private int duration;

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }


}
