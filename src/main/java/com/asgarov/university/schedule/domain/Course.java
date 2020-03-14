package com.asgarov.university.schedule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(cascade = ALL)
    private List<Student> registeredStudents;

    @OneToOne(cascade = ALL)
    private Professor professor;

    @OneToMany(cascade = ALL)
    private List<Lecture> lectures;

    public Course() {
    }

    public Course(final String name) {
        this.name = name;
    }

    public void registerStudent(Student student) {
        if (registeredStudents == null) {
            registeredStudents = new ArrayList<>();
        }
        registeredStudents.add(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    @Override public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final Course course = (Course) o;

        if (!Objects.equals(id, course.id))
            return false;
        if (!Objects.equals(name, course.name))
            return false;
        if (!Objects.equals(registeredStudents, course.registeredStudents))
            return false;
        if (!Objects.equals(professor, course.professor))
            return false;
        return Objects.equals(lectures, course.lectures);
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (registeredStudents != null ? registeredStudents.hashCode() : 0);
        result = 31 * result + (professor != null ? professor.hashCode() : 0);
        result = 31 * result + (lectures != null ? lectures.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registeredStudents=" + registeredStudents +
                ", professor=" + professor +
                ", lectures=" + lectures +
                '}';
    }
}
