package com.uday.learningNavigator.services;

import java.util.List;

import com.uday.learningNavigator.dtos.ExamDto;
import com.uday.learningNavigator.exceptions.ExamNotFoundException;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.CreateExamRequest;


public interface IExamService {
    ExamDto createExam(CreateExamRequest createExamRequest) throws SubjectNotFoundException;

    ExamDto findExamById(long examId) throws ExamNotFoundException;

    List<ExamDto> findAllExams();

    void deleteExam(long examId) throws ExamNotFoundException; 
}
