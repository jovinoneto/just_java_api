package br.com.usatec.just_java_api.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseRequestDTO(

        @NotBlank(message = "Name is required!")
        String name,

        @NotNull(message = "UUID is required!")
        UUID categoryId
        ) {
}
