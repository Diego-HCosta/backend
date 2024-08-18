package com.example.server.domain.dto;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class InfoLoginDto {
    private final String login;

    private final String dateTime;
    public InfoLoginDto(String login) {
        this.login = login;
        this.dateTime = LocalDate.now().toString();
    }
}
