package com.bosonit.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.bosonit.backend.persona",
        "com.bosonit.backend.estudiante",
        "com.bosonit.backend.profesor",
        "com.bosonit.backend.asignatura",
        "com.bosonit.backend.utils"
})
@EntityScan(basePackages = {
        "com.bosonit.backend.persona.domain",
        "com.bosonit.backend.estudiante.domain",
        "com.bosonit.backend.profesor.domain",
        "com.bosonit.backend.estudiante_asignatura.domain",
        "com.bosonit.backend.asignatura.domain"
})
@EnableJpaRepositories({
        "com.bosonit.backend.persona.repository",
        "com.bosonit.backend.estudiante.repository",
        "com.bosonit.backend.profesor.repository",
        "com.bosonit.backend.asignatura.repository"
})
public class EscuelaApp {

    public static void main(String[] args) {
        SpringApplication.run(EscuelaApp.class, args);
    }
}
