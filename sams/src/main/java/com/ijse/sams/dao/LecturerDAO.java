package com.ijse.sams.dao;

import com.ijse.sams.entity.Lecturer;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LecturerDAO {
    public void saveLecturer(Lecturer lecturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(lecturer);
        transaction.commit();
        session.close();
    }

    public void updateLecturer(Lecturer lecturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(lecturer);
        transaction.commit();
        session.close();
    }

    public void deleteLecturer(Lecturer lecturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(lecturer);
        transaction.commit();
        session.close();
    }

    public List<Lecturer> getAllLecturers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Lecturer> list = session.createQuery("FROM Lecturer", Lecturer.class).list();
        session.close();
        return list;
    }
}
