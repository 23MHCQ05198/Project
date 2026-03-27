package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.Repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class ExcelServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private ExcelService service;
    
    @Test
    void testUploadExcel() {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "student.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                new byte[0]
        );

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.loadExcel(file);   // ✅ FIXED
        });

        assertEquals("Error reading Excel", exception.getMessage());
    }
}