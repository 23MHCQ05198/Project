package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    // Home → upload form
    @GetMapping("/")
    public String home() {
        return "upload";
    }

    @PostMapping("/saveBoth")
    public String saveBoth(
            @RequestParam String rollNumber,
            @RequestParam String name,
            @RequestParam int py,
            @RequestParam int ja,
            @RequestParam int c,
            @RequestParam int html,
            @RequestParam int js,
            @RequestParam int dev,
            @RequestParam int cc,
            @RequestParam int cns
    ) {

        // Semester 1
        Student s1 = new Student();
        s1.setRollNumber(rollNumber);
        s1.setName(name);
        s1.setSemester(1);
        s1.setPython(py);
        s1.setJava(ja);
        s1.setC(c);
        s1.setHtmlCss(html);

        studentService.save(s1);

        // Semester 2
        Student s2 = new Student();
        s2.setRollNumber(rollNumber);
        s2.setName(name);
        s2.setSemester(2);
        s2.setJavascript(js);
        s2.setDevops(dev);
        s2.setCc(cc);
        s2.setCns(cns);

        studentService.save(s2);

        return "redirect:/search";
    }

    // Search page
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @GetMapping("/result")
    public String getResult(@RequestParam String roll, Model model) {

        List<Student> students = studentService.getByRoll(roll);

        model.addAttribute("students", students);

        return "search";
    }
}