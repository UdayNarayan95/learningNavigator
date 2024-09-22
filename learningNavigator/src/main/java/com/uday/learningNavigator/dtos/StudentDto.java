package com.uday.learningNavigator.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"enrolledSubjects","enrolledExams"})
public class StudentDto {
    private long id;
    private String name;
    private Set<SubjectDto> enrolledSubjects;
    private Set< ExamDto> enrolledExams;
}
