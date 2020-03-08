package com.example.shortener;

import com.example.shortener.model.Redirection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShortenerApplication {
	public static List<Redirection> garbage = new ArrayList<>();
	public static List<Redirection> garbageForOldGen = new ArrayList<>();
	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
}
