package com.bosonit.backend.app.feign;

import com.bosonit.backend.profesor.infrastructure.controller.dto.output.ProfesorOutputDTO;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class FeignFallbackFactory implements FallbackFactory<IFeignServer> {
    @Override
    public IFeignServer create(Throwable cause) {
        return new FeignFallback(cause);
    }
}

class FeignFallback implements IFeignServer {
    int code;
    String msg;

    FeignFallback(Throwable cause) {
        if (cause instanceof FeignException feignException) {
            code = feignException.status();

            //System.out.println("Devolvio status: "+ feignException.status()+ " Mensaje: "+feignException.getLocalizedMessage());
        }
        msg = cause.getLocalizedMessage();
    }

    //TODO llamadas
    @Override
    public ResponseEntity<ProfesorOutputDTO> callServer(@PathVariable("id") String id) {
        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO();
        // FIXME aqui que se supone que se hace
        profesorOutputDTO.setId_profesor(id);
        return ResponseEntity.status(code).body(new ProfesorOutputDTO());
    }
}
