package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Service.ExcelService;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ExcelService excelService;

    @Override
    public void run(String... args) {
        excelService.loadExcel();
        System.out.println("Excel data loaded into database");
    }
}
