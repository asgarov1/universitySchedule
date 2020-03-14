package com.asgarov.university.schedule.data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.asgarov.university.schedule.dao.exception.DaoException;
import com.asgarov.university.schedule.domain.Course;
import com.asgarov.university.schedule.domain.Lecture;
import com.asgarov.university.schedule.domain.Professor;
import com.asgarov.university.schedule.domain.Room;
import com.asgarov.university.schedule.domain.Student;
import com.asgarov.university.schedule.util.HibernateUtil;
import com.asgarov.university.schedule.util.SQLRunner;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    public static final String CREATE_TABLES_SQL = "createTables.sql";
    private SQLRunner sqlRunner;

    public DataGenerator(final SQLRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @PostConstruct
    private void init() throws IOException {
        //        HibernateUtil.generateTables();
        sqlRunner.runSQL(CREATE_TABLES_SQL);

        try (Session session = HibernateUtil.getHibernateSession()) {
            session.beginTransaction();

//            List<Room> rooms = generateRooms("Room A1", "Room A2", "Room A3", "Room A4", "Room B1", "Room B2",
//                    "Room B3", "Room B4");
//            rooms.forEach(session::save);
//
//            List<Student> students = generateStudents("Mark Zukerberg", "Joshua Bloch", "Bill Gates",
//                    "Elon Musk", "Nikola Tesla");
//            students.forEach(session::save);
//
//            List<Professor> professors = generateProfessors("Vasya Pupkin", "Petya Pushkin", "Sema Sirkin",
//                    "Professor Xavier", "Linus Torvalds", "Albus Dumbledore");
//            professors.forEach(session::save);
//
//            generatesCourses(students, rooms, professors, "Informatics 101", "Algorithmic Thinking", "JavaEE",
//                    "C++ in Robotics", "Data Science", "Hacking with Python", "Architecture of Networks",
//                    "Game Development with C#", "Ethical Hacking", "Non-ethical Hacking",
//                    "Programming Architectural Solutions",
//                    "Agile Methodologies").forEach(session::save);

            session.getTransaction().commit();
        }

    }

    private List<Course> generatesCourses(
            List<Student> students,
            List<Room> rooms,
            List<Professor> professors,
            String... courseNames) {
        List<Course> courses = new ArrayList<>();

        for (final String courseName : courseNames) {
            Course course = new Course(courseName);

            for (Student student : students) {
                double everyThirdBound = 0.333;
                if (Math.random() < everyThirdBound) {
                    course.registerStudent(student);
                }
            }

            int numberOfLecturesPerCourse = 12;
            course.setLectures(generateLectures(numberOfLecturesPerCourse, course, rooms));
            course.setProfessor(professors.get(randomNumberListSizeBound(professors)));
            courses.add(course);
        }
        return courses;
    }

    private List<Lecture> generateLectures(int amount, Course course, List<Room> rooms) {
        List<Lecture> lectures = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            lectures.add(new Lecture(getRandomDateTime(), rooms.get(randomNumberListSizeBound(rooms)), course));
        }
        return lectures;
    }

    private LocalDateTime getRandomDateTime() {
        int averageMonthBound = 30;
        int firstLessonHour = 9;
        int lastLessonHour = 17;
        return LocalDateTime.now()
                .plusDays(new Random().nextInt(averageMonthBound))
                .withHour(firstLessonHour)
                .plusHours(new Random().nextInt(lastLessonHour - firstLessonHour));
    }

    private int randomNumberListSizeBound(final List<?> list) {
        return new Random().nextInt(list.size() - 1);
    }

    private List<Professor> generateProfessors(String... lecturerNames) {
        List<Professor> professors = new ArrayList<>();
        for (final String lecturerName : lecturerNames) {
            String firstName = lecturerName.split(" ")[0];
            String lastName = lecturerName.split(" ")[1];
            Professor professor = new Professor(firstName, lastName, lastName.toLowerCase() + "@mail.ru", "pass");
            professors.add(professor);
        }
        return professors;
    }

    private List<Student> generateStudents(String... studentNames) {
        List<Student> students = new ArrayList<>();
        for (final String studentName : studentNames) {
            String firstName = studentName.split(" ")[0];
            String lastName = studentName.split(" ")[1];
            students.add(new Student(firstName, lastName, lastName.toLowerCase() + "@mail.ru", "pass", randomDegree()));
        }
        return students;
    }

    private Student.Degree randomDegree() {
        Student.Degree[] degrees = Student.Degree.values();
        int bound = new Random().nextInt(degrees.length);
        return degrees[bound];
    }

    private List<Room> generateRooms(String... roomNames) {
        List<Room> rooms = new ArrayList<>();
        for (final String roomName : roomNames) {
            rooms.add(new Room(roomName));
        }
        return rooms;
    }

}
