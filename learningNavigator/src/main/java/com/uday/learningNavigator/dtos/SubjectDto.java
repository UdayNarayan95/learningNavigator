package com.uday.learningNavigator.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="enrolledStudents")
public class SubjectDto {
    private long id;
    private String name;
    private Set<StudentDto> enrolledStudents;
}
