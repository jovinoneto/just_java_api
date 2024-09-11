package br.com.usatec.just_java_api.modules.course.dto;

import java.util.List;

public record CourseCategoryResponseDTO(
        String name,
        List<CourseDTO> courses
) {

    public record CourseDTO(
            String name,
            boolean active
    ) {}
}
