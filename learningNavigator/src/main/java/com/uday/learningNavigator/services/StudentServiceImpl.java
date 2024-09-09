package com.uday.learningNavigator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.learningNavigator.dtos.StudentDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.StudentNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotEnrolledInException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.RegisterStudentRequest;

import com.uday.learningNavigator.repositoryServices.IStudentRepositoryService;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    private IStudentRepositoryService studentRepositoryService;

    @Override
    public StudentDto registerStudent(RegisterStudentRequest registerStudentRequest) {
        String studentName = registerStudentRequest.getStudentName();
        StudentDto studentDto = studentRepositoryService.createStudent(studentName);
        return studentDto;
    }

    @Override
    public StudentDto findStudentById(long studentId) throws StudentNotFoundException {
        return studentRepositoryService.findStudentById(studentId);
        
    }

    @Override
    public StudentDto enrollStudentInSubject(long studentId, long subjectId)
            throws StudentNotFoundException, SubjectNotFoundException {
                return studentRepositoryService.enrollStudentInSubject(studentId, subjectId);
                
    }

    @Override
    public StudentDto registerStudentForExam(long studentId, long examId)
            throws StudentNotFoundException, ExamNotFoundException, SubjectNotEnrolledInException {
                return studentRepositoryService.registerStudentForExam(studentId, examId);
                
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentRepositoryService.findAllStudents();
        
    }

    @Override
    public void deregisterStudent(long studentId) throws StudentNotFoundException {
        studentRepositoryService.deleteStudent(studentId);
    }
    
}
