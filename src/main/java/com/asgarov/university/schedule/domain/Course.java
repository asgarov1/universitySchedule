package com.asgarov.university.schedule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Course {

    private String name;
    private List<Student> registeredStudents;
    private Professor professor;
    private List<Lecture> lectures;

    public Course() {
    }

    public void registerStudent(Student student) {
        if (registeredStudents == null) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(name, course.name)) return false;
        if (!Objects.equals(registeredStudents, course.registeredStudents))
            return false;
        if (!Objects.equals(professor, course.professor)) return false;
        return Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (registeredStudents != null ? registeredStudents.hashCode() : 0);
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + (lectures != null ? lectures.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", registeredStudents=" + registeredStudents +
                ", professor=" + professor +
                ", lectures=" + lectures +
                '}';
    }
}
