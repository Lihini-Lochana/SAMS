package com.ijse.sams.service;

import com.ijse.sams.dao.AttendanceReportDAO;
import com.ijse.sams.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public class AttendanceReportService {
    private final AttendanceReportDAO attendanceReportDAO = new AttendanceReportDAO();

    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceReportDAO.getByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByCourse(int courseId) {
        return attendanceReportDAO.getByCourseId(courseId);
    }

    public List<Attendance> getAttendanceByDateRange(LocalDate from, LocalDate to) {
        return attendanceReportDAO.getByDateRange(from, to);
    }
}
