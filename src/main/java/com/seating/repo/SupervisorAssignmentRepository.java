package com.seating.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seating.model.SupervisorAssignment;

public interface SupervisorAssignmentRepository extends JpaRepository<SupervisorAssignment, Long>{
	
	List<SupervisorAssignment> findBySupervisorNameAndExamDateAndExamTime(String supervisorName, Date examDate, String examTime);
	

}
