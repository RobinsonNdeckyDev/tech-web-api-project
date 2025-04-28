package com.example.api_techbWeb_robin.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class AdminDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String service;
}
