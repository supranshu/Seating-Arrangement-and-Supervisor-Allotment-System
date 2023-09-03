package com.seating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.seating.model.Student;
import com.seating.model.Supervisor;
import com.seating.repo.SupervisorRepo;

@Controller
public class SupervisorController {

    @Autowired
    SupervisorRepo supervisorRepo;

    @GetMapping("/supervisordetails")
    public String supervisorDetailsPage(Model model) {
       
        

        return "supervisordetails";
    }

    @PostMapping("/addSupervisor")
    public String addSupervisor(@ModelAttribute Supervisor supervisor) {
        // Save supervisor data from the form
    	System.out.println(supervisor.toString());
        supervisorRepo.save(supervisor);
        return "redirect:/supervisordetails";
    }

    @PostMapping("/uploadSupervisorCSV")
    public String uploadSupervisorCSV(@RequestParam("csvFile") MultipartFile file) {
        try {
            // Create a reader for the uploaded CSV file
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip the header row

            // Iterate through CSV rows and save supervisor data
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                String name = row[0];
                String branch = row[1];
                String post = row[2];
                String fromDateStr = row[3];
                String toDateStr = row[4];

                // Parse date strings to java.sql.Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedFromDate = dateFormat.parse(fromDateStr);
                java.util.Date parsedToDate = dateFormat.parse(toDateStr);

                java.sql.Date fromDate = new java.sql.Date(parsedFromDate.getTime());
                java.sql.Date toDate = new java.sql.Date(parsedToDate.getTime());

                
                // Create and save a Supervisor object
                Supervisor supervisor = new Supervisor(name, branch, post, fromDate, toDate);
                supervisorRepo.save(supervisor);
            }

            // Close the CSV reader
            csvReader.close();
        } catch (IOException | CsvValidationException | ParseException e) {
            e.printStackTrace();
            // Handle file read/upload errors here
        }

        return "redirect:/supervisordetails";
    }
    @GetMapping("/getSupervisors")
    public String getStudents(Model model) {
    	List<Supervisor> supervisors = supervisorRepo.findAll();
        model.addAttribute("supervisors", supervisors);
        return "supervisordetails";
    }

}

