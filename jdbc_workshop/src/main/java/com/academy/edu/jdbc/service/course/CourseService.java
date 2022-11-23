package com.academy.edu.jdbc.service.course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();

    Course findByCourseId(int id);

    int deletedById(int id);
}
