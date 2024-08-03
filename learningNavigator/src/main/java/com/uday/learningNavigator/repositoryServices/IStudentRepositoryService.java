package com.uday.learningNavigator.repositoryServices;

import java.util.List;

import com.uday.learningNavigator.dtos.StudentDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.StudentNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.models.Student;

public interface IStudentRepositoryService {

    StudentDto createStudent(String studentName);

    StudentDto findStudentById(long studentId) throws StudentNotFoundException;

    StudentDto enrollStudentInSubject(long studentId, long subjectId) throws StudentNotFoundException, SubjectNotFoundException;

    StudentDto registerStudentForExam(long studentId, long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotEnrolledException;

    List<StudentDto> findAllStudents();

    void deleteStudent(long studentId) throws StudentNotFoundException; 
}
