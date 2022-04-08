package com.bosonit.backend.security;

import com.bosonit.backend.persona.infrastructure.controller.dto.output.PersonaOutputDTO;
import com.bosonit.backend.persona.service.PersonaService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private PersonaService service;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(
            @RequestParam("user") String username,
            @RequestParam("password") String pwd) {

        // Rescatamos persona & password
        PersonaOutputDTO personaOutputDTO = service.getPersonaByUser(username);
        String password = personaOutputDTO.getPassword();

        // Comprobamos credenciales y asignamos rol
        if (!pwd.equals(password))
            return ResponseEntity.unprocessableEntity().body("Pass incorrecto");
        String rol = personaOutputDTO.isAdmin() ? "ADMIN" : "USER";

        return new ResponseEntity<>(getJWTToken(username, rol), HttpStatus.OK);
    }

    /**
     * Token JWT
     *
     * @param username
     * @param rol
     * @return
     */
    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
