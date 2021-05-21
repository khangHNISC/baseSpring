package com.example.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ScheduledTasksTest {

    @SpyBean
    private ScheduledTasks scheduledTasks;

    @Test
    void whenAwait10Sec_thenScheduleCall2() {
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> verify(scheduledTasks, atLeast(2)).reportCurrentTime());
    }
}