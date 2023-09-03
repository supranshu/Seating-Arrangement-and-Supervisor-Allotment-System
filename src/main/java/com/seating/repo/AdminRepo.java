package com.seating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seating.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{

	Admin findByUserid(String Userid);
}
