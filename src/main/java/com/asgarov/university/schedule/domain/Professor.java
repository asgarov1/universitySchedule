package com.asgarov.university.schedule.domain;

public class Professor extends Person {

    private int salary;

    public Professor() {
    }

    @Override
    void setRole() {
        role = Role.PROFESSOR;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
