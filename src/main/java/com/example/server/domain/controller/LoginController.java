package com.example.server.domain.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.server.domain.dto.InfoLoginDto;
import com.example.server.domain.dto.LoginDto;
import com.example.server.domain.repositories.UserRepository;
import com.example.server.domain.services.UserService;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        boolean authenticated = false;

        UserService userService = new UserService(this.userRepository);
        if(userService.findByLogin(loginDto).isPresent()){
            authenticated = true;
        };
        if(authenticated){
            try {
                Algorithm algorithm = Algorithm.HMAC256("./w3r");
                InfoLoginDto infoLoginDto = new InfoLoginDto(loginDto.getUsername());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(infoLoginDto);
                return JWT.create()
                        .withIssuer("test-key-secret")
                        .withSubject(json)
                        .sign(algorithm);
            }catch (JWTCreationException | JsonProcessingException jwtCreationException){
                throw new AuthenticationException(jwtCreationException.getMessage());
            }
        } else {
            throw new AuthenticationException();
        }
    }
}
