package com.seating.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.seating.model.Block;
import com.seating.model.Student;
import com.seating.repo.BlockRepo;

@Controller
public class BlockController {
    @Autowired
    private BlockRepo blockRepo;

    // Mapping to display the blockdetails.html page
    @GetMapping("/blockdetails")
    public String blockDetails(Model model) {
        // Retrieve existing block details from the repository
       
        // Create an empty Block object for the form
       

        return "blockdetails";
    }

    // Mapping to add individual block details
    @PostMapping("/addBlock")
    public String addBlock(@ModelAttribute Block block) {
        // Save the entered block details to the repository
    	System.out.println(block);
        blockRepo.save(block);
        
        // Redirect back to the blockdetails page
        return "redirect:/blockdetails";
    }

    // Mapping to handle CSV file upload
    @PostMapping("/uploadBlockCSV")
    public String uploadBlockCSV(@RequestParam("csvFile") MultipartFile file) {
        try {
            // Create a reader for the uploaded CSV file
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip the header row

            // Iterate through CSV rows and save block data
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                String blockNo = row[0];
                String benchCount = row[1];
                
                long blockno=Long.parseLong(blockNo);
                long benchcount=Long.parseLong(benchCount);
                String blockCondition=row[2];

                // Create and save a Block object
                Block block = new Block(blockno, benchcount,blockCondition);
                blockRepo.save(block);
            }

            // Close the CSV reader
            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // Handle file read/upload errors here
        }

        return "redirect:/blockdetails";
    }
    @GetMapping("/getBlocks")
    public String getBlocks(Model model) {
    	 List<Block> blocks = blockRepo.findAll();
         model.addAttribute("blocks", blocks );
         return "blockdetails";
    }
}

