package com.example.demo.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronService {

    private final RedisDistributedLock lock;
    private final String LOCK_KEY = "lock-key";

    @Autowired
    public CronService(RedisDistributedLock lock) {
        this.lock = lock;
    }

    @Scheduled(fixedDelay = 15000L)
    private void cronMethod() throws InterruptedException {
        System.out.println("Cron job running..");

        if (lock.acquireLock(this.LOCK_KEY, 15000, TimeUnit.MILLISECONDS)) {
        	System.out.println("Lock acquired. Operation started.");

            Thread.sleep(200);

            System.out.println("Operation completed.");
        } else {
        	System.out.println("Failed to acquire lock. Resource is busy.");
        }

    }
}
