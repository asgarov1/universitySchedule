LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'pupkin@mail.ru','Vasya','Pupkin','pass','PROFESSOR'),(2,'pushkin@mail.ru','Petya','Pushkin','pass','PROFESSOR'),(3,'sirkin@mail.ru','Sema','Sirkin','pass','PROFESSOR'),(4,'xavier@mail.ru','Professor','Xavier','pass','PROFESSOR'),(5,'torvalds@mail.ru','Linus','Torvalds','pass','PROFESSOR'),(6,'dumbledore@mail.ru','Albus','Dumbledore','pass','PROFESSOR');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Informatics 101',1),(2,'Algorithmic Thinking',2),(3,'JavaEE',1),(4,'C++ in Robotics',3),(5,'Data Science',1),(6,'Hacking with Python',2),(7,'Architecture of Networks',1),(8,'Game Development with C#',4),(9,'Ethical Hacking',2),(10,'Non-ethical Hacking',3),(11,'Programming Architectural Solutions',3),(12,'Agile Methodologies',5),(13,'Biology',1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Room A1'),(2,'Room A2'),(3,'Room A3'),(4,'Room A4'),(5,'Room B1'),(6,'Room B2'),(7,'Room B3'),(8,'Room B4');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'2020-04-01 11:22:50.701000', 2),
                             (2,'2020-04-02 09:22:50.702000', 1),
                             (3,'2020-03-27 15:22:50.702000', 7),
                             (4,'2020-03-23 14:22:50.702000', 2),
                             (5,'2020-03-17 14:22:50.702000', 1),
                             (6,'2020-03-25 08:22:50.702000', 6),
                             (7,'2020-04-03 08:22:50.702000', 2),
                             (8,'2020-04-06 09:22:50.702000', 4),
                             (9,'2020-03-16 08:22:50.702000', 6),
                             (10,'2020-03-21 14:22:50.702000',3),
                             (11,'2020-04-10 07:22:50.702000',6),
                             (12,'2020-04-09 11:22:50.702000',3),
                             (13,'2020-03-31 13:22:50.702000',6),
                             (14,'2020-04-03 10:22:50.702000',6),
                             (15,'2020-03-16 09:22:50.702000',7),
                             (16,'2020-04-12 13:22:50.702000',2),
                             (17,'2020-04-06 12:22:50.702000',2),
                             (18,'2020-03-22 11:22:50.702000',3),
                             (19,'2020-03-17 13:22:50.702000',4),
                             (20,'2020-04-08 11:22:50.702000',3),
                             (21,'2020-04-06 12:22:50.702000',3),
                             (22,'2020-04-01 12:22:50.702000',4),
                             (23,'2020-03-18 13:22:50.703000',5),
                             (24,'2020-03-25 12:22:50.703000',3),
                             (25,'2020-04-07 11:22:50.703000',2),
                             (26,'2020-03-16 12:22:50.703000',7),
                             (27,'2020-04-04 07:22:50.703000',7),
                             (28,'2020-04-03 11:22:50.703000',6),
                             (29,'2020-03-20 09:22:50.703000',5),
                             (30,'2020-03-31 09:22:50.703000',3),
                             (31,'2020-04-01 07:22:50.703000',3),
                             (32,'2020-03-23 11:22:50.703000',5),
                             (33,'2020-03-25 13:22:50.703000',6),
                             (34,'2020-03-29 14:22:50.703000',3),
                             (35,'2020-03-29 14:22:50.703000',6),
                             (36,'2020-03-23 14:22:50.703000',3),
                             (37,'2020-03-19 11:22:50.703000',6),
                             (38,'2020-03-14 15:22:50.703000',3),
                             (39,'2020-04-10 08:22:50.703000',7),
                             (40,'2020-03-23 14:22:50.703000',1),
                             (41,'2020-04-05 14:22:50.703000',6),
                             (42,'2020-03-27 08:22:50.703000',1),
                             (43,'2020-03-16 10:22:50.703000',7),
                             (44,'2020-03-19 11:22:50.703000',3),
                             (45,'2020-03-23 08:22:50.703000',1),
                             (46,'2020-03-26 15:22:50.703000',5),
                             (47,'2020-04-01 09:22:50.703000',6),
                             (48,'2020-04-01 10:22:50.703000',5),
                             (49,'2020-04-05 09:22:50.703000',2),
                             (50,'2020-03-31 14:22:50.703000',6),
                             (51,'2020-03-16 09:22:50.703000',5),
                             (52,'2020-04-05 10:22:50.703000',4),
                             (53,'2020-04-04 13:22:50.703000',4),
                             (54,'2020-03-22 09:22:50.703000',3),
                             (55,'2020-04-03 13:22:50.703000',7),
                             (56,'2020-04-05 07:22:50.703000',7),
                             (57,'2020-03-21 09:22:50.703000',7),
                             (58,'2020-04-05 08:22:50.703000',6),
                             (59,'2020-03-24 14:22:50.703000',4),
                             (60,'2020-04-02 08:22:50.703000',3),
                             (61,'2020-03-22 08:22:50.703000',3),
                             (62,'2020-03-23 09:22:50.703000',2),
                             (63,'2020-03-17 15:22:50.703000',7),
                             (64,'2020-04-11 09:22:50.703000',4),
                             (65,'2020-03-23 14:22:50.703000',1),
                             (66,'2020-03-16 13:22:50.703000',6),
                             (67,'2020-04-11 08:22:50.703000',7),
                             (68,'2020-04-06 14:22:50.703000',6),
                             (69,'2020-04-09 12:22:50.703000',7),
                             (70,'2020-03-27 14:22:50.703000',2),
                             (71,'2020-04-01 11:22:50.703000',5),
                             (72,'2020-03-20 13:22:50.703000',5),
                             (73,'2020-04-03 14:22:50.703000',7),
                             (74,'2020-04-06 09:22:50.703000',2),
                             (75,'2020-03-23 11:22:50.703000',7),
                             (76,'2020-04-07 10:22:50.703000',2),
                             (77,'2020-04-04 08:22:50.703000',2),
                             (78,'2020-03-20 13:22:50.703000',4),
                             (79,'2020-03-14 10:22:50.703000',1),
                             (80,'2020-04-03 09:22:50.703000',1),
                             (81,'2020-03-20 10:22:50.703000',5),
                             (82,'2020-03-28 09:22:50.703000',4),
                             (83,'2020-03-23 11:22:50.703000',7),
                             (84,'2020-03-23 13:22:50.703000',4),
                             (85,'2020-03-24 08:22:50.703000',7),
                             (86,'2020-04-11 14:22:50.703000',4),
                             (87,'2020-04-04 13:22:50.703000',4),
                             (88,'2020-03-22 13:22:50.703000',1),
                             (89,'2020-03-29 08:22:50.703000',7),
                             (90,'2020-03-19 13:22:50.703000',2),
                             (91,'2020-03-24 15:22:50.703000',4),
                             (92,'2020-04-06 13:22:50.703000',4),
                             (93,'2020-03-22 13:22:50.704000',1),
                             (94,'2020-03-30 08:22:50.704000',2),
                             (95,'2020-04-07 10:22:50.704000',1),
                             (96,'2020-04-06 10:22:50.704000',6),
                             (97,'2020-03-16 08:22:50.704000',6),
                             (98,'2020-04-10 12:22:50.704000',5),
                             (99,'2020-03-21 11:22:50.704000',4),
                             (100,'2020-03-20 08:22:50.704000',5),
                             (101,'2020-04-06 11:22:50.704000',3),
                             (102,'2020-04-09 07:22:50.704000',1),
                             (103,'2020-04-06 08:22:50.704000',6),
                             (104,'2020-03-23 13:22:50.704000',4),
                             (105,'2020-03-30 07:22:50.704000',4),
                             (106,'2020-04-12 13:22:50.704000',1),
                             (107,'2020-04-09 12:22:50.704000',3),
                             (108,'2020-03-25 12:22:50.704000',5),
                             (109,'2020-03-27 09:22:50.704000',4),
                             (110,'2020-03-30 09:22:50.704000',4),
                             (111,'2020-03-31 12:22:50.704000',1),
                             (112,'2020-03-31 07:22:50.704000',2),
                             (113,'2020-03-28 09:22:50.704000',4),
                             (114,'2020-04-11 07:22:50.704000',1),
                             (115,'2020-03-29 10:22:50.704000',4),
                             (116,'2020-03-31 14:22:50.704000',7),
                             (117,'2020-04-05 13:22:50.704000',5),
                             (118,'2020-03-23 11:22:50.704000',2),
                             (119,'2020-04-03 10:22:50.704000',3),
                             (120,'2020-03-19 10:22:50.704000',1),
                             (121,'2020-03-29 07:22:50.704000',2),
                             (122,'2020-04-06 12:22:50.704000',6),
                             (123,'2020-03-28 14:22:50.704000',5),
                             (124,'2020-03-30 10:22:50.704000',1),
                             (125,'2020-04-04 10:22:50.704000',6),
                             (126,'2020-04-04 09:22:50.704000',4),
                             (127,'2020-03-22 10:22:50.704000',5),
                             (128,'2020-03-17 10:22:50.704000',1),
                             (129,'2020-03-14 09:22:50.704000',3),
                             (130,'2020-04-08 09:22:50.704000',5),
                             (131,'2020-03-14 13:22:50.704000',6),
                             (132,'2020-03-23 11:22:50.704000',4),
                             (133,'2020-03-27 13:22:50.704000',3),
                             (134,'2020-04-04 14:22:50.704000',5),
                             (135,'2020-03-21 09:22:50.704000',3),
                             (136,'2020-03-16 10:22:50.704000',3),
                             (137,'2020-03-29 14:22:50.704000',2),
                             (138,'2020-04-05 12:22:50.704000',5),
                             (139,'2020-04-05 08:22:50.704000',4),
                             (140,'2020-04-01 13:22:50.704000',4),
                             (141,'2020-04-06 09:22:50.704000',5),
                             (142,'2020-03-17 11:22:50.704000',1),
                             (143,'2020-03-24 13:22:50.704000',4),(144,'2020-04-11 11:22:50.704000',2);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `course_lectures` WRITE;
