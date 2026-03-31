package com.example.demo.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Entity.Student;
import com.example.demo.Service.ExcelService;
import com.example.demo.Service.StudentService;

@WebMvcTest(WebController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private ExcelService excelService;

    // ✅ Test root redirect
    @Test
    void testRootRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/upload"));
    }

    // ✅ Test upload page GET
    @Test
    void testUploadPage() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"));
    }

    // ✅ Test upload Excel POST → redirect to /add
    @Test
    void testUploadExcel() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "students.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                new byte[]{1, 2, 3}
        );

        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/add"));

        // verify service called
        Mockito.verify(excelService, Mockito.times(1)).loadExcel(Mockito.any());
    }

    // ✅ Test add page GET
    @Test
    void testAddPage() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    // ✅ Test save student → redirect to success
    @Test
    void testSaveStudent() throws Exception {
        mockMvc.perform(post("/add")
                        .param("rollNumber", "101")
                        .param("name", "Ramya")
                        .param("py", "80")
                        .param("ja", "90")
                        .param("c", "70")
                        .param("html", "85")
                        .param("js", "75")
                        .param("dev", "80")
                        .param("cc", "60")
                        .param("cns", "65"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/success"));

        // two saves (sem1 + sem2)
        Mockito.verify(studentService, Mockito.times(2)).save(Mockito.any(Student.class));
    }

    // ✅ Test success page
    @Test
    void testSuccessPage() throws Exception {
        mockMvc.perform(get("/success"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }

    // ✅ Test search page without roll
    @Test
    void testSearchPageWithoutRoll() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeDoesNotExist("students"));
    }

    // ✅ Test search page with roll
    @Test
    void testSearchPageWithRoll() throws Exception {
        Student s = new Student();
        s.setRollNumber("101");
        s.setName("Ramya");

        Mockito.when(studentService.getByRoll("101")).thenReturn(List.of(s));

        mockMvc.perform(get("/search").param("roll", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("students"));
    }
}