package com.seating.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.model.Block;
import com.seating.model.Supervisor;
import com.seating.model.SupervisorAssignment;
import com.seating.model.Time_Table;
import com.seating.repo.BlockRepo;
import com.seating.repo.SupervisorAssignmentRepository;
import com.seating.repo.SupervisorRepo;
import com.seating.repo.Time_TableRepository;

@Service
public class ReportService {

    @Autowired
    private SupervisorRepo supervisorRepository;

    @Autowired
    private Time_TableRepository timeTableRepository;
    
    @Autowired
    private SupervisorAssignmentRepository supervisorAssignmentRepository;

    @Autowired
    private BlockRepo blockRepo;

    public List<SupervisorAssignment> generateReport() {
        List<SupervisorAssignment> reportData = new ArrayList<>();

        List<Supervisor> supervisors = supervisorRepository.findAll();

        // Fetch available blocks
        List<Block> availableBlocks = blockRepo.findAvailableBlocksLessThanMax();


        for (Supervisor supervisor : supervisors) {
            SupervisorAssignment assignment = new SupervisorAssignment();
            assignment.setSupervisorName(supervisor.getName());

            // Fetch timetable data for this supervisor's branch
            List<Time_Table> timetableEntries = timeTableRepository.findByBranch(supervisor.getBranch());

            for (Time_Table timetableEntry : timetableEntries) {
                // Check if the supervisor is on leave for this exam date
                if (!isSupervisorOnLeave(supervisor, timetableEntry.getDate())) {
                    // Check if the supervisor is already assigned to an exam on this date and time
                    if (!isSupervisorAssigned(supervisor.getName(), timetableEntry.getDate(), timetableEntry.getTime())) {
                        // Assign the supervisor to this exam
                        assignment.addAssignment(timetableEntry.getDate(), timetableEntry.getTime());

                        // Assign a block to the supervisor
                        if (!availableBlocks.isEmpty()) {
                            Block assignedBlock = availableBlocks.remove(0); // Get the first available block
                            assignment.setBlock(assignedBlock);
                        }
                    }
                }
            }

            reportData.add(assignment);
        }

        return reportData;
    }
    private boolean isSupervisorOnLeave(Supervisor supervisor, Date date) {
        Date leaveStartDate = supervisor.getLeaveFrom();
        Date leaveEndDate = supervisor.getLeaveTo();

        return date.equals(leaveStartDate) || (date.after(leaveStartDate) && date.before(leaveEndDate));
    }

    // Check if a supervisor is already assigned to an exam on a specific date and time
    private boolean isSupervisorAssigned(String supervisorName, Date examDate, String examTime) {
        // You need to implement a method to fetch assignments for a supervisor based on their name, examDate, and examTime
        List<SupervisorAssignment> assignments = supervisorAssignmentRepository.findBySupervisorNameAndExamDateAndExamTime(supervisorName, examDate, examTime);

        return !assignments.isEmpty();
    }
    
}


