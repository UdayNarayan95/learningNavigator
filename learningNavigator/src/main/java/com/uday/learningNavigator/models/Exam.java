package com.uday.learningNavigator.models;
import java.util.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name="exams")
@EqualsAndHashCode(exclude="enrolledStudents")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examId;


    @OneToOne
    private Subject subject;

    @ManyToMany(mappedBy = "enrolledExams", fetch = FetchType.EAGER)
    private Set<Student> enrolledStudents=new HashSet<>();
    

    // @ManyToMany(mappedBy = "enrolledExams", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<Student> enrolledStudents = new HashSet<>();

    // public Exam() {
    //     // Initialize collections in the constructor for consistency
    //     this.enrolledStudents = new HashSet<>();
    // }
    

}
