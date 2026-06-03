package com.natalia.tasktracker.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FieldValidationException {

    private String field;
    private String message;

}
