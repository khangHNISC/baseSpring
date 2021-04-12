package com.example.base.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CachingService {

    @Cacheable("dto")
    public DTO getDTO(String id) throws InterruptedException {
        log.info("working first time");
        simulateSlowService();
        return new DTO(id);
    }

    private void simulateSlowService() throws InterruptedException {
        long time = 3000L;
        Thread.sleep(time);
    }
}
