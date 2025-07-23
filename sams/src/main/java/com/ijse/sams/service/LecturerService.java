package com.ijse.sams.service;

import com.ijse.sams.dao.LecturerDAO;
import com.ijse.sams.entity.Lecturer;

import java.util.List;

public class LecturerService {
    private LecturerDAO lecturerDAO = new LecturerDAO();

    public void addLecturer(Lecturer lecturer){
        lecturerDAO.saveLecturer(lecturer);
    }

    public void updateLecturer(Lecturer lecturer) {
        lecturerDAO.updateLecturer(lecturer);
    }

    public void deleteLecturer(Lecturer lecturer) {
        lecturerDAO.deleteLecturer(lecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerDAO.getAllLecturers();
    }

}
