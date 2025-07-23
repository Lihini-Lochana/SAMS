package com.ijse.sams.dao;

import com.ijse.sams.entity.Attendance;
import com.ijse.sams.entity.ClassSchedule;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class AttendanceReportDAO {

    public List<Attendance> getByStudentId(int studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Attendance where student.id = :sid";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("sid", studentId);
            return query.list();

        }
    }

    public List<Attendance> getByCourseId(int courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Attendance where classSchedule.course.id = :cid";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("cid", courseId);
            return query.list();

        }
    }

    public List<Attendance> getByDateRange(LocalDate from, LocalDate to) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Attendance where date between :from AND :to";
            Query<Attendance> query = session.createQuery(hql, Attendance.class);
            query.setParameter("from", from);
            query.setParameter("to", to);
            return query.list();

        }
    }
}
