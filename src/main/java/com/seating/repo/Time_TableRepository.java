package com.seating.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seating.model.SupervisorAssignment;
import com.seating.model.Time_Table;

@Repository
public interface Time_TableRepository extends JpaRepository<Time_Table, Long> {
	List<Time_Table> findByBranch(String branch);
	@Query("SELECT t FROM Time_Table t WHERE t.date >= CURRENT_DATE")
    List<Time_Table> findUpcomingTimetableEntries();
}

