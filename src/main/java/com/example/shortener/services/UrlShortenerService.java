package com.example.shortener.services;

import com.example.shortener.model.RandomKeyGen;
import com.example.shortener.model.Redirection;
import com.example.shortener.model.RedirectionNotFoundException;
import com.example.shortener.model.RedirectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.shortener.ShortenerApplication.garbage;
import static com.example.shortener.ShortenerApplication.garbageForOldGen;

@Service
public class UrlShortenerService {
    private Lock lockForBadSynchronization = new ReentrantLock();
    private List<Lock> resourcesForDeadlock = Arrays.asList(new ReentrantLock(), new ReentrantLock());

    @Autowired
    private RedirectionRepo repo;
    @Autowired
    private RandomKeyGen gen;

    @Value("${show.memory.leakage}")
    boolean showMemoryLeakage;

    @Value("${show.unnecessary.synchronization}")
    boolean showUnnecessarySynchronization;

    @Value("${shortKeySize}")
    private int shortKeySize = 3;

    @Value("${application.domain}")
    private String appDomain = "localhost";

    @Value("${application.protocol}")
    private String protocol = "http";

    @Value("${server.port}")
    private String serverPort;

    @Value("${internStrings}")
    private boolean internStrings;

    @Value("${show.deadlock}")
    private boolean showDeadlock;

    @Value("${workThreadSleepTimeMs}")
    private long workThreadSleepTimeMs;

    @Value("${showOldGenEating}")
    private boolean showOldGenEating;

    private long lastGarbageForOldGenCleanupTime = System.currentTimeMillis();
    private long garbageForOldGenCleanupPeriodMs = 60000;

    @Autowired
    private DynConfService dynConfService;

    @PostConstruct
    void postConstruct() {
        dynConfService.addBean(this);
    }

    public String shorten(String longUrl) {
        String shortKey = gen.generateKey(shortKeySize);

        Lock deadlockable = null;
        if (showDeadlock) {
            deadlockable = resourcesForDeadlock.get(ThreadLocalRandom.current().nextInt(resourcesForDeadlock.size()));
            deadlockable.lock();
        }

        if (showUnnecessarySynchronization) {
            lockForBadSynchronization.lock();
        }

        try {
            try {
                if (workThreadSleepTimeMs > 0) Thread.sleep(workThreadSleepTimeMs);
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted, sorry!");
            }
            Redirection redirection = new Redirection(internOrNot(longUrl.intern()), internOrNot(shortKey.intern()));
            repo.save(redirection);
            if (showMemoryLeakage) {
                garbage.add(redirection);
            }

            if (showOldGenEating) {
                garbageForOldGen.add(redirection);
                if ((System.currentTimeMillis() - lastGarbageForOldGenCleanupTime) > garbageForOldGenCleanupPeriodMs) {
                    garbageForOldGen.clear();
                }
            }
        } finally {
            if (showUnnecessarySynchronization) lockForBadSynchronization.unlock();
            if (deadlockable != null) deadlockable.unlock();
        }
        return protocol + "://" + appDomain + ":" + serverPort + "/" + shortKey;
    }

    private String internOrNot(String s) {
        return internStrings && s != null ? s.intern() : s;
    }

    public Redirection resolve(String shortKey) throws RedirectionNotFoundException {
        Optional<Redirection> redirection = repo.findById(shortKey);
        if (redirection.isPresent()) {
            return redirection.get();
        }
        throw new RedirectionNotFoundException(shortKey);
    }
}
