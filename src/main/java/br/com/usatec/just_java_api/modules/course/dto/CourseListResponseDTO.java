package br.com.usatec.just_java_api.modules.course.dto;

import br.com.usatec.just_java_api.modules.course.enums.CourseStatus;

import java.util.UUID;

public record CourseListResponseDTO(
        UUID id,
        String name,
        CourseStatus courseStatus,
        String createdAt,
        String updatedAt,
        CategoryDTO categories
) {

    public record CategoryDTO(

            UUID id,
            String name
    ) {}
}
