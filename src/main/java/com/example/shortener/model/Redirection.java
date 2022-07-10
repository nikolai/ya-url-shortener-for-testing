package com.example.shortener.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "redirections")
public class Redirection {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String shortKey;
    @Column(unique=true)
    private String secretKey;

    private String longUrl;

    private Date creationDate;
    private long usageCount;

    public Redirection() {} // needed by hibernate

    public Redirection(String longUrl, String shortKey, String secretKey) {
        this.longUrl = longUrl;
        this.shortKey = shortKey;
        this.secretKey = secretKey;
        this.creationDate = new Date();
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

    public void incrementUsageCount() {
        usageCount++;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getUsageCount() {
        return usageCount;
    }
}
