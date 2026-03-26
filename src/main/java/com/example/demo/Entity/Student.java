package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rollNumber;
    private String name;
    private int semester;

    // Semester 1
    private int python;
    private int java;
    private int c;
    private int htmlCss;

    // Semester 2
    private int javascript;
    private int devops;
    private int cc;
    private int cns;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public int getPython() { return python; }
    public void setPython(int python) { this.python = python; }

    public int getJava() { return java; }
    public void setJava(int java) { this.java = java; }

    public int getC() { return c; }
    public void setC(int c) { this.c = c; }

    public int getHtmlCss() { return htmlCss; }
    public void setHtmlCss(int htmlCss) { this.htmlCss = htmlCss; }

    public int getJavascript() { return javascript; }
    public void setJavascript(int javascript) { this.javascript = javascript; }

    public int getDevops() { return devops; }
    public void setDevops(int devops) { this.devops = devops; }

    public int getCc() { return cc; }
    public void setCc(int cc) { this.cc = cc; }

    public int getCns() { return cns; }
    public void setCns(int cns) { this.cns = cns; }
}