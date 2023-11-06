package com.seating.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seating.model.SupervisorAssignment;
import com.seating.repo.SupervisorAssignmentRepository;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.util.List;



 
@Service
public class ReportGenerationService {

	@Autowired
	SupervisorAssignmentRepository assignmentRepository;
    
    
   

 // ...

 public byte[] generateReport(String year, String branch) {
     try {

    	 List<SupervisorAssignment> supers=assignmentRepository.findAll();
    	 JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(supers);

         InputStream reportTemplate = getClass().getResourceAsStream("/static/super.jrxml");
         JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);

         
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

         return outputStream.toByteArray();
     } catch (JRException e) {
         e.printStackTrace();
        
         return null;
     } catch (Exception e) {
         e.printStackTrace();
         
         return null;
     }
 }


}

