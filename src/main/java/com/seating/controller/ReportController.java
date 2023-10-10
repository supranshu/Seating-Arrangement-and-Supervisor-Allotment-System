package com.seating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.seating.model.SupervisorAssignment;
import com.seating.service.ReportService;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public String viewReport(Model model) {
        List<SupervisorAssignment> reportData = reportService.generateReport();
        System.out.println(reportData.toString());
        model.addAttribute("reportData", reportData);
        return "reporttemplate"; 

    }
}

