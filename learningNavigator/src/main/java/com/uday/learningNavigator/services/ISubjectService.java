package com.uday.learningNavigator.services;

import java.util.List;

import com.uday.learningNavigator.dtos.SubjectDto;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.CreateSubjectRequest;

public interface ISubjectService {
    SubjectDto createSubject(CreateSubjectRequest createSubjectRequest);

    SubjectDto findSubjectById(long subjectId) throws SubjectNotFoundException;

    List<SubjectDto> findAllSubjects();

    void deleteSubject(long subjectId) throws SubjectNotFoundException;
}
