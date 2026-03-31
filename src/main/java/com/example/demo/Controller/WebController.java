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

    // Step 1: Upload page
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    // Step 2: Upload Excel → go to manual form
    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        excelService.loadExcel(file);
        return "redirect:/add";   // 🔥 go to manual form
    }

    // Step 3: Manual marks form
    @GetMapping("/add")
    public String addPage() {
        return "add";   // your form page
    }

    // Step 4: Save manual marks → success
    @PostMapping("/add")
    public String saveStudent(
            @RequestParam String rollNumber,
            @RequestParam String name,
            @RequestParam(required = false) Integer py,
            @RequestParam(required = false) Integer ja,
            @RequestParam(required = false) Integer c,
            @RequestParam(required = false) Integer html,
            @RequestParam(required = false) Integer js,
            @RequestParam(required = false) Integer dev,
            @RequestParam(required = false) Integer cc,
            @RequestParam(required = false) Integer cns
    ) {

        Student s1 = new Student();
        s1.setRollNumber(rollNumber);
        s1.setName(name);
        s1.setSemester(1);
        s1.setPython(py != null ? py : 0);
        s1.setJava(ja != null ? ja : 0);
        s1.setC(c != null ? c : 0);
        s1.setHtmlCss(html != null ? html : 0);
        studentService.save(s1);

        Student s2 = new Student();
        s2.setRollNumber(rollNumber);
        s2.setName(name);
        s2.setSemester(2);
        s2.setJavascript(js != null ? js : 0);
        s2.setDevops(dev != null ? dev : 0);
        s2.setCc(cc != null ? cc : 0);
        s2.setCns(cns != null ? cns : 0);
        studentService.save(s2);

        return "redirect:/success";
    }

    // Step 5: Success page
    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    // Step 6: Search page
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String roll, Model model) {

        if (roll != null && !roll.isEmpty()) {
            List<Student> students = studentService.getByRoll(roll);
            model.addAttribute("students", students);
        }

        return "search";
    }
}