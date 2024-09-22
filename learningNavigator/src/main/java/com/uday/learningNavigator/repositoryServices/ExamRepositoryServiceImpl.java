package com.uday.learningNavigator.repositoryServices;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.learningNavigator.dtos.ExamDto;
import com.uday.learningNavigator.dtos.SubjectDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.models.Exam;
import com.uday.learningNavigator.models.Subject;
import com.uday.learningNavigator.repositories.ExamRepository;
import com.uday.learningNavigator.repositories.SubjectRepository;

import jakarta.inject.Provider;

import java.util.stream.Collectors;

@Service
public class ExamRepositoryServiceImpl implements IExamRepositoryService{

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;
    
    @Override
    // public ExamDto createExam(long subjectId) throws SubjectNotFoundException {
        
    //     String message = String.format("Could not find subject with ID: %d", subjectId);
    //     ModelMapper modelMapper = modelMapperProvider.get();

    //     Exam exam = new Exam();
    //     Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(message));
    //     System.out.println("create exam of servimpl revoked");
    //     System.out.println(subject);
        
    //     exam.setSubject(subject);
        
    //     ExamDto examDto = modelMapper.map(examRepository.save(exam), ExamDto.class);
    //     System.out.println(examDto);
    //     return examDto;  
    // }
    public ExamDto createExam(long subjectId) throws SubjectNotFoundException {
    String message = String.format("Could not find subject with ID: %d", subjectId);
    ModelMapper modelMapper = modelMapperProvider.get();

    Exam exam = new Exam();
    Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new SubjectNotFoundException(message));
    
    System.out.println("create exam of servimpl revoked");
    System.out.println(subject);
    
    exam.setSubject(subject);

    
    
    ExamDto examDto = modelMapper.map(examRepository.save(exam), ExamDto.class);

    // Manually map Subject -> SubjectDto
    SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
    examDto.setSubjectDto(subjectDto);
     
    
    return examDto;
}


    @Override
    public ExamDto findExamById(long examId) throws ExamNotFoundException {
        String message = String.format("Could not find exam with ID: %d", examId);
        ModelMapper modelMapper = modelMapperProvider.get();
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException(message));
        ExamDto examDto = modelMapper.map(exam, ExamDto.class);
        return examDto;
    }

    @Override
    public List<ExamDto> findAllExams() {
        List<Exam> examEntities = examRepository.findAll();
        List<ExamDto> exams = mapToExamList(examEntities);
        return exams;
    }

    @Override
    public void deleteExam(long examId) throws ExamNotFoundException {
        String message = "Could not find exam with ID: " + String.valueOf(examId);
        Exam examEntity = examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException(message));
        examRepository.delete(examEntity);
    }

   
    // public List<ExamDto> mapToExamList(List<Exam> exams) {
    //     ModelMapper modelMapper = modelMapperProvider.get();
    //     return exams.stream()
    //                 .map(exam -> modelMapper.map(exam, ExamDto.class))
    //                 .collect(Collectors.toList());
    // }

    public List<ExamDto> mapToExamList(List<Exam> exams) {
    ModelMapper modelMapper = modelMapperProvider.get();
    return exams.stream()
                .map(exam -> {
                    ExamDto examDto = modelMapper.map(exam, ExamDto.class);
                    return examDto;
                })
                .collect(Collectors.toList());
}
    
}
/*
 * There is a problem in this code. Give me a list of the bugs you find.

2. Potential NullPointerException in createExam Method
In the createExam method, the subject object is retrieved from the subjectRepository using findById.
 If the subject is not found, a SubjectNotFoundException is thrown.
However, if the subject is found, its id is not checked for null before being used to create a new Exam object.
 If the id is null, a NullPointerException may be thrown when trying to access the id field.

3. Missing Input Validation in createExam and findExamById Methods
The createExam and findExamById methods do not perform any input validation on the subjectId
 and examId parameters, respectively.
  This could lead to unexpected behavior or errors if invalid or null values are passed to these methods.

4. Missing Error Handling in findAllExams Method
The findAllExams method does not handle any potential errors that may occur when retrieving the list of exams from the examRepository.
 It would be a good practice to add try-catch blocks to handle any exceptions that may be thrown.


 */