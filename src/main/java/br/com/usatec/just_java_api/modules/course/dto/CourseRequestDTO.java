package br.com.usatec.just_java_api.modules.curse.dto;

import br.com.usatec.just_java_api.modules.curse.enums.CourseStatus;

import java.util.UUID;

public record CourseRequestDTO(
        UUID id,
        String name,
        CourseStatus curseStatus,
        Long createdAt
        ) {
}
