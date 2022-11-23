package com.academy.edu.jdbc.service.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCourseService implements CourseService {
    CourseRepository courseRepository;

    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByCourseId(int id) {
        return courseRepository.findByCourseId(id);
    }

    @Override
    public int deletedById(int id) {
        return courseRepository.deleteById(id);
    }
}
