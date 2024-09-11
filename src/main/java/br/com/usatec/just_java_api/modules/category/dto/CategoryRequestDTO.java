package br.com.usatec.just_java_api.modules.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required!")
        String name)
{}