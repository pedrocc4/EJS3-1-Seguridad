package com.bosonit.backend.estudiante.infrastructure.controller.mapper;

import com.bosonit.backend.estudiante.domain.Estudiante;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteInputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudianteOutputDTO;
import com.bosonit.backend.estudiante.infrastructure.controller.dto.EstudiantePersonaOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Validated
public interface EstudianteMapper {

    @Valid Estudiante toEntity(EstudianteInputDTO estudianteInputDTO);

    EstudianteOutputDTO toDTO(@Valid Estudiante estudiante);

    EstudiantePersonaOutputDTO toDTO2(@Valid Estudiante estudiante);

    default List<EstudianteOutputDTO> toDTOList(@Valid List<Estudiante> estudiantes) {
        if (estudiantes == null) {
            return new ArrayList<>();
        }
        return estudiantes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<Estudiante> toEntityList(@Valid List<EstudianteInputDTO> estudiantes) {
        if (estudiantes == null) {
            return new ArrayList<>();
        }
        return estudiantes.stream().map(this::toEntity).collect(Collectors.toList());
    }
}