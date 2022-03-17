package com.bosonit.backend.estudiante.infrastructure.controller.dto;

import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class EstudiantePersonaOutputDTO {
    private String id_estudiante;
    private int num_hours_week;
    private String comments;
    private String branch;
    private Persona id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
