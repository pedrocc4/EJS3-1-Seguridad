package com.bosonit.backend.persona.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Size(min = 6, max = 10)
    @Column(unique = true)
    String usuario;

    @NotNull
    String password;

    @NotNull
    String name;

    String surname;

    @NotNull
    @Email
    String company_email;

    @NotNull
    @Email
    String personal_email;

    @NotNull
    String city;

    @NotNull
    Boolean active;

    @NotNull
    Date created_date;

    String imagen_url;

    Date termination_date;
/*
    public Persona(String usuario, String password, String name, String company_email, String personal_email, String city, Boolean active, Date created_date) {
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
        this.active = active;
        this.created_date = created_date;
    }
*/
}
