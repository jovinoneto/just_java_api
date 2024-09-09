package br.com.usatec.just_java_api.modules.curse.controller;

import br.com.usatec.just_java_api.modules.curse.dto.CourseRequestDTO;
import br.com.usatec.just_java_api.modules.curse.entity.Course;
import br.com.usatec.just_java_api.modules.curse.enums.CourseStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/course")
public class CourseController {



    @PostMapping("")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseRequestDTO body) {
        Course course = new Course();

        course.setName(body.name());
        course.setCurseStatus(body.());
        course.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.status(201).body(course);
    }
}
