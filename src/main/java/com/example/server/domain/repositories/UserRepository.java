package com.example.server.domain.repositories;

import com.example.server.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {
    @Query("SELECT u FROM UserModel u WHERE u.login = :login AND u.password = :password")
    Optional<UserModel> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
