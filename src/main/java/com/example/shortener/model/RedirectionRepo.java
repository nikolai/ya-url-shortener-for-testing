package com.example.shortener.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public interface RedirectionRepo extends CrudRepository<Redirection, String> {
//    private final Map<String, Redirection> repo = new ConcurrentHashMap<>();
//
//    public void addRedirection(Redirection redirection){
//        if (repo.putIfAbsent(redirection.getShortKey(), redirection) != null) {
//            throw new AlreadyMappedShortKeyException(redirection.getShortKey());
//        }
//    }
//
//    public Redirection getRedirection(String shortKey) {
//        return repo.get(shortKey);
//    }
}
