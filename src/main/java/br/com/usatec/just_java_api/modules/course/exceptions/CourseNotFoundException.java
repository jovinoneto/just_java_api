package br.com.usatec.just_java_api.modules.course.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException() {
        super("Course not found");
    }
}
