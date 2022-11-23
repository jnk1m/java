package com.academy.edu.jdbc.service.course;

import com.academy.edu.jdbc.service.subject.Subject;
import com.academy.edu.jdbc.service.teacher.Teacher;

import java.util.Date;

public class Course {
    private int id;
    private final Subject subject;
    private final Teacher teacher;
    private final Date createdAt;

    public Course(Subject subject, Teacher teacher, Date createdAt) {
        this.subject = subject;
        this.teacher = teacher;
        this.createdAt = createdAt;
    }

    public Course(int id, Subject subject, Teacher teacher, Date createdAt) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", createdAt=" + createdAt +
                '}';
    }
}