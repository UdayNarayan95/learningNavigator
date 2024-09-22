package com.uday.learningNavigator.models;

import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.HashSet;

@Data
@Entity
@Table(name = "students")
@EqualsAndHashCode(exclude={"enrolledSubjects","enrolledExams"})

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    private long studentId;

    @Column(name = "studentName")
    private String studentName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Subject> enrolledSubjects=new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Exam> enrolledExams=new HashSet<>();;
    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<Subject> enrolledSubjects;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<Exam> enrolledExams;
}   
