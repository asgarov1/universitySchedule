package com.asgarov.university.schedule.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends Person {

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany(mappedBy = "registeredStudents")
    private final List<Course> courses = new ArrayList<>();

    {
        role = Role.STUDENT;
    }

    public Student() {
    }

    public Student(
            final String firstName,
            final String lastName,
            final String email,
            final String password,
            Degree degree) {
        super(firstName, lastName, email, password);
        this.degree = degree;
    }

    public Student(final String firstName, final String lastName, Degree degree) {
        super(firstName, lastName, lastName.toLowerCase() + "@mail.ru", "pass");
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (getDegree() != student.getDegree()) return false;
        return courses.equals(student.courses);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDegree() != null ? getDegree().hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    public enum Degree {
        BACHELOR,
        MASTER,
        DOCTORATE;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", degree=" + degree +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
