package com.academy.edu.jdbc.repository;

import com.academy.edu.jdbc.service.course.Course;
import com.academy.edu.jdbc.service.course.CourseRepository;
import com.academy.edu.jdbc.service.subject.Subject;
import com.academy.edu.jdbc.service.teacher.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Course> findAll() {

        String query = "SELECT c.id as c_id, c.teacher_id, c.subject_id, c.created_at as c_created_at," +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at," +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at \n" +
                "FROM JdbcCourses as c \n" +
                "INNER JOIN JdbcSubjects as s on c.subject_id = s.id \n" +
                "INNER JOIN JdbcTeachers as t on c.teacher_id = t.id;";


        return jdbcTemplate.query(query,
                (rs, rowNum) ->
                        new Course(rs.getInt("c_id"),
                                new Subject(rs.getLong("s_id"),
                                        rs.getString("s_name"),
                                        rs.getTimestamp("s_created_at")),
                                new Teacher(rs.getLong("t_id"),
                                        rs.getString("t_name"),
                                        rs.getTimestamp("t_created_at")),
                                rs.getTimestamp("c_created_at")));

    }

    @Override
    @Transactional(readOnly = true)
    public Course findByCourseId(int id) {
        String query = "SELECT c.id as c_id, c.teacher_id, c.subject_id, c.created_at as c_created_at," +
                "s.id as s_id, s.name as s_name, s.created_at as s_created_at," +
                "t.id as t_id, t.name as t_name, t.created_at as t_created_at \n" +
                "FROM JdbcCourses as c \n" +
                "INNER JOIN JdbcSubjects as s on c.subject_id = s.id \n" +
                "INNER JOIN JdbcTeachers as t on c.teacher_id = t.id\n" +
                "WHERE c.id = ?";

        try {
            return jdbcTemplate.queryForObject(query,
                    (rs, rowNum) ->
                            new Course(rs.getInt("c_id"),
                                    new Subject(rs.getLong("s_id"),
                                            rs.getString("s_name"),
                                            rs.getTimestamp("s_created_at")),
                                    new Teacher(rs.getLong("t_id"),
                                            rs.getString("t_name"),
                                            rs.getTimestamp("t_created_at")),
                                    rs.getTimestamp("c_created_at")), id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "DELETE FROM JdbcCourses WHERE id=?",
                id
        );
    }

}
