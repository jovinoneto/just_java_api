package br.com.usatec.just_java_api.modules.course.dto;

public record CourseUpdateResponseDTO(
        String name,
        String updatedAt,
        CategoryDTO category
) {

    public record CategoryDTO(
            String name
    ) {}
}
