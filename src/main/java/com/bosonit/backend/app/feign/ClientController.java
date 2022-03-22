package com.bosonit.backend.app.feign;

import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    IFeignServer iFeignServer;

    @GetMapping("{id}")
    ResponseEntity<ProfesorOutputDTO> callUsingFeign(@PathVariable String id) {
        System.out.println("En client. Antes de llamada a server Devolvere: " + id);
        ResponseEntity<ProfesorOutputDTO> respuesta = iFeignServer.callServer(id);
        System.out.println("En client. Despues de llamada a server");
        return respuesta;
    }

    @GetMapping("/template/{id}")
    ResponseEntity<ProfesorOutputDTO> callUsingRestTemplate(@PathVariable String id) {
        System.out.println("En client Resttemplate. Antes de llamada a server Devolvere: " + id);
        ResponseEntity<ProfesorOutputDTO> rs = new RestTemplate().getForEntity("http://localhost:8080/server/" + id, ProfesorOutputDTO.class);
        System.out.println("En client Resttemplate. Despues de llamada a server");
        return ResponseEntity.ok(rs.getBody());
    }
}
