package br.com.usatec.just_java_api.modules.course.dto;

import br.com.usatec.just_java_api.modules.course.enums.CourseStatus;

import java.util.UUID;

public record CourseResponseDTO(
        UUID id,
        String name,
        CourseStatus courseStatus,
        String createdAt,
        CategoryDTO categories
) {

        public record CategoryDTO(
                String name
        ) {}
}
