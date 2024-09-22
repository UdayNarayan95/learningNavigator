package com.uday.learningNavigator.models;


import java.util.*;

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

@Data
@Entity
@Table(name = "subjects")
@EqualsAndHashCode(exclude = "enrolledStudents")
public class Subject {

    @Id
    @Column(name = "subjectId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;  

    @Column(name = "subjectName")
    private String subjectName;

    @ManyToMany(mappedBy = "enrolledSubjects", fetch = FetchType.EAGER)
    private Set<Student> enrolledStudents = new HashSet<>();



    // @ManyToMany(mappedBy = "enrolledSubjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<Student> enrolledStudents = new HashSet<>();

    // public Subject() {
    //     // Initialize collections in the constructor for consistency
    //     this.enrolledStudents = new HashSet<>();
    // }
}

