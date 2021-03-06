package ru.otus.library.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@Data
@RequiredArgsConstructor
public class BookDto {
    private final long id;

    private final String title;
    private final Set<Long> authorsIds;
    private final Set<Long> genresIds;

}
