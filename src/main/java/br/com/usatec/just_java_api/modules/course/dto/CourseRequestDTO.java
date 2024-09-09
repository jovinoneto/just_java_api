package br.com.usatec.just_java_api.modules.course.dto;

import br.com.usatec.just_java_api.modules.course.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CourseRequestDTO(

        @NotBlank(message = "Name is mandatory!")
        String name,
        CourseStatus courseStatus,

        /*@Pattern(
                regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "UUID should follow the 36-character pattern, such as 123e4567-e89b-12d3-a456-426614174000"
        )*/
        @NotNull(message = "O UUID é obrigatório")
        UUID categoryId
        ) {
}
