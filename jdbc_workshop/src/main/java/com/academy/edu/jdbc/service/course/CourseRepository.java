package com.academy.edu.jdbc.service.course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAll();

    Course findByCourseId(int id);

    int deleteById(int id);

}
