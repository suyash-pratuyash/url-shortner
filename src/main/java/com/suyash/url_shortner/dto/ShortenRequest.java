package com.suyash.url_shortner.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor

public class ShortenRequest {

    @NotBlank(message = "Original URL must not be blank")
    @URL(message = "Original URL must be a valid URL")
    private String originalUrl;
}
