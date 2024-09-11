package br.com.usatec.just_java_api.modules.course.dto;

import java.util.UUID;

public record CourseResponseDTO(
        UUID id,
        String name,
        boolean active,
        String createdAt,
        CategoryDTO category
) {

        public record CategoryDTO(
                String name
        ) {}
}
