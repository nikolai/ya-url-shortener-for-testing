package com.example.shortener.messages;

import java.util.Date;

public class AdminGetStatsResponse {
    private final Date creationDate;
    private final long usageCount;

    public AdminGetStatsResponse(Date creationDate, long usageCount) {
        this.creationDate = creationDate;
        this.usageCount = usageCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getUsageCount() {
        return usageCount;
    }
}
