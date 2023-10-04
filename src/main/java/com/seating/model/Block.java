package com.seating.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Block {
	
	@Id
	private long blockNo;
	private long benchCount;
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
	public Block(long blockNo, long benchCount) {
		super();
		this.blockNo = blockNo;
		this.benchCount = benchCount;
	}
	public Block() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Block [blockNo=" + blockNo + ", benchCount=" + benchCount + "]";
	}
	
    
}