/*!40000 ALTER TABLE `course_lectures` DISABLE KEYS */;
INSERT INTO `course_lectures` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),
                                     (2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),
                                     (3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(3,30),(3,31),(3,32),(3,33),(3,34),(3,35),(3,36),
                                     (4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47),(4,48),
                                     (5,49),(5,50),(5,51),(5,52),(5,53),(5,54),(5,55),(5,56),(5,57),(5,58),(5,59),(5,60),
                                     (6,61),(6,62),(6,63),(6,64),(6,65),(6,66),(6,67),(6,68),(6,69),(6,70),(6,71),(6,72),
                                     (7,73),(7,74),(7,75),(7,76),(7,77),(7,78),(7,79),(7,80),(7,81),(7,82),(7,83),(7,84),
                                     (8,85),(8,86),(8,87),(8,88),(8,89),(8,90),(8,91),(8,92),(8,93),(8,94),(8,95),(8,96),
                                     (9,97),(9,98),(9,99),(9,100),(9,101),(9,102),(9,103),(9,104),(9,105),(9,106),(9,107),(9,108),
                                     (10,109),(10,110),(10,111),(10,112),(10,113),(10,114),(10,115),(10,116),(10,117),(10,118),(10,119),(10,120),
                                     (11,121),(11,122),(11,123),(11,124),(11,125),(11,126),(11,127),(11,128),(11,129),(11,130),(11,131),(11,132),
                                     (12,133),(12,134),(12,135),(12,136),(12,137),(12,138),(12,139),(12,140),(12,141),(12,142),(12,143),(12,144);
/*!40000 ALTER TABLE `course_lectures` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'zukerberg@mail.ru','Mark','Zukerberg','pass','STUDENT','DOCTORATE'),(2,'bloch@mail.ru','Joshua','Bloch','pass','STUDENT','DOCTORATE'),(3,'gates@mail.ru','Bill','Gates','pass','STUDENT','BACHELOR'),(4,'musk@mail.ru','Elon','Musk','pass','STUDENT','BACHELOR'),(5,'tesla@mail.ru','Nikola','Tesla','pass','STUDENT','BACHELOR');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `courses_students` WRITE;
/*!40000 ALTER TABLE `courses_students` DISABLE KEYS */;
INSERT INTO `courses_students` VALUES (1,2),
                                      (2,4),
                                      (3,3),
                                      (3,4),
                                      (4,1),
                                      (4,3),
                                      (4,5),
                                      (5,2),
                                      (6,2),
                                      (6,4),
                                      (6,5),
                                      (7,1),
                                      (7,2),
                                      (7,4),
                                      (7,5),
                                      (8,2),
                                      (8,3),
                                      (8,4),
                                      (9,2),
                                      (10,1),
                                      (10,3),
                                      (10,5),
                                      (11,1),
                                      (11,5);
/*!40000 ALTER TABLE `courses_students` ENABLE KEYS */;
UNLOCK TABLES;
