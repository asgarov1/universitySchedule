create schema if not exists universityDB collate utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `course_student`, `student`, `lecture`, `room`, `course`, `professor`;

create table professor
(
    id bigint auto_increment
        primary key,
    email varchar(255) null,
    firstName varchar(255) null,
    lastName varchar(255) null,
    password varchar(255) null,
    role varchar(255) null
);

create table course
(
    id bigint auto_increment
        primary key,
    name varchar(255) null,
    professor_id bigint null,
    constraint FKqctak3o6xmul2nu2561al3pb5
        foreign key (professor_id) references professor (id)
);

create table room
(
    id bigint auto_increment
        primary key,
    name varchar(255) null
);

create table lecture
(
    id bigint auto_increment
        primary key,
    dateTime datetime(6) null,
    course_id bigint null,
    room_id bigint null,
    constraint FKjoc9yetfohpygdvx5wv385vwb
        foreign key (course_id) references course (id)
    on DELETE cascade,
    constraint FKljp95a81uvc6kdkdr7lfvnx94
        foreign key (room_id) references room (id)
);

create table student
(
    id bigint auto_increment
        primary key,
    email varchar(255) null,
    firstName varchar(255) null,
    lastName varchar(255) null,
    password varchar(255) null,
    role varchar(255) null,
    degree varchar(255) null
);

create table course_student
(
    Course_id bigint not null,
    registeredStudents_id bigint not null,
    constraint FKg8y6buks2g8ivbtadod9o5rcy
        foreign key (Course_id) references course (id)
            ON DELETE CASCADE,
        constraint FKq4m02g8ovgdy67659x5qprk8o
        foreign key (registeredStudents_id) references student (id)
            ON DELETE CASCADE
);

