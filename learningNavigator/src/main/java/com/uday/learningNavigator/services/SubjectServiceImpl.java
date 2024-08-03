package com.uday.learningNavigator.services;

import com.uday.learningNavigator.dtos.SubjectDto;

import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.CreateSubjectRequest;

import com.uday.learningNavigator.repositoryServices.ISubjectRepositoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class SubjectServiceImpl implements ISubjectService{

    @Autowired
    private ISubjectRepositoryService subjectRepositoryService;

    @Override
    public SubjectDto createSubject(CreateSubjectRequest createSubjectRequest) {
        String subjectName = createSubjectRequest.getSubjectName();
        return subjectRepositoryService.createSubject(subjectName);
        
    }

    @Override
    public SubjectDto findSubjectById(long subjectId) throws SubjectNotFoundException {
        return subjectRepositoryService.findSubjectById(subjectId);
        
    }

    @Override
    public List<SubjectDto> findAllSubjects() {
        return subjectRepositoryService.findAllSubjects();
        
    }

    @Override
    public void deleteSubject(long subjectId) throws SubjectNotFoundException {
        subjectRepositoryService.deleteSubject(subjectId);
    }
     
}
