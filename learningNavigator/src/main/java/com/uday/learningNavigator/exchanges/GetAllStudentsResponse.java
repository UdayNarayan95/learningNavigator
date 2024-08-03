package com.uday.learningNavigator.exchanges;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import com.uday.learningNavigator.dtos.StudentDto;

@Getter
@AllArgsConstructor
public class GetAllStudentsResponse {
    private List<StudentDto> students;
    
}
