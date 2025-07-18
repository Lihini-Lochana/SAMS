package com.ijse.sams.service;

import com.ijse.sams.dao.CourseDAO;
import com.ijse.sams.entity.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO = new CourseDAO();

    public void addCourse(String name, int duration){
        Course course = new Course();
        course.setName(name);
        course.setDuration(duration);
        courseDAO.saveCourse(course);
    }

    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    public void deleteCourse(Course course) {
        courseDAO.deleteCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }
}
