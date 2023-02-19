package com.example.springurlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Url {
    private String id;
    private String url;
    private LocalDateTime created;


}
