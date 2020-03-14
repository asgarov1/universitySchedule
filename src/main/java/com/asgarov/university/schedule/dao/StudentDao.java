package com.asgarov.university.schedule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Role;
import com.asgarov.university.schedule.domain.Student;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends AbstractDao<Long, Student> {

    @Override protected String tableName() {
        return "Student";
    }

    @Override protected String getUpdateQuery() {
        return "UPDATE " + tableName() + " SET email = ?, firstName = ?, lastName = ?, password = ?, role = ?, degree = ? WHERE id = ?;";
    }

    @Override protected Student rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setEmail(resultSet.getString("email"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setPassword(resultSet.getString("password"));
        student.setRole(Role.valueOf(resultSet.getString("role")));
        student.setDegree(Student.Degree.valueOf(resultSet.getString("degree")));
        return student;
    }

    @Override protected Map<String, ?> parameters(final Student student) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", student.getId());
        parameters.put("email", student.getEmail());
        parameters.put("firstName", student.getFirstName());
        parameters.put("lastName", student.getLastName());
        parameters.put("role", student.getRole().toString());
        parameters.put("degree", student.getDegree().toString());
        return parameters;
    }

    @Override protected Object[] updateParameters(final Student student) {
        return new Object[] { student.getEmail(), student.getFirstName(), student.getLastName(),
                student.getPassword(), student.getRole().toString(), student.getDegree().toString(), student.getId()};
    }
}
