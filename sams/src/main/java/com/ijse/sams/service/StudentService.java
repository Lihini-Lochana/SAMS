package com.ijse.sams.service;

import com.ijse.sams.dao.CourseDAO;
import com.ijse.sams.dao.StudentDAO;
import com.ijse.sams.entity.Course;
import com.ijse.sams.entity.Student;

import java.util.List;

public class StudentService {


    private StudentDAO studentDAO = new StudentDAO();

    public void addStudent(Student student){
        studentDAO.saveStudent(student);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(Student student) {
        studentDAO.deleteStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

}
