package com.uday.learningNavigator.repositoryServices;

import java.util.List;

import com.uday.learningNavigator.dtos.ExamDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;


public interface IExamRepositoryService {

    ExamDto createExam(long subjectId) throws SubjectNotFoundException;

    ExamDto findExamById(long examId) throws ExamNotFoundException;

    List<ExamDto> findAllExams();

    void deleteExam(long examId) throws ExamNotFoundException;
    
} 
