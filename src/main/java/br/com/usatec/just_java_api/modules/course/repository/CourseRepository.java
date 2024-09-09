package br.com.usatec.just_java_api.modules.course.repository;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    Optional<Course> findByNameIgnoreCase(String name);

    List<Course> findByNameContainingIgnoreCase(String name);

    boolean existsByCategoryId(UUID id);
}
