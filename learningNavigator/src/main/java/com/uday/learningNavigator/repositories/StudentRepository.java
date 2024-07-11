package com.uday.learningNavigator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.learningNavigator.models.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}
