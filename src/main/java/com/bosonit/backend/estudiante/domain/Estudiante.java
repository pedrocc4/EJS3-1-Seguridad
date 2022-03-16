package com.bosonit.backend.estudiante.domain;

import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador")
    @GenericGenerator(
            name = "generador",
            strategy = "com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id_estudiante;

    @NotNull
    private int num_hours_week;

    private String comments;

    private String branch;

    // Relacion con tablas

    @OneToOne
    private Persona id_persona;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_profesor")
//    private Profesor profesor;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "estudiante_asignatura")
//    private List<Estudiante_asignatura> asignaturas;

}
