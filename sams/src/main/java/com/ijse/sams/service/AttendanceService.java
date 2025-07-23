package com.ijse.sams.service;

import com.ijse.sams.dao.AttendanceDAO;
import com.ijse.sams.entity.Attendance;
import com.ijse.sams.entity.ClassSchedule;

import java.util.List;

public class AttendanceService {
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void markAttendance(List<Attendance> attendanceList){
        for (Attendance attendance : attendanceList) {
            attendanceDAO.saveAttendance(attendance);
        }
    }

    public List<Attendance> getAttendanceByClass(ClassSchedule classSchedule) {
        return attendanceDAO.getAttendanceByClass(classSchedule);
    }

}
