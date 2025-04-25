package com.example.api_techbWeb_robin.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleRequest {
    @NotBlank
    private String nomRole;
}
