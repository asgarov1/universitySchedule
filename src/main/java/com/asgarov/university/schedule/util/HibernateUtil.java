package com.asgarov.university.schedule.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static Session getHibernateSession() {
        final SessionFactory sf = new Configuration()
                .configure()
                .buildSessionFactory();

        return sf.openSession();
    }

}
