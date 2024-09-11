package br.com.usatec.just_java_api.modules.course.controller;

import br.com.usatec.just_java_api.modules.category.repository.CategoryRepository;
import br.com.usatec.just_java_api.modules.course.dto.*;
import br.com.usatec.just_java_api.modules.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("")
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody  CourseRequestDTO body) {
        CourseResponseDTO createCourse = courseService.createCourse(body);

        return new ResponseEntity<>(createCourse, HttpStatus.CREATED);
    }

    @GetMapping("/search/name")
    public List<CourseListResponseDTO> getCourseList(@RequestParam String name) {

        return courseService.getSearchNameCourse(name);
    }

    @GetMapping("")
    public List<CourseListResponseDTO> getAllCourse() {

        return courseService.getListAllCourse();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CourseCategoryResponseDTO> getCourseByCategory(@PathVariable("id") UUID categoryId) {

        CourseCategoryResponseDTO courses = courseService.getListCourseByCategory(categoryId);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseUpdateResponseDTO> updateCourse(@PathVariable("id") UUID id, @Valid @RequestBody CourseUpdateRequestDTO body) throws IllegalAccessException {
        CourseUpdateResponseDTO updateCourse = courseService.updateCourse(id, body);

        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") UUID id) {
        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CourseResponseDTO> activateStatusCourse(@PathVariable("id") UUID id) {
        CourseResponseDTO courseResponseDTO = courseService.activateStatusCourse(id);

        return ResponseEntity.ok(courseResponseDTO);
    }
}
