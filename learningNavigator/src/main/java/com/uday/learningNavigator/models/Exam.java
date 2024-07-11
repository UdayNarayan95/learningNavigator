package com.uday.learningNavigator.models;
import java.util.*;

import lombok.Data;
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
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long examId;


    @OneToOne
    private Subject subject;

    @ManyToMany(mappedBy = "enrolledExams", fetch = FetchType.EAGER)
    private Set<Student> enrolledStudents=new HashSet<>();
    

}
