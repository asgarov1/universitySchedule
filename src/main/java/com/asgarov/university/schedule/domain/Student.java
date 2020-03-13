package com.asgarov.university.schedule.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Student extends Person {

    @Enumerated(EnumType.STRING)
    private Degree degree;

    public Student() {
    }

    public Student(final String firstName, final String lastName, final String email, final String password, Degree degree){
        super(firstName, lastName, email, password);
        this.degree=degree;
    }

    public Student(final String firstName, final String lastName, Degree degree){
        super(firstName, lastName, lastName.toLowerCase()+"@mail.ru", "pass");
        this.degree=degree;
    }

    @Override
    void setRole() {
        role = Role.STUDENT;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public enum Degree {
        BACHELOR,
        MASTER,
        DOCTORATE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        return degree == student.degree;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "degree=" + degree +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}