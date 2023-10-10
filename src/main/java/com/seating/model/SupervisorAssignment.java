package com.seating.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.seating.service.DateToStringMapConverter;

@Entity
@Table(name = "SupervisorAssignment")
public class SupervisorAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supervisor_name")
    private String supervisorName;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "exam_time")
    private String examTime;
    
    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block; // New field to store the assigned block number
    public Block getBlock() {
        return block;
    }

    public void setBlock(Block assignedBlock) {
        this.block = assignedBlock;
    }

    // Initialize assignments map in the default constructor
    public SupervisorAssignment() {
        assignments = new HashMap<>();
    }

    @ElementCollection
    @MapKeyColumn(name = "assignment_date")
    @Column(name = "assignment_details")
    @Convert(converter = DateToStringMapConverter.class)
    private Map<Date, String> assignments = new HashMap<>();

    // Getters and setters

    public void addAssignment(Date date, String time) {
        // Initialize the assignments map if it's not already initialized
        if (assignments == null) {
            assignments = new HashMap<>();
        }

        // Store the assignment details in the map
        assignments.put(date, time);
    }

    public Map<Date, String> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Date, String> assignments) {
        this.assignments = assignments;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public SupervisorAssignment(Long id, String supervisorName, Date examDate, String examTime,
			Map<Date, String> assignments,Block block) {
		super();
		this.id = id;
		this.supervisorName = supervisorName;
		this.examDate = examDate;
		this.examTime = examTime;
		this.assignments = assignments;
		this.block=block;
	}

	@Override
	public String toString() {
		return "SupervisorAssignment [id=" + id + ", supervisorName=" + supervisorName + ", examDate=" + examDate
				+ ", examTime=" + examTime + ", assignments=" + assignments + ", block=" +block+"]";
	}
	
    
    
}
