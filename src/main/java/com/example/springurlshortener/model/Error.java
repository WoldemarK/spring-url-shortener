package com.example.springurlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String field;
    private String value;
    private String message;

}
