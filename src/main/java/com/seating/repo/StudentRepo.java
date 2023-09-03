package com.seating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seating.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	
}
