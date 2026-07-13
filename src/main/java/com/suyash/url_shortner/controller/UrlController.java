package com.suyash.url_shortner.controller;


import com.suyash.url_shortner.dto.ShortenRequest;
import com.suyash.url_shortner.dto.ShortenResponse;
import com.suyash.url_shortner.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@Valid @RequestBody ShortenRequest request) {
        String shortCode = urlService.shortenUrl(request.getOriginalUrl());
        String shortUrl = "http://localhost:8080/" + shortCode;

        ShortenResponse response = new ShortenResponse(shortCode, shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
