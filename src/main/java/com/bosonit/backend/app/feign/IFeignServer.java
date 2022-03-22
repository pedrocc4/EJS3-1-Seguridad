package com.bosonit.backend.app.feign;

import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign",url="http://localhost:8080/",fallbackFactory = FeignFallbackFactory.class)
public interface IFeignServer {

    //TODO llamadas

    @GetMapping("profesor/{id}")
    ResponseEntity<ProfesorOutputDTO> callServer(@PathVariable("id") String id);

}
