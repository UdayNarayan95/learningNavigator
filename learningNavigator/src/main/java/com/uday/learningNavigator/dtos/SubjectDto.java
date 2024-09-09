package com.uday.learningNavigator.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="enrolledStudents")
public class SubjectDto {
    private long id;
    private String name;

    @JsonIgnore
    private Set<StudentDto> enrolledStudents;
}
