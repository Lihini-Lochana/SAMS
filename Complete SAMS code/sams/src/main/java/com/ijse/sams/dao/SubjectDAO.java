package com.ijse.sams.dao;

import com.ijse.sams.entity.Student;
import com.ijse.sams.entity.Subject;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SubjectDAO {
    public List<Subject> getAllSubjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = session.createQuery("FROM Subject", Subject.class).list();
        session.close();
        return subjects;
    }
}
