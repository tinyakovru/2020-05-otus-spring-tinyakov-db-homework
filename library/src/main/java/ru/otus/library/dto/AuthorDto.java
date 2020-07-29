package ru.otus.library.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AuthorDto {
    private final String firstName;
    private final String middleName;
    private final String lastName;
}
