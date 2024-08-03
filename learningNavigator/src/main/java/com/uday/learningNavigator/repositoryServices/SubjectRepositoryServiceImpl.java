package com.uday.learningNavigator.repositoryServices;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uday.learningNavigator.dtos.SubjectDto;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;

import com.uday.learningNavigator.models.Subject;
import com.uday.learningNavigator.repositories.SubjectRepository;

import jakarta.inject.Provider;

@Service
public class SubjectRepositoryServiceImpl implements ISubjectRepositoryService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;

    @Override
    public SubjectDto createSubject(String subjectName) {
        
        ModelMapper modelMapper = modelMapperProvider.get();
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        SubjectDto subjectDto = modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
        return subjectDto;
    }

    @Override
    public SubjectDto findSubjectById(long subjectId) throws SubjectNotFoundException {
        String message = String.format("Could not find subject with ID: %d", subjectId);
        ModelMapper modelMapper = modelMapperProvider.get();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(message));
        SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
        return subjectDto;
    }

    @Override
    public List<SubjectDto> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = mapToSubjectList(subjects);
        return subjectDtos;
    }

    @Override
    public void deleteSubject(long subjectId) throws SubjectNotFoundException {
        String message = String.format("Could not find subject with ID: %d", subjectId);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException(message));
        subjectRepository.delete(subject);
    }

    private List<SubjectDto> mapToSubjectList(List<Subject> subjects) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        ModelMapper modelMapper = modelMapperProvider.get();

        for(Subject subject : subjects) {
            SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
            subjectDtos.add(subjectDto);
        }
        
        return subjectDtos;
    }

}
