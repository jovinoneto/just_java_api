package br.com.usatec.just_java_api.modules.course.controller;

import br.com.usatec.just_java_api.modules.category.repository.CategoryRepository;
import br.com.usatec.just_java_api.modules.course.dto.CourseListResponseDTO;
import br.com.usatec.just_java_api.modules.course.dto.CourseRequestDTO;
import br.com.usatec.just_java_api.modules.course.dto.CourseResponseDTO;
import br.com.usatec.just_java_api.modules.course.services.CourseService;
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

    @GetMapping("")
    public List<CourseListResponseDTO> getCourseList(@RequestParam String name) {

        return courseService.getListCourse(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable("id") UUID id, @Valid @RequestBody CourseRequestDTO body) {
        CourseResponseDTO responseDTO = courseService.updateCourse(id, body);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") UUID id) {
        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}
