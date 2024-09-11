package br.com.usatec.just_java_api.modules.course.dto;

import java.util.UUID;

public record CourseUpdateRequestDTO(
        String name,
        UUID categoryId
) {
}
