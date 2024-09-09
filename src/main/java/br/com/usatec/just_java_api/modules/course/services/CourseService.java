package br.com.usatec.just_java_api.modules.course.services;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryNotFoundException;
import br.com.usatec.just_java_api.modules.category.repository.CategoryRepository;
import br.com.usatec.just_java_api.modules.course.dto.CourseListResponseDTO;
import br.com.usatec.just_java_api.modules.course.dto.CourseRequestDTO;
import br.com.usatec.just_java_api.modules.course.dto.CourseResponseDTO;
import br.com.usatec.just_java_api.modules.course.entity.Course;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseAlreadyExistException;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseNotFoundException;
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
                .courseStatus(body.courseStatus())
                .category(category)
                .createdAt(LocalDateTime.now())
                .build();

        Course saveCourse = courseRepository.save(course);

        return new CourseResponseDTO(
                saveCourse.getId(),
                saveCourse.getName(),
                saveCourse.getCourseStatus(),
                saveCourse.getCreatedAt().toString(),
                new CourseResponseDTO.CategoryDTO(
                        saveCourse.getCategory().getName()
                ));
    }

    public List<CourseListResponseDTO> getListCourse(String name) {
        List<Course> courses = this.courseRepository.findByNameContainingIgnoreCase(name);

        if(courses.isEmpty())
            throw new CourseNotFoundException();

        return courses.stream()
                .map(course -> new CourseListResponseDTO(
                        course.getId(),
                        course.getName(),
                        course.getCourseStatus(),
                        course.getCreatedAt().toString(),
                        course.getUpdatedAt() != null ? course.getUpdatedAt().toString() : null,
                        new CourseListResponseDTO.CategoryDTO(
                                course.getCategory().getId(),
                                course.getCategory().getName()
                        )
                )).toList();
    }

    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO body) {
        Course course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        this.verifyCourseUpdate(body.name(), id);
        Category category = this.categoryRepository.findById(body.categoryId()).orElseThrow(CategoryNotFoundException::new);

        course.setName(body.name());
        course.setCourseStatus(body.courseStatus());
        course.setCategory(category);
        course.setUpdatedAt(LocalDateTime.now());

        Course updateCourse = courseRepository.save(course);

        return new CourseResponseDTO(
                updateCourse.getId(),
                updateCourse.getName(),
                updateCourse.getCourseStatus(),
                updateCourse.getCreatedAt().toString(),
                new CourseResponseDTO.CategoryDTO(
                        updateCourse.getCategory().getName()
                ));
    }

    public void deleteCourse(UUID id) {
        Course course = this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);

        courseRepository.delete(course);
    }

    private void verifyCourseRegistered(String name) {
        Optional<Course> isCourseRegister = this.courseRepository.findByNameIgnoreCase(name);

        if(isCourseRegister.isPresent())
            throw new CourseAlreadyExistException();
    }

    private void verifyCourseUpdate(String name, UUID id) {
        Optional<Course> isCourseUpdate = this.courseRepository.findByNameIgnoreCase(name);

        if(isCourseUpdate.isPresent() && !isCourseUpdate.get().getId().equals(id))
            throw new CourseAlreadyExistException();

    }
}
