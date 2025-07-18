package com.ijse.sams.dao;

import com.ijse.sams.entity.Course;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAO {

    public void saveCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
    }

    public void updateCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(course);
        transaction.commit();
        session.close();
    }

    public void deleteCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(course);
        transaction.commit();
        session.close();
    }

    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> list = session.createQuery("FROM Course", Course.class).list();
        session.close();
        return list;
    }
}
