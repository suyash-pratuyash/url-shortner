package com.suyash.url_shortner.repository;

import com.suyash.url_shortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShorCode(String shortCode);
}
