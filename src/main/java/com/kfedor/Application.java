package com.kfedor;

import com.kfedor.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Application {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.getTransaction().commit();
        }
    }

}
