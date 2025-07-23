package com.ijse.sams.service;

import com.ijse.sams.dao.SubjectDAO;
import com.ijse.sams.entity.Subject;

import java.util.List;

public class SubjectService {
    private SubjectDAO subjectDAO = new SubjectDAO();

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }


}
