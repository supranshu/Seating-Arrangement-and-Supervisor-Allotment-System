package com.seating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seating.model.Supervisor;

public interface SupervisorRepo extends JpaRepository<Supervisor, Long> {

}
