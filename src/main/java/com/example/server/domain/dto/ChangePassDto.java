package com.example.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ChangePassDto {
    private final Long id;
    private final String newPass;

}
