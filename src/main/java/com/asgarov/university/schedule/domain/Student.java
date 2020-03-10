package com.asgarov.university.schedule.domain;

public class Student extends Person {

    private Degree degree;

    public Student() {
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
}