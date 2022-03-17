package com.bosonit.backend.profesor.infrastructure.controller.mapper;

import com.bosonit.backend.profesor.domain.Profesor;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorInputDTO;
import com.bosonit.backend.profesor.infrastructure.controller.dto.ProfesorOutputDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Validated
public interface ProfesorMapper {

    @Valid Profesor toEntity(ProfesorInputDTO ProfesorInputDTO);

    ProfesorOutputDTO toDTO(@Valid Profesor Profesor);

    default List<ProfesorOutputDTO> toDTOList(@Valid List<Profesor> Profesores) {
        if (Profesores == null) {
            return new ArrayList<>();
        }
        return Profesores.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
