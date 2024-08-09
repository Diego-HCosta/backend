package com.example.server.domain.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.server.domain.dto.LoginDto;
import com.example.server.domain.repositories.UserRepository;
import com.example.server.domain.services.UserService;
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
        Boolean authenticated = false;
        UserService userService = new UserService(this.userRepository);
        if(userService.findByLogin(loginDto).isPresent()){
            authenticated = true;
        };
        if(authenticated){
            try {
                Algorithm algorithm = Algorithm.HMAC256(loginDto.getPassword());
                String token = JWT.create()
                        .withIssuer("test-key-secret")
                        .sign(algorithm);
                return token;
            }catch (JWTCreationException jwtCreationException){
                throw new AuthenticationException(jwtCreationException.getMessage());
            }
        } else {
            throw new AuthenticationException();
        }
    }
}
