package com.seating.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.seating.model.Student;
import com.seating.repo.StudentRepo;

@Controller
public class StudentController {

	@Autowired
	StudentRepo studentRepo;
	
	@GetMapping("/studentdetails")
	 public String studentDetailsPage(Model model) {
        // Add branch and semester options to the model for dropdowns
        
        return "studentdetails";
    }
	
	@PostMapping("/addStudent")
    public String addStudent(Model model,@RequestParam Long seatNumber, @RequestParam String branch, @RequestParam String semester) {
        // Create a new Student object and save it to the repository
        Student student = new Student(seatNumber, branch, semester);
        studentRepo.save(student);
        
        model.addAttribute("seatNumber", seatNumber);
        return "redirect:/studentdetails";
    }
	
	@PostMapping("/uploadCSV")
    public String uploadCSV(@RequestParam("csvFile") MultipartFile file) throws CsvValidationException, NumberFormatException {
		try {
	        // Create a reader for the uploaded CSV file
	        Reader reader = new InputStreamReader(file.getInputStream());
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip the header row

	        // Iterate through CSV rows and save student data
	        String[] row;
	        while ((row = csvReader.readNext()) != null) {
	            String seatNumber = row[0];
	            String branch = row[1];
	            String semester = row[2];
	            long seatno=Long.parseLong(seatNumber);

	            // Create and save a Student object
	            Student student = new Student(seatno, branch, semester);
	            studentRepo.save(student);
	        }

	        // Close the CSV reader
	        csvReader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle file read/upload errors here
	    }
        return "redirect:/studentdetails";
    }
	
	@GetMapping("/getStudents")
    public String getStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "studentdetails";
    }
	
	
}
