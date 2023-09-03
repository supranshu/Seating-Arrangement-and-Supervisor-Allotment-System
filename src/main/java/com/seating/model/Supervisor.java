package com.seating.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supervisor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long supervisorId;
	private String name;
	private String branch;
	private String post;
	private Date leaveFrom;
	private Date leaveTo;
	
	
	
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public Date getLeaveFrom() {
		return leaveFrom;
	}
	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}
	public Date getLeaveTo() {
		return leaveTo;
	}
	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}
	
	
	public Supervisor( String name, String branch, String post, Date leaveFrom, Date leaveTo) {
		super();
		
		this.name = name;
		this.branch = branch;
		this.post = post;
		this.leaveFrom = leaveFrom;
		this.leaveTo = leaveTo;
	}
	public Supervisor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Supervisor [supervisorId=" + supervisorId + ", name=" + name + ", branch=" + branch + ", post=" + post
				+ ", leaveFrom=" + leaveFrom + ", leaveTo=" + leaveTo + "]";
	}
	
	
	
	

}
