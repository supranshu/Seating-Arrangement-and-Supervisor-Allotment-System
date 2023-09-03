package com.seating.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	private long seatnumber;
	private String branch;
	private String sem;
	
	
	
	public long getSeatnumber() {
		return seatnumber;
	}
	public void setSeatnumber(long seatnumber) {
		this.seatnumber = seatnumber;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSem() {
		return sem;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	@Override
	public String toString() {
		return "Student [seatnumber=" + seatnumber + ", branch=" + branch + ", sem=" + sem + "]";
	}
	public Student(long seatnumber, String branch, String sem) {
		super();
		this.seatnumber = seatnumber;
		this.branch = branch;
		this.sem = sem;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
