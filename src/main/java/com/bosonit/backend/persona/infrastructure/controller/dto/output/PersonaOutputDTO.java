package com.bosonit.backend.persona.infrastructure.controller.dto.output;

import com.bosonit.backend.persona.domain.Persona;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaOutputDTO {
    private int id;
    private String usuario;
    private String name;
    private String surname;
    private String password;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private boolean admin;
    private Persona.TipoPersona tipoPersona;
}
