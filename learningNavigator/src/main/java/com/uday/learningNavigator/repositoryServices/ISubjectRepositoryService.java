package com.uday.learningNavigator.repositoryServices;

import java.util.List;

import com.uday.learningNavigator.dtos.SubjectDto;

import com.uday.learningNavigator.exceptions.SubjectNotFoundException;

public interface ISubjectRepositoryService {
    SubjectDto createSubject(String subjectName);

    SubjectDto findSubjectById(long subjectId) throws SubjectNotFoundException;

    List<SubjectDto> findAllSubjects();

    void deleteSubject(long subjectId) throws SubjectNotFoundException;
}
