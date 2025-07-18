package com.ijse.sams.dao;


import com.ijse.sams.entity.Student;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO {
    public void saveStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    public void updateStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
    }

    public void deleteStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
        session.close();
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return list;
    }

    public List<Student> getStudentsByCourse(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = session.createQuery(
                        "FROM Student WHERE course.id = :courseId", Student.class)
                .setParameter("courseId", courseId)
                .list();
        session.close();
        return list;
    }
}
