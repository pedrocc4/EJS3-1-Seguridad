package com.bosonit.backend.estudiante.domain;

import com.bosonit.backend.estudiante_asignatura.domain.Estudiante_Asignatura;
import com.bosonit.backend.persona.domain.Persona;
import com.bosonit.backend.utils.StringPrefixedSequenceIdGenerator;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Column(columnDefinition = "VARCHAR(10) CHECK (branch IN ('FRONT', 'BACK', 'FULLSTACK'))")
    private String branch;

    // Relacion con tablas

    @OneToOne(fetch = FetchType.EAGER)
    private Persona id_persona;

    //    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//    }, // sin REMOVE (eliminar asignatura y no estudiantes)
//            fetch = FetchType.LAZY
//    )
//    @JoinTable(
//            name = "ESTUDIANTE_ASIGNATURA",
//            joinColumns = {@JoinColumn(name = "id_estudiante")},
//            inverseJoinColumns = {@JoinColumn(name = "id_asignatura")}
//    )
    @OneToMany(mappedBy = "id_estudiante")
    private List<Estudiante_Asignatura> estudiante_asignatura;

}
