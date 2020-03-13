package com.asgarov.university.schedule.util;

import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.DaySchedule;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.domain.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static void generateTables() {
        new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(DaySchedule.class)
                .addAnnotatedClass(Lecture.class)
                .addAnnotatedClass(Professor.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
    }

    public static Session getHibernateSession() {
        final SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(DaySchedule.class)
                .addAnnotatedClass(Lecture.class)
                .addAnnotatedClass(Professor.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        return sf.openSession();
    }

}
