package com.ijse.sams.dao;

import com.ijse.sams.entity.User;
import com.ijse.sams.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO {

    public User checkLogin(String username, String password) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :u AND password = :p", User.class);
            query.setParameter("u", username);
            query.setParameter("p", password);

            return query.uniqueResult();
        }
    }
}
