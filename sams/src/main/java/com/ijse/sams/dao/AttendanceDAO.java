package com.ijse.sams.dao;

import com.ijse.sams.entity.Attendance;
import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AttendanceDAO {

    public void saveAttendance(Attendance attendance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(attendance);
        transaction.commit();
        session.close();
    }

    public List<Attendance> getAttendanceByClass(ClassSchedule classSchedule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Attendance where classSchedule = :classSchedule";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("classSchedule", classSchedule);
            return query.list();

        }
    }

}
