package org.example;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student std= new Student();


        std.setName(rs.getString("name"));
        std.setRoll(rs.getInt("roll"));
        std.setEmail(rs.getString("email"));
        return std;
    }
}
