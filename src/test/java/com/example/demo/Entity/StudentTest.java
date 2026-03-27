package com.example.demo.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentTest {

	    @Test
	    void testStudentEntity() {
	        Student s = new Student();

	        s.setId(1);
	        s.setRollNumber("101");
	        s.setName("Ramya");
	        s.setSemester(1);

	        s.setPython(90);
	        s.setJava(85);
	        s.setC(80);
	        s.setHtmlCss(88);

	        s.setJavascript(92);
	        s.setDevops(87);
	        s.setCc(85);
	        s.setCns(89);

	        assertEquals(1, s.getId());
	        assertEquals("101", s.getRollNumber());
	        assertEquals("Ramya", s.getName());
	        assertEquals(1, s.getSemester());

	        assertEquals(90, s.getPython());
	        assertEquals(85, s.getJava());
	        assertEquals(80, s.getC());
	        assertEquals(88, s.getHtmlCss());

	        assertEquals(92, s.getJavascript());
	        assertEquals(87, s.getDevops());
	        assertEquals(85, s.getCc());
	        assertEquals(89, s.getCns());
	    }
}