package com.bosonit.backend.estudiante_asignatura.domain;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Estudiante_Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EA_generador")
    @GenericGenerator(
            name = "EA_generador",
            strategy = "com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST-ASG"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id_estudiante_asignatura;

    private String comments;

    @NotNull
    private Date initial_date;

    private Date finish_date;

    @ManyToOne
    //@MapsId("id_estudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante id_estudiante;

    @ManyToOne
   // @MapsId("id_asignatura")
    @JoinColumn(name = "id_asignatura")
    private Asignatura id_asignatura;

}
