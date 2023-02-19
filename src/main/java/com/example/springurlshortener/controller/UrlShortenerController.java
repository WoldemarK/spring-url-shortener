package com.example.springurlshortener.controller;

import com.example.springurlshortener.model.Error;
import com.example.springurlshortener.model.Url;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rest/url")
public class UrlShortenerController {

    private final RedisTemplate<String, Url> redisTemplate;

    @Value("${redis.ttl}")
    private long ttl;

    @GetMapping("/{id}")
    public ResponseEntity getUrl(@PathVariable("id") String id) {

        Url url = redisTemplate.opsForValue().get(id);

        if (url == null) {
            Error error = new Error("id", id, "No such key exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(url);
    }

    @PostMapping()
    public ResponseEntity createUrl(@RequestBody @NotNull Url url) {
        UrlValidator validator = new UrlValidator(new String[]{"http", "https"});

        if (!validator.isValid(url.getUrl())) {
            Error error = new Error("url", url.getUrl(), "Invalid URL");
            return ResponseEntity.badRequest().body(error);
        }

        String id = Hashing.murmur3_32().hashString(url.getUrl(), StandardCharsets.UTF_8).toString();
        url.setId(id);
        url.setCreated(LocalDateTime.now());

        redisTemplate.opsForValue().set(url.getId(), url, ttl, TimeUnit.SECONDS);

        return ResponseEntity.ok(url);
    }
}