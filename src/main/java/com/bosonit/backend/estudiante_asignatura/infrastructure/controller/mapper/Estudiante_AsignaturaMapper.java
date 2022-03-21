package com.bosonit.backend.estudiante_asignatura.infrastructure.controller.mapper;

import com.bosonit.backend.estudiante_asignatura.domain.Estudiante_Asignatura;
import com.bosonit.backend.estudiante_asignatura.infrastructure.controller.dto.Estudiante_AsignaturaInputDTO;
import com.bosonit.backend.estudiante_asignatura.infrastructure.controller.dto.Estudiante_AsignaturaOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Validated
public interface Estudiante_AsignaturaMapper {

        @Valid
        Estudiante_Asignatura toEntity(Estudiante_AsignaturaInputDTO Estudiante_AsignaturaInputDTO);

        Estudiante_AsignaturaOutputDTO toDTO(@Valid Estudiante_Asignatura Estudiante_Asignatura);

        default List<Estudiante_AsignaturaOutputDTO> toDTOList(@Valid List<Estudiante_Asignatura> Estudiante_Asignaturas) {
            if (Estudiante_Asignaturas == null) {
                return new ArrayList<>();
            }
            return Estudiante_Asignaturas.stream().map(this::toDTO).collect(Collectors.toList());
        }

        default List<Estudiante_Asignatura> toEntityList(@Valid List<Estudiante_AsignaturaInputDTO> Estudiante_Asignaturas) {
            if (Estudiante_Asignaturas == null) {
                return new ArrayList<>();
            }
            return Estudiante_Asignaturas.stream().map(this::toEntity).collect(Collectors.toList());
        }
    }
