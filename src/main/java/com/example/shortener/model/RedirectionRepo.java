package com.example.shortener.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public interface RedirectionRepo extends CrudRepository<Redirection, String> {
    Optional<Redirection> findBySecretKey(String secretKey);
}
