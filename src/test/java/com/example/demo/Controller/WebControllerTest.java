package com.example.demo.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.example.demo.Entity.Student;
import com.example.demo.Service.ExcelService;
import com.example.demo.Service.StudentService;

@WebMvcTest(WebController.class)
@AutoConfigureMockMvc(addFilters = false) // ❌ DISABLE SECURITY FILTERS
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private ExcelService excelService;

    @Test
    void testRootRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/upload"));
    }

    @Test
    void testUploadPageGet() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"));
    }

    @Test
    void testUploadExcelPost() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "student.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                new byte[]{1, 2, 3} // dummy content
        );

        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/search"));
    }

    @Test
    void testSearchPageNoRoll() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attribute("students", (Object) null));
    }

    @Test
    void testSearchPageWithRoll() throws Exception {
        Student s = new Student();
        s.setRollNumber("101");
        s.setName("Ramya");

        org.mockito.Mockito.when(studentService.getByRoll("101")).thenReturn(List.of(s));

        mockMvc.perform(get("/search").param("roll", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("students"));
    }
}