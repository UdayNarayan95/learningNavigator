package com.uday.learningNavigator.exchanges;
import java.util.List;

import com.uday.learningNavigator.dtos.SubjectDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllSubjectsResponse {
    private List<SubjectDto>subjects;
}
