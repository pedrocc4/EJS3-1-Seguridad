package com.bosonit.backend.itinerariocontinuacion.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.bosonit.backend.itinerariocontinuacion.REST",
        "com.bosonit.backend.itinerariocontinuacion.servicios",
        "com.bosonit.backend.itinerariocontinuacion.repositorios"
})
@EntityScan(basePackages = "com.bosonit.backend.itinerariocontinuacion.entidades")
@EnableJpaRepositories("com.bosonit.backend.itinerariocontinuacion.repositorios")
public class ItinerarioContinuacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItinerarioContinuacionApplication.class, args);
    }
}
