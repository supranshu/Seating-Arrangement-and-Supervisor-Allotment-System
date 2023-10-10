package com.seating.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seating.model.Block;


public interface BlockRepo extends JpaRepository<Block, Long>{

	@Query("SELECT b FROM Block b WHERE b.blockNo < (SELECT MAX(b2.blockNo) FROM Block b2)")
    List<Block> findAvailableBlocksLessThanMax();
}
