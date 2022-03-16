package com.bosonit.backend.estudiante_asignatura.domain;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Estudiante_asignatura {
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
    private String id_estudiante_asignatura;

    private String comments;

    private String branch;

    @NotNull
    private Date initial_date;

    private Date finish_date;

    // Relacion tablas

    @OneToMany(fetch = FetchType.LAZY)
    private List<Asignatura> asignatura;

}
