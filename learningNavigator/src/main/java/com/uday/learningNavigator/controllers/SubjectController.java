package com.uday.learningNavigator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uday.learningNavigator.dtos.SubjectDto;
import com.uday.learningNavigator.exceptions.SubjectNotFoundException;
import com.uday.learningNavigator.exchanges.CreateSubjectRequest;
import com.uday.learningNavigator.exchanges.GetAllSubjectsResponse;
import com.uday.learningNavigator.services.ISubjectService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping(SubjectController.SUBJECT_API_ENDPOINT)
public class SubjectController {

    public static final String SUBJECT_API_ENDPOINT = "/subjects";

    @Autowired
    private ISubjectService subjectService;

    @PostMapping(SUBJECT_API_ENDPOINT)
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody CreateSubjectRequest createSubjectRequest) {
        SubjectDto subject = subjectService.createSubject(createSubjectRequest);
        return ResponseEntity.ok().body(subject);
    }

    @GetMapping(SUBJECT_API_ENDPOINT+"/{subjectId}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable(value = "subjectId") long subjectId) throws SubjectNotFoundException {
        SubjectDto subject = subjectService.findSubjectById(subjectId);
        return ResponseEntity.ok().body(subject);
    }

    @GetMapping(SUBJECT_API_ENDPOINT)
    public ResponseEntity<GetAllSubjectsResponse> getAllSubjects() {
        List<SubjectDto> subjects = subjectService.findAllSubjects();
        GetAllSubjectsResponse getAllSubjectsResponse = new GetAllSubjectsResponse(subjects);
        return ResponseEntity.ok().body(getAllSubjectsResponse);
    }

    @DeleteMapping(SUBJECT_API_ENDPOINT+"/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable long subjectId) throws SubjectNotFoundException {
        String message = "Successfully deleted subject with ID: " + String.valueOf(subjectId);
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok().body(message);
    }

    //TO DO
    //UpdateSubjectData
}
