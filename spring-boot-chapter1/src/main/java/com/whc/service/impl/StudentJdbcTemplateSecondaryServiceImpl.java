package com.whc.service.impl;

import com.whc.model.Student;
import com.whc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by whc on 2017/3/29.
 */
@Service("studentJdbcTemplateSecondaryService")
public class StudentJdbcTemplateSecondaryServiceImpl implements StudentService {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int create(Student s) {
        return jdbcTemplate.update("INSERT INTO sbt_student(NAME, AGE, GENDER, CREATE_TIME, UPDATE_TIME) VALUES (?, ?, ?, ?, ?)", s.getName(), s.getAge(), s.getGender(), s.getCreateTime(), s.getUpdateTime());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM sbt_student WHERE ID = ?", id);
    }

    public int deleteByName(String name) {
        return jdbcTemplate.update("DELETE FROM sbt_student WHERE NAME = ?", name);
    }

    public List<Student> getAll() {
        return jdbcTemplate.query("SELECT * FROM sbt_student", new RowMapper<Student>() {
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                return new Student(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getInt("GENDER"), rs.getDate("CREATE_TIME"), rs.getDate("UPDATE_TIME"));
            }
        });
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM sbt_student");
    }

    @Transactional(transactionManager = "secondaryDataSourceTransactionManager", propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        deleteByName(student.getName());
        create(student);
        deleteByName(student.getName());
    }
}
