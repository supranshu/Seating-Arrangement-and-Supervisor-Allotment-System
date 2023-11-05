package com.seating.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Time_Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branch;
    private String semester;
    @Column(name = "date")
    private java.sql.Date date;;
    private String time;
    private String subject;

   
    public Time_Table() {
    }

    // Constructor without id (if needed)
    

	public Long getId() {
		return id;
	}

	public Time_Table(Long id, String branch, String semester, java.sql.Date date, String time, String subject) {
        this.id = id;
        this.branch = branch;
        this.semester = semester;
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	

	public Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Time_Table [id=" + id + ", branch=" + branch + ", semester=" + semester + ", date=" + date + ", time="
				+ time + ", subject=" + subject + "]";
	}
	

    
}

