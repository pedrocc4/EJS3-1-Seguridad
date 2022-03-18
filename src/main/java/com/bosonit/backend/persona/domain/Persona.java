package com.bosonit.backend.persona.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Persona { //TODO una persona solo puede ser o estudiante o profesor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 6, max = 10)
    @Column(unique = true)
    private String usuario;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private String surname;

    @NotNull
    @Email
    private String company_email;

    @NotNull
    @Email
    private String personal_email;

    @NotNull
    private String city;

    @NotNull
    private Boolean active;

    @NotNull
    private Date created_date;

    private String imagen_url;

    private Date termination_date;

    // @Transient
    private TipoPersona tipoPersona;

    @Getter
    public
    enum TipoPersona {
        ESTUDIANTE(),
        PROFESOR()
    }
}