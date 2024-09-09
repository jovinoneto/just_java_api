package br.com.usatec.just_java_api.exceptions;

import br.com.usatec.just_java_api.modules.category.exceptions.CategoryAlreadyExistException;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryInUseException;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryNotFoundException;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseAlreadyExistException;
import br.com.usatec.just_java_api.modules.course.exceptions.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ErrorMessageDTO> handleCategoryAlreadyExistException(CategoryAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(e.getMessage()));
    }


    @ExceptionHandler(CategoryInUseException.class)
    public ResponseEntity<ErrorMessageDTO> handleCategoryInUseException(CategoryInUseException e) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ErrorMessageDTO(e.getMessage()));
    }


    @ExceptionHandler(CourseAlreadyExistException.class)
    public ResponseEntity<ErrorMessageDTO> handleCourseAlreadyExistException(CourseAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleCourseNotFoundException(CourseNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(e.getMessage()));
    }

    // Generic Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
