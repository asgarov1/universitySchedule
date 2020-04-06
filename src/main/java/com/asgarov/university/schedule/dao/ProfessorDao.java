package com.asgarov.university.schedule.dao;

import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Role;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfessorDao extends AbstractDao<Long, Professor> {

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + tableName()
                + " SET email = ?, firstName = ?, lastName = ?, password = ?, role = ? WHERE id = ?;";
    }

    @Override
    protected Professor rowMapper(final ResultSet resultSet, final int rowNum) throws SQLException {
        Professor professor = new Professor();
        professor.setId(resultSet.getLong("id"));
        professor.setEmail(resultSet.getString("email"));
        professor.setFirstName(resultSet.getString("firstName"));
        professor.setLastName(resultSet.getString("lastName"));
        professor.setPassword(resultSet.getString("password"));
        professor.setRole(Role.valueOf(resultSet.getString("role")));
        return professor;
    }

    @Override
    protected Map<String, ?> createParameters(final Professor professor) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", professor.getId());
        parameters.put("email", professor.getEmail());
        parameters.put("firstName", professor.getFirstName());
        parameters.put("lastName", professor.getLastName());
        parameters.put("password", professor.getPassword());
        parameters.put("role", professor.getRole().toString());
        return parameters;
    }

    @Override
    protected Object[] updateParameters(final Professor professor) {
        return new Object[] { professor.getEmail(), professor.getFirstName(), professor.getLastName(),
                professor.getPassword(), professor.getRole().toString(), professor.getId() };
    }

    @Override
    protected String tableName() {
        return "professor";
    }
}
