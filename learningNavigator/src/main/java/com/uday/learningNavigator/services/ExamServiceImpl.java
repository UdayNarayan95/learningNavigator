package com.uday.learningNavigator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uday.learningNavigator.dtos.ExamDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.CreateExamRequest;


import com.uday.learningNavigator.repositoryServices.IExamRepositoryService;

public class ExamServiceImpl implements IExamService{

    @Autowired
    private IExamRepositoryService examRepositoryService;

    @Override
    public ExamDto createExam(CreateExamRequest createExamRequest) throws SubjectNotFoundException {
        long subjectId=createExamRequest.getSubjectId();
        ExamDto examDto=examRepositoryService.createExam(subjectId);
        return examDto;
    }

    @Override
    public ExamDto findExamById(long examId) throws ExamNotFoundException {
        
        ExamDto examDto = examRepositoryService.findExamById(examId);
        return examDto;
    }

    @Override
    public List<ExamDto> findAllExams() {   
        List<ExamDto> exams = examRepositoryService.findAllExams();
        return exams;
    }

    @Override
    public void deleteExam(long examId) throws ExamNotFoundException {
        
        examRepositoryService.deleteExam(examId);
    }
    
}
