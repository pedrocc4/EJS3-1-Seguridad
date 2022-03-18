package com.bosonit.backend.asignatura.domain;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generadorAsg")
    @GenericGenerator(
            name = "generadorAsg",
            strategy = "com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ASG"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id_asignatura;

    private String nombre;
    private String descripcion;

    // Relacion con tablas

    @ManyToMany//(mappedBy = "asignaturas")
    private Set<Estudiante> estudiantes;
}
