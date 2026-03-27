package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Student;
import com.example.demo.Service.ExcelService;
import com.example.demo.Service.StudentService;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExcelService excelService;

    // Redirect root URL to upload page
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/upload";
    }

    // Upload page
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload"; // upload.html
    }

    // Handle Excel upload
    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        excelService.loadExcel(file);
        return "redirect:/search"; // after upload, go to search
    }

    // Search page
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String roll, Model model) {
        List<Student> students = (roll != null && !roll.isEmpty()) ?
                studentService.getByRoll(roll) : null;
        model.addAttribute("students", students);
        return "search"; // search.html
    }
}