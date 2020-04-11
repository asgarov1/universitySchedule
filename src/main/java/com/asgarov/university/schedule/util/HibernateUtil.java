package com.asgarov.university.schedule.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static Session getHibernateSession() {
        final SessionFactory sf = new Configuration()
                .configure()
//                .addAnnotatedClass(Course.class)
//                .addAnnotatedClass(DaySchedule.class)
//                .addAnnotatedClass(Lecture.class)
//                .addAnnotatedClass(Professor.class)
//                .addAnnotatedClass(Room.class)
//                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        return sf.openSession();
    }

}
