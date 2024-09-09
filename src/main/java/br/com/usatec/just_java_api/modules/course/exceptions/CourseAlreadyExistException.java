package br.com.usatec.just_java_api.modules.course.exceptions;

public class CourseAlreadyExistException extends RuntimeException{

    public CourseAlreadyExistException() {
        super("Course is already registered");
    }
}
