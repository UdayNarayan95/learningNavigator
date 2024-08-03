package com.uday.learningNavigator.exchanges;

import com.uday.learningNavigator.dtos.ExamDto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllExamsResponse {
    private List<ExamDto> exams;
}
