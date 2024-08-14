package com.example.server.domain.controller;

import com.example.server.domain.services.UserService;
import com.example.server.domain.dto.UserDto;
import com.example.server.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String add() {

        return "All its working";
    }
    @GetMapping("/add/{name}/{password}")
    public String add(@PathVariable String name, @PathVariable String password) {
        UserService service = new UserService(userRepository);
        System.out.println("entrou service");
        service.save(new UserDto(name, password));
        return "working";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id) {
        UserService service = new UserService(userRepository);
        return "removed";
    }
}
