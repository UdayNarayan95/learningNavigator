package com.uday.learningNavigator.services;
import java.util.List;

import com.uday.learningNavigator.dtos.StudentDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.StudentNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotEnrolledInException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.RegisterStudentRequest;


public interface IStudentService {
    StudentDto registerStudent(RegisterStudentRequest registerStudentRequest);

    StudentDto findStudentById(long studentId) throws StudentNotFoundException;

    StudentDto enrollStudentInSubject(long studentId, long subjectId) throws StudentNotFoundException, SubjectNotFoundException;

    StudentDto registerStudentForExam(long studentId, long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotEnrolledInException;

    List<StudentDto> findAllStudents();

    void deregisterStudent(long studentId) throws StudentNotFoundException;
}
