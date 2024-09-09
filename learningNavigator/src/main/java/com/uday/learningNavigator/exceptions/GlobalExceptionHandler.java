package com.uday.learningNavigator.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    ResponseEntity<String> handleSubjectNotFoundException(SubjectNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler(ExamNotFoundException.class)
    ResponseEntity<String> handleExamNotFoundException(ExamNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler(SubjectNotEnrolledInException.class)
    ResponseEntity<String> handleSubjectNotEnrolledException(SubjectNotEnrolledInException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(403).body(message);
    }
}
