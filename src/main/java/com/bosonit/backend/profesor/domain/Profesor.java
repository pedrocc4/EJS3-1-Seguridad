package com.bosonit.backend.profesor.domain;

import com.bosonit.backend.estudiante.domain.Estudiante;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador")
    @GenericGenerator(
            name = "generador",
            strategy = "com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    private String id_profesor;

    private String comments;

    private String branch;

    // Relacion con tablas

//    @OneToOne
//    private Persona id_persona;

    @OneToMany
    private List<Estudiante> estudiantes;

}
