package com.example.demo.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class LockService {

    private final RedisDistributedLock lock;

    public LockService(RedisDistributedLock lock) {
        this.lock = lock;
    }

    public void performWithLock(String lockKey) throws InterruptedException {

		try {
			if (lock.acquireLock(lockKey, 15000, TimeUnit.MILLISECONDS)) {
				log.info("Lock acquired. Operation started.");

			    Thread.sleep(200);
			    //write to postgres
			    
			    log.info("Operation completed.");

			    // if you want, you can release lock.
			    lock.releaseLock(lockKey);
			} else {
				log.info("Failed to acquire lock. Resource is busy.");
			}
		} catch (InterruptedException e) {
		} finally {
			lock.releaseLock(lockKey);
		}

    }
}