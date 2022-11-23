package com.academy.edu.jdbc.web;

import com.academy.edu.jdbc.service.course.Course;
import com.academy.edu.jdbc.service.course.CourseService;
import com.academy.edu.jdbc.service.course.DefaultCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(DefaultCourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courseList")
    public String courseListView(Model model) {
        List<Course> allCourses = courseService.findAllCourses();

        model.addAttribute("courses", allCourses);
        return "courseList";
    }

    @GetMapping("/registration")
    public String registrationView() {
        return "registrationForm";
    }

    @GetMapping("/modify")
    public String modifyView(@RequestParam("id") int id, Model model) {
        Course course = courseService.findByCourseId(id);
        model.addAttribute("course", course);
        return "modifyForm";
    }

    @GetMapping("/delete")
    public String doDeleteCourse(@RequestParam("id") int id) {
        int deleteResult = courseService.deletedById(id);

        return "redirect:courseList";
    }


}
