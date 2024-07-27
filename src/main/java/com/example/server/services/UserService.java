package com.example.server.services;

import com.example.server.dto.UserDto;
import com.example.server.model.UserModel;
import com.example.server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void save(UserDto user) {
        UserModel usuario = new UserModel(null,
                                user.login(),
                                user.password());
        userRepository.save(usuario);
    }
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
