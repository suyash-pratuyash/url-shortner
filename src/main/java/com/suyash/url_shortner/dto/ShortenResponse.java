package com.suyash.url_shortner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortenResponse {

    private String shortCode;
    private String shortUrl;
}
