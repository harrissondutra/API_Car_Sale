package com.carsale.cars.infra.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ScheduleService {

    @Scheduled(cron = "${spring.task.scheduling.cron}")
    @EventListener(ApplicationReadyEvent.class)
    public void scheduleTask(){
        log.info("Agendado e executado em {}", LocalDateTime.now());
    }
}