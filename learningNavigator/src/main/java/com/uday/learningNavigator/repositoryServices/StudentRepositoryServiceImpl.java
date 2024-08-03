package com.uday.learningNavigator.repositoryServices;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.uday.learningNavigator.dtos.StudentDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.StudentNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotEnrolledInException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.models.Exam;
import com.uday.learningNavigator.models.Student;
import com.uday.learningNavigator.models.Subject;
import com.uday.learningNavigator.repositories.ExamRepository;
import com.uday.learningNavigator.repositories.StudentRepository;
import com.uday.learningNavigator.repositories.SubjectRepository;

import jakarta.inject.Provider;

public class StudentRepositoryServiceImpl implements IStudentRepositoryService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;

    @Override
    public StudentDto createStudent(String studentName) {
        ModelMapper modelMapper = modelMapperProvider.get();
        Student student = new Student();
        student.setStudentName(studentName);
        StudentDto studentDto = modelMapper.map(studentRepository.save(student), StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto findStudentById(long studentId) throws StudentNotFoundException {
        ModelMapper modelMapper = modelMapperProvider.get();
        Optional<Student> maybeStudentEntity = studentRepository.findById(studentId);
        
        if(maybeStudentEntity.isPresent()) {
            Student student = maybeStudentEntity.get();
            // System.out.println("Enrolled Subjects: " + studentEntity.getEnrolledSubjects());
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            return studentDto;
        }
        else {
            String message = "Could not find student with ID: " + String.valueOf(studentId);
            throw new StudentNotFoundException(message);
        }
    }


    @Override
    public StudentDto enrollStudentInSubject(long studentId, long subjectId)
            throws StudentNotFoundException, SubjectNotFoundException {
       
        ModelMapper modelMapper = modelMapperProvider.get();
        String studentNotFoundMessage = "Could not find student with ID: " + String.valueOf(studentId);
        String subjectNotFoundMessage = "Could not find subject with ID: " + String.valueOf(subjectId);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentNotFoundMessage));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(subjectNotFoundMessage));

        Set<Subject> enrolledSubjects = student.getEnrolledSubjects();
        enrolledSubjects.add(subject);

        StudentDto studentDto = modelMapper.map(studentRepository.save(student), StudentDto.class);
        
        return studentDto;
    }

    @Override
    public StudentDto registerStudentForExam(long studentId, long examId)
            throws StudentNotFoundException, ExamNotFoundException, SubjectNotEnrolledInException {
                ModelMapper modelMapper = modelMapperProvider.get();
                String studentNotFoundMessage = "Could not find student with ID: " + String.valueOf(studentId);
                String examNotFoundMessage = "Could not find exam with ID: " + String.valueOf(examId);
        
                Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentNotFoundMessage));
                Exam exam = examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException(examNotFoundMessage));
                Subject subject = exam.getSubject();
        
                String subjectNotEnrolledMessage = "Student has not enrolled in subject with ID: " + String.valueOf(subject.getSubjectId());
        
                if(isSubjectEnrolled(student, subject)) {
                    Set<Exam> registeredExams = student.getEnrolledExams();
                    registeredExams.add(exam);
                    StudentDto studentDto = modelMapper.map(studentRepository.save(student), StudentDto.class);
                    return studentDto;
                }
                else {
                    throw new SubjectNotEnrolledInException(subjectNotEnrolledMessage);
                }
    }

    @Override
    public List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = mapToStudentList(students);
        return studentDtos;
    }

    @Override
    public void deleteStudent(long studentId) throws StudentNotFoundException {
        String message = "Could not find student with ID: " + String.valueOf(studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(message));
        studentRepository.delete(student);
    }

    private List<StudentDto> mapToStudentList(List<Student> studentEntities) {
        List<StudentDto> studentDtos = new ArrayList<>();
        ModelMapper modelMapper = modelMapperProvider.get();

        for(Student student : studentEntities) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

   


    private boolean isSubjectEnrolled(Student student, Subject subject) {
        Set<Subject> enrolledSubjects = student.getEnrolledSubjects();

        if(enrolledSubjects.contains(subject))
            return true;
        return false;
    }
    
}
