package com.example.demo.Service;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;

@Service
public class ExcelService {

    @Autowired
    private StudentRepository repo;

    // ✅ For Controller / Testing
    public void loadExcel(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Error reading Excel");
            }

            processWorkbook(file.getInputStream());

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel");
        }
    }

    // ✅ For DataLoader (auto load from resources)
    public void loadExcelFromFile() {
        try {
            ClassPathResource resource = new ClassPathResource("student.xlsx");
            InputStream is = resource.getInputStream();

            processWorkbook(is);

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel");
        }
    }

    // ✅ Common logic (BEST PRACTICE)
    private void processWorkbook(InputStream is) throws Exception {

        Workbook workbook = new XSSFWorkbook(is);
        DataFormatter formatter = new DataFormatter();

        // ---------- SHEET 1 ----------
        Sheet sheet1 = workbook.getSheetAt(0);

        for (Row row : sheet1) {
            if (row.getRowNum() == 0) continue;

            Student s = new Student();

            s.setRollNumber(getCellValue(row.getCell(0), formatter));
            s.setName(getCellValue(row.getCell(1), formatter));
            s.setSemester(1);

            s.setPython((int) row.getCell(2).getNumericCellValue());
            s.setJava((int) row.getCell(3).getNumericCellValue());
            s.setC((int) row.getCell(4).getNumericCellValue());
            s.setHtmlCss((int) row.getCell(5).getNumericCellValue());

            repo.save(s);
        }

        // ---------- SHEET 2 ----------
        Sheet sheet2 = workbook.getSheetAt(1);

        for (Row row : sheet2) {
            if (row.getRowNum() == 0) continue;

            Student s = new Student();

            s.setRollNumber(getCellValue(row.getCell(0), formatter));
            s.setName(getCellValue(row.getCell(1), formatter));
            s.setSemester(2);

            s.setJavascript((int) row.getCell(2).getNumericCellValue());
            s.setDevops((int) row.getCell(3).getNumericCellValue());
            s.setCc((int) row.getCell(4).getNumericCellValue());
            s.setCns((int) row.getCell(5).getNumericCellValue());

            repo.save(s);
        }

        workbook.close();
    }

    private String getCellValue(Cell cell, DataFormatter formatter) {
        if (cell == null) return "";
        return formatter.formatCellValue(cell);
    }
}