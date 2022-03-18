package com.bosonit.backend.asignatura.infrastructure.controller.mapper;

import com.bosonit.backend.asignatura.domain.Asignatura;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaInputDTO;
import com.bosonit.backend.asignatura.infrastructure.controller.dto.AsignaturaOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Validated
public interface AsignaturaMapper {
    @Valid Asignatura toEntity(AsignaturaInputDTO AsignaturaInputDTO);

    AsignaturaOutputDTO toDTO(@Valid Asignatura Asignatura);

    default List<AsignaturaOutputDTO> toDTOList(@Valid List<Asignatura> Asignaturas) {
        if (Asignaturas == null) {
            return new ArrayList<>();
        }
        return Asignaturas.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
