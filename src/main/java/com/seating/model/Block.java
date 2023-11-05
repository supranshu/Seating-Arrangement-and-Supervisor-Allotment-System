package com.seating.model;

import com.seating.repo.BlockRepo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Block {
	
	@Id
	private long blockNo;
	private long benchCount;
	private String blockCondition;
	public long getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(long blockNo) {
		this.blockNo = blockNo;
	}
	public long getBenchCount() {
		return benchCount;
	}
	public void setBenchCount(long benchCount) {
		this.benchCount = benchCount;
	}
	public String getBlockCondition() {
		return blockCondition;
	}
	public void setBlockCondition(String blockCondition) {
		this.blockCondition = blockCondition;
	}
	public Block(long blockNo, long benchCount, String blockCondition) {
		super();
		this.blockNo = blockNo;
		this.benchCount = benchCount;
		this.blockCondition = blockCondition;
	}
	@Override
	public String toString() {
		return "Block [blockNo=" + blockNo + ", benchCount=" + benchCount + ", blockCondition=" + blockCondition + "]";
	}
	public Block() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	 
    
}
