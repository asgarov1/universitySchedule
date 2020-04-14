package com.asgarov.university.schedule.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {MERGE, REFRESH, PERSIST})
    private List<Student> registeredStudents = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(cascade = ALL, mappedBy = "course")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Lecture> lectures = new ArrayList<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, List<Student> registeredStudents, Professor professor, List<Lecture> lectures) {
        this.name = name;
        this.registeredStudents = registeredStudents;
        this.professor = professor;
        this.lectures = lectures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void addStudent(Student student) {
        registeredStudents.add(student);
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    public void removeLecture(Lecture lecture) {
        lectures.remove(lecture);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != null ? !id.equals(course.id) : course.id == null) return false;
        if (name != null ? !name.equals(course.name) : course.name == null) return false;
        if (registeredStudents != null ? !(registeredStudents.size() == course.registeredStudents.size() && registeredStudents.containsAll(course.registeredStudents)) : course.registeredStudents == null)
            return false;
        if (professor != null ? !professor.equals(course.professor) : course.professor != null) return false;
        return lectures != null ? !lectures.equals(course.lectures) : course.lectures == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (registeredStudents != null ? registeredStudents.hashCode() : 0);
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + (lectures != null ? lectures.hashCode() : 0);
        return result;
    }
}
