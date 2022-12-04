package ru.yandex.practicum.filmorate.model;

import lombok.Data;


import javax.validation.constraints.*;
import java.time.LocalDate;

@Data

public class Film {
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @Size(max=200)
    private String description;
    @Pattern(isAfter.LocalDate.of(1895, 12, 28))
    private LocalDate releaseDate;
    @PositiveOrZero
    private int duration;

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
