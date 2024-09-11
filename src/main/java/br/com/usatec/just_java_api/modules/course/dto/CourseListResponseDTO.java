package br.com.usatec.just_java_api.modules.course.dto;

import java.util.UUID;

public record CourseListResponseDTO(
        UUID id,
        String name,
        boolean active,
        String createdAt,
        String updatedAt,
        CategoryDTO categories
) {

    public record CategoryDTO(
            UUID id,
            String name
    ) {}
}
