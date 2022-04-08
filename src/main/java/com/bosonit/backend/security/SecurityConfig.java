package com.bosonit.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    //private final encrypt;

//    @Override
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(...)
////    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/estudiante").hasAnyRole("USUARIO", "ADMINISTRADOR")
                .antMatchers(HttpMethod.GET, "/persona").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/persona").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/persona").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/persona").hasRole("ADMINISTRADOR")
                .anyRequest().authenticated();
    }
}
