package br.com.usatec.just_java_api.modules.course.service;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryNotFoundException;
import br.com.usatec.just_java_api.modules.category.repository.CategoryRepository;
import br.com.usatec.just_java_api.modules.course.dto.*;
import br.com.usatec.just_java_api.modules.course.entity.Course;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseAlreadyExistException;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseNotFoundException;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseNotNameOrEmpty;
import br.com.usatec.just_java_api.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO body) {
        this.verifyCourseRegistered(body.name());
        Category category = this.categoryRepository.findById(body.categoryId()).orElseThrow(CategoryNotFoundException::new);

        Course course = Course.builder()
                .name(body.name())
                .category(category)
                .createdAt(LocalDateTime.now())
                .build();

        Course saveCourse = courseRepository.save(course);

        return new CourseResponseDTO(
                saveCourse.getId(),
                saveCourse.getName(),
                saveCourse.isActive(),
                saveCourse.getCreatedAt().toString(),
                new CourseResponseDTO.CategoryDTO(
                        saveCourse.getCategory().getName()
                ));
    }

    public CourseUpdateResponseDTO updateCourse(UUID id, CourseUpdateRequestDTO body) {
        Course course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        if ((body.name() != null) && !body.name().isEmpty()){
            course.setName(body.name());
            course.setUpdatedAt(LocalDateTime.now());
        }

        if (body.categoryId() != null) {
            Category category = categoryRepository.findById(body.categoryId()).orElseThrow(CategoryNotFoundException::new);
            course.setCategory(category);
            course.setUpdatedAt(LocalDateTime.now());
        }

        Course updateCourse = courseRepository.save(course);

        return new CourseUpdateResponseDTO(
                updateCourse.getName(),
                updateCourse.getUpdatedAt().toString(),
                new CourseUpdateResponseDTO.CategoryDTO(
                    updateCourse.getCategory().getName()
                )
        );
    }

    public CourseResponseDTO activateStatusCourse(UUID id) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        course.setActive(!course.isActive());

        Course updateCourse = this.courseRepository.save(course);

        return new CourseResponseDTO(
                updateCourse.getId(),
                updateCourse.getName(),
                updateCourse.isActive(),
                updateCourse.getCreatedAt().toString(),
                new CourseResponseDTO.CategoryDTO(
                        updateCourse.getCategory().getName()
                )
        );
    }

    public void deleteCourse(UUID id) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        courseRepository.delete(course);
    }

    public List<CourseListResponseDTO> getSearchNameCourse(String name) {
        if(name == null || name.isEmpty())
            throw new CourseNotNameOrEmpty();

        List<Course> courses = this.courseRepository.findByNameContainingIgnoreCase(name);

        if(courses.isEmpty())
            throw new CourseNotFoundException();

        return courses.stream()
                .map(course -> new CourseListResponseDTO(
                        course.getId(),
                        course.getName(),
                        course.isActive(),
                        course.getCreatedAt().toString(),
                        course.getUpdatedAt() != null ? course.getUpdatedAt().toString() : null,
                        new CourseListResponseDTO.CategoryDTO(
                                course.getCategory().getId(),
                                course.getCategory().getName()
                        )
                )).toList();
    }

    public CourseCategoryResponseDTO getListCourseByCategory(UUID categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

        List<Course> courses = this.courseRepository.findByCategoryId(categoryId);

        List<CourseCategoryResponseDTO.CourseDTO> courseDTOS = courses.stream()
                .map(course -> new CourseCategoryResponseDTO.CourseDTO(
                        course.getName(),
                        course.isActive())
                ).toList();

        return new CourseCategoryResponseDTO(
                category.getName(),
                courseDTOS
        );
    }

    public List<CourseListResponseDTO> getListAllCourse() {
        return this.courseRepository.findAll().stream()
                .map(course -> new CourseListResponseDTO(
                        course.getId(),
                        course.getName(),
                        course.isActive(),
                        course.getCreatedAt().toString(),
                        course.getUpdatedAt() != null ? course.getUpdatedAt().toString() : null,
                        new CourseListResponseDTO.CategoryDTO(
                                course.getCategory().getId(),
                                course.getCategory().getName()
                        )
                )).toList();
    }

    private void verifyCourseRegistered(String name) {
        Optional<Course> isCourseRegister = this.courseRepository.findByNameIgnoreCase(name);

        if(isCourseRegister.isPresent())
            throw new CourseAlreadyExistException();
    }
}
