package com.asgarov.university.schedule.domain;

import java.util.ArrayList;
import java.util.List;


public class Course{

    private String name;
    private List<Student> registeredStudents;
    private Professor professor;
    private List<Lecture> lectures;

    public Course() {}

    public void registerStudent(Student student){
        if(registeredStudents == null) {
            registeredStudents = new ArrayList<>();
        }
        registeredStudents.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}
