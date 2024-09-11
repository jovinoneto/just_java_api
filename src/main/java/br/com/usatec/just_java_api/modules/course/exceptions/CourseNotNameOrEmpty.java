package br.com.usatec.just_java_api.modules.course.exceptions;

public class CourseNotNameOrEmpty extends RuntimeException {

    public CourseNotNameOrEmpty() {
        super("Course name cannot be null or empty");
    }
}
