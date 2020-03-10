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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Professor professor = (Professor) o;

        return salary == professor.salary;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + salary;
        return result;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "salary=" + salary +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
