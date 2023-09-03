package com.seating.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seating.model.Admin;
import com.seating.model.TimeTableForm;
import com.seating.model.Time_Table;
import com.seating.repo.AdminRepo;
import com.seating.repo.Time_TableRepository;


@Controller
public class HomeController {
	private final Time_TableRepository timeTableRepository;

	@Autowired
	AdminRepo adminRepo;
	
    @Autowired	
    public HomeController(Time_TableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }
	@GetMapping("/")
	public String home() {
		
		return "login";
	}
	
	@PostMapping("/login")
	public String authenticateUser(Model model, String userid, String password) {
	    Admin user = adminRepo.findByUserid(userid);
	   
	    if (user != null && user.getPassword().equals(password)) 
	    {
	        if (user.getRole().equals("ROLE_ADMIN")) {
	            return "home1";
	        } else {
	            return "home";
	        }
	    } else 
	    {
	        model.addAttribute("error", "Authentication failed");
	        return "login";
	    }
	}

	
	@GetMapping("/time-table")
	public String timetable(Model model) {
		List<Time_Table> examDetailsList = new ArrayList<Time_Table>(); 
	    model.addAttribute("examDetails", examDetailsList);
		return "timetable";
	}
	
	
	@PostMapping("/submit")
	public String submitForm(@ModelAttribute("examDetails") TimeTableForm timeTableForm,
	                         @RequestParam("branch") String branch,
	                         @RequestParam("sem") String semester) {

	    List<Time_Table> examDetails = timeTableForm.getExamDetails();

	    for (Time_Table exam : examDetails) {
	        exam.setBranch(branch);
	        exam.setSemester(semester);
	        timeTableRepository.save(exam);
	    }

	    return "redirect:/";
	}




	
}
