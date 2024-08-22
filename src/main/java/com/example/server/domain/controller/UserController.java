package com.example.server.domain.controller;

import com.example.server.domain.dto.ChangePassDto;
import com.example.server.domain.model.UserModel;
import com.example.server.domain.services.UserService;
import com.example.server.domain.dto.UserDto;
import com.example.server.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String remove(@PathVariable long id) {
        UserService service = new UserService(userRepository);
        UserModel userModel = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        service.delete(userModel.getId());
        return "removed";
    }

    @PostMapping("/changePass")
    public String changePass(@RequestBody ChangePassDto changePassDto) {
        UserService service = new UserService(userRepository);
        UserModel user = userRepository.findById(changePassDto.getId())
                .orElseThrow(()-> new RuntimeException("User dont found"));
        user.setPassword(changePassDto.getNewPass());
        userRepository.save(user);
        return "changed pass";
    }
}
