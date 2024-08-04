package com.uday.learningNavigator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uday.learningNavigator.dtos.StudentDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.StudentNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotEnrolledInException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.GetAllStudentsResponse;
import com.uday.learningNavigator.exchanges.RegisterStudentRequest;
import com.uday.learningNavigator.models.Student;
import com.uday.learningNavigator.services.IStudentService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping(StudentController.STUDENT_API_ENDPOINT)
public class StudentController {

    public static final String STUDENT_API_ENDPOINT = "/students";

    @Autowired
    private IStudentService studentService;

    @PostMapping(STUDENT_API_ENDPOINT)
    public ResponseEntity<StudentDto> registerStudent(@Valid @RequestBody RegisterStudentRequest registerStudentRequest) {
        StudentDto student = studentService.registerStudent(registerStudentRequest);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping(STUDENT_API_ENDPOINT+"/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(value = "studentId") long studentId) throws StudentNotFoundException {
        StudentDto student = studentService.findStudentById(studentId);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping(STUDENT_API_ENDPOINT+"/{studentId}/subject/{subjectId}")
    public ResponseEntity<StudentDto> enrollStudentInSubject(@PathVariable long studentId, @PathVariable long subjectId) throws StudentNotFoundException, SubjectNotFoundException {
        StudentDto student = studentService.enrollStudentInSubject(studentId, subjectId);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping(STUDENT_API_ENDPOINT+"/{studentId}/exam/{examId}")
    public ResponseEntity<StudentDto> registerStudentForExam(@PathVariable long studentId, @PathVariable long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotEnrolledInException {
        StudentDto student = studentService.registerStudentForExam(studentId, examId);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping(STUDENT_API_ENDPOINT)
    public ResponseEntity<GetAllStudentsResponse> getAllStudents() {
        List<StudentDto> students = studentService.findAllStudents();
        GetAllStudentsResponse getAllStudentsResponse = new GetAllStudentsResponse(students);
        return ResponseEntity.ok().body(getAllStudentsResponse);
    }

    @DeleteMapping(STUDENT_API_ENDPOINT+"/{studentId}")
    public ResponseEntity<String> deregisterStudent(@PathVariable long studentId) throws StudentNotFoundException {
        String message = "Successfully deleted student with ID: " + String.valueOf(studentId);
        studentService.deregisterStudent(studentId);
        return ResponseEntity.ok().body(message);
    }
}
