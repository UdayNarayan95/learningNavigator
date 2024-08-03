package com.uday.learningNavigator.repositoryServices;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.learningNavigator.dtos.ExamDto;
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
    public ExamDto createExam(long subjectId) throws SubjectNotFoundException {
        
        String message = String.format("Could not find subject with ID: %d", subjectId);
        ModelMapper modelMapper = modelMapperProvider.get();

        Exam exam = new Exam();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(message));

        exam.setSubject(subject);
        ExamDto examDto = modelMapper.map(examRepository.save(exam), ExamDto.class);
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

   
    public List<ExamDto> mapToExamList(List<Exam> exams) {
        ModelMapper modelMapper = modelMapperProvider.get();
        return exams.stream()
                    .map(exam -> modelMapper.map(exam, ExamDto.class))
                    .collect(Collectors.toList());
    }
    
}
