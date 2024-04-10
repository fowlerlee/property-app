package com.example.demo.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LockService {

    private final RedisDistributedLock lock;

    @Autowired
    public LockService(RedisDistributedLock lock) {
        this.lock = lock;
    }

    public void performWithLock(String lockKey) throws InterruptedException {

		try {
			if (lock.acquireLock(lockKey, 15000, TimeUnit.MILLISECONDS)) {
			    System.out.println("Lock acquired. Operation started.");

			    Thread.sleep(200);
			    //write to postgres
			    
			    System.out.println("Operation completed.");

			    // if you want, you can release lock.
			    lock.releaseLock(lockKey);
			} else {
				System.out.println("Failed to acquire lock. Resource is busy.");
			}
		} catch (InterruptedException e) {
		} finally {
			lock.releaseLock(lockKey);
		}

    }
}