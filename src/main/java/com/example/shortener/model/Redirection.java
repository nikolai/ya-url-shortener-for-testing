package com.example.shortener.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Redirections")
public class Redirection {
    private String longUrl;
    @Id
    private String shortKey;

    public Redirection() {
    }

    public Redirection(String longUrl, String shortKey) {
        this.longUrl = longUrl;
        this.shortKey = shortKey;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortKey() {
        return shortKey;
    }
}
