USE `universityDB`;
DROP TABLE IF EXISTS `courses_students`, `student`, `course_lectures`, `lecture`, `room`, `course`, `professor`;

CREATE TABLE `professor` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `email` varchar(255) DEFAULT NULL,
                             `firstName` varchar(255) DEFAULT NULL,
                             `lastName` varchar(255) DEFAULT NULL,
                             `password` varchar(255) DEFAULT NULL,
                             `role` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `course` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `name` varchar(255) DEFAULT NULL,
                          `professor_id` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK20bm3pmkdf360qlu26qtinakn` (`professor_id`),
                          CONSTRAINT `FK20bm3pmkdf360qlu26qtinakn` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

CREATE TABLE `room` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE `lecture` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `dateTime` datetime(6) DEFAULT NULL,
                           `room_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKafvwiqfiveub6cfhouykw9qjp` (`room_id`),
                           CONSTRAINT `FKafvwiqfiveub6cfhouykw9qjp` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;

CREATE TABLE `course_lectures` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `course_id` bigint(20) NOT NULL,
                                  `lecture_id` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `UK_lgrpaui8p3o56gempa4e28yi5` (`lecture_id`),
                                  KEY `FKagc53awx2ih1i7gfktwec4jth` (`course_id`),
                                  CONSTRAINT `FK8xx5fjdkf9b52yar3gujno9wb` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
                                  CONSTRAINT `FKagc53awx2ih1i7gfktwec4jth` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `email` varchar(255) DEFAULT NULL,
                           `firstName` varchar(255) DEFAULT NULL,
                           `lastName` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `role` varchar(255) DEFAULT NULL,
                           `degree` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `courses_students` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `course_id` bigint(20) NOT NULL,
                                  `student_id` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  CONSTRAINT `FK8burkcp7uhtoeart4nrr9oxcp` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
                                  CONSTRAINT `FKb1k0d26mobqx4gv3u2128k7my` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
