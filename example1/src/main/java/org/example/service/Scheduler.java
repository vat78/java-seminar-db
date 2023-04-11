package org.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Scheduler {

    private final DataGenerator dataGenerator;

    @Scheduled(fixedRate = 5000)
    public void scheduledTask() {
        dataGenerator.generateData();
    }
}
