package com.ecotrack.carbon_tracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StartupLogger {

    @Bean
    public CommandLineRunner logStartup() {
        return args -> {
            log.info("EcoTrack Application started successfully. Active profile: {}", System.getProperty("spring.profiles.active", "default"));
        };
    }
}
