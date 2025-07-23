package com.ijse.sams.dao;

import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.entity.Course;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClassScheduleDAO {

    public void saveClassSchedule(ClassSchedule classSchedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(classSchedule);
        transaction.commit();
        session.close();
    }

    public void updateClassSchedule(ClassSchedule classSchedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(classSchedule);
        transaction.commit();
        session.close();
    }

    public void deleteClassSchedule(ClassSchedule classSchedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(classSchedule);
        transaction.commit();
        session.close();
    }

    public List<ClassSchedule> getAllClassSchedules() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ClassSchedule> list = session.createQuery("FROM ClassSchedule", ClassSchedule.class).list();
        session.close();
        return list;
    }
}
