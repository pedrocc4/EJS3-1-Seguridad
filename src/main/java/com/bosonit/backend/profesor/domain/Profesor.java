package com.bosonit.backend.profesor.domain;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generadorProfe")
    @GenericGenerator(
            name = "generadorProfe",
            strategy = "com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROF"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id_profesor;

    private String comments;

    @Column(columnDefinition = "VARCHAR(10) CHECK (branch IN ('FRONT', 'BACK', 'FULLSTACK'))")
    private String branch;

    // Relacion con tablas

    @OneToOne(fetch = FetchType.EAGER)
    private Persona id_persona;

    @OneToMany
    private List<Estudiante> estudiantes;

}
