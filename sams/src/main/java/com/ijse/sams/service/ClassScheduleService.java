package com.ijse.sams.service;

import com.ijse.sams.dao.ClassScheduleDAO;
import com.ijse.sams.dao.LecturerDAO;
import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.entity.Lecturer;

import java.util.List;

public class ClassScheduleService {

    private ClassScheduleDAO classScheduleDAO = new ClassScheduleDAO();

    public void addClassSchedule(ClassSchedule classSchedule){
        classScheduleDAO.saveClassSchedule(classSchedule);
    }

    public void updateClassSchedule(ClassSchedule classSchedule) {
        classScheduleDAO.updateClassSchedule(classSchedule);
    }

    public void deleteClassSchedule(ClassSchedule classSchedule) {
        classScheduleDAO.deleteClassSchedule(classSchedule);
    }

    public List<ClassSchedule> getAllClassSchedules() {
        return classScheduleDAO.getAllClassSchedules();
    }
}
