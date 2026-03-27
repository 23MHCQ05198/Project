package com.example.demo.Service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @InjectMocks
    private StudentService service;

    @Test
    void testGetByRollNumber() {

        Student s = new Student();
        s.setRollNumber("23MH1A05101");

        when(repo.findByRollNumber("23MH1A05101")).thenReturn(List.of(s));

        List<Student> result = service.getByRoll("23MH1A05101");

        assertEquals(1, result.size());
        assertEquals("23MH1A05101", result.get(0).getRollNumber());

        verify(repo, times(1)).findByRollNumber("23MH1A05101");
    }
}