-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: k5c201.p.ssafy.io    Database: pt
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) DEFAULT NULL,
  `exercise_name` varchar(100) DEFAULT NULL,
  `step` int DEFAULT NULL,
  `set` int DEFAULT NULL,
  `exercise_idx` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_course_exercise1_idx` (`exercise_idx`),
  CONSTRAINT `fk_course_exercise1` FOREIGN KEY (`exercise_idx`) REFERENCES `exercise` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'코스1','런지',4,2,1),(2,'코스2','사이드스탭',4,2,7),(3,'코스2','덤벨로우',2,2,3),(4,'코스1','숄더프레스',3,2,4),(5,'코스1','팔벌려높이뛰기',1,2,6),(6,'코스2','팔벌려높이뛰기',1,2,6),(7,'코스2','스쿼트',3,2,5),(8,'코스1','덤벨컬',2,2,2),(9,'코스3','숄더프레스',1,2,4),(10,'코스3','스쿼트',2,2,5);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `part` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'런지','하체','무산소'),(2,'덤벨컬','상체','무산소'),(3,'덤벨로우','상체','무산소'),(4,'숄더프레스','상체','무산소'),(5,'스쿼트','하체','무산소'),(6,'팔벌려높이뛰기','전신','유산소'),(7,'사이드스탭','전신','유산소');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_detailed_record`
--

DROP TABLE IF EXISTS `exercise_detailed_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_detailed_record` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `accuracy` int DEFAULT NULL,
  `step` int DEFAULT NULL,
  `count` int DEFAULT NULL,
  `exercise_record_idx` int DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_detailed_record`
--

LOCK TABLES `exercise_detailed_record` WRITE;
/*!40000 ALTER TABLE `exercise_detailed_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise_detailed_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_image`
--

DROP TABLE IF EXISTS `exercise_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_image` (
  `path` varchar(200) NOT NULL,
  `step` int DEFAULT NULL,
  `exercise_idx` int NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`path`),
  KEY `fk_exercise_image_exercise1_idx` (`exercise_idx`),
  CONSTRAINT `fk_exercise_image_exercise1_idx` FOREIGN KEY (`exercise_idx`) REFERENCES `exercise` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_image`
--

LOCK TABLES `exercise_image` WRITE;
/*!40000 ALTER TABLE `exercise_image` DISABLE KEYS */;
INSERT INTO `exercise_image` VALUES ('https://k5c201.p.ssafy.io/api/image/dumbbellcurl/dumbbellcurl1.PNG',1,2,'덤벨을 들고, 팔꿈치를 몸통에 밀착하듯이 붙여준 상태로 정면을 보고 선다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellcurl/dumbbellcurl2.PNG',2,2,'호흡을 들이쉬며 팔꿈치를 굽혀 이두근을 수축한다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellcurl/dumbbellcurl3.PNG',3,2,'호흡을 내쉬며 팔꿈치를 펼쳐 이두근을 이완한다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellrow/dumbbellrow0.PNG',1,3,'양손에 덤벨을 잡고, 다리는 골반 너비로 벌리고 선다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellrow/dumbbellrow1.PNG',2,3,'무릎은 가볍게 굽히고 상체를 45도 정도 앞으로 숙이고 덤벨을 무릎 양쪽에 위치한다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellrow/dumbbellrow2.PNG',3,3,'호흡을 내쉬며 양손을 옆구리 쪽으로 당기고 가슴을 활짝 편다.'),('https://k5c201.p.ssafy.io/api/image/dumbbellrow/dumbbellrow3.PNG',4,3,'무릎은 가볍게 굽히고 상체를 45도 정도 앞으로 숙이고 덤벨을 무릎 양쪽에 위치한다.'),('https://k5c201.p.ssafy.io/api/image/gif/dumbbellcurl.gif',4,2,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/dumbbellrow.gif',5,3,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/jumpingjack.gif',6,6,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/lunge.gif',4,1,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/shoulderpress.gif',5,4,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/sidestep.gif',8,7,'운동'),('https://k5c201.p.ssafy.io/api/image/gif/squat.gif',4,5,'운동'),('https://k5c201.p.ssafy.io/api/image/jumpingjack/jumpingjack1.PNG',1,6,'정자세로 서주세요'),('https://k5c201.p.ssafy.io/api/image/jumpingjack/jumpingjack2.PNG',2,6,'다리를 벌리고 손을 아래로 뻗어주세요'),('https://k5c201.p.ssafy.io/api/image/jumpingjack/jumpingjack3.PNG',3,6,'정자세로 돌아오세요'),('https://k5c201.p.ssafy.io/api/image/jumpingjack/jumpingjack4.PNG',4,6,'다리를 벌리고 손을 위로 뻗어주세요'),('https://k5c201.p.ssafy.io/api/image/jumpingjack/jumpingjack5.PNG',5,6,'정자세로 돌아오세요'),('https://k5c201.p.ssafy.io/api/image/lunge/lunge1.PNG',1,1,'우측측면으로 서서 앞뒤로 다리를 벌려주세요'),('https://k5c201.p.ssafy.io/api/image/lunge/lunge2.PNG',2,1,'복부에 힘의주고 허리를 세운 상태에서 앞다리와 뒷다리를 직각으로 만들어주세요'),('https://k5c201.p.ssafy.io/api/image/lunge/lunge3.PNG',3,1,'그대로 일어나주세요'),('https://k5c201.p.ssafy.io/api/image/shoulderpress/shoulderpress0.PNG',1,4,'양손에 덤벨을 잡고, 다리는 골반 너비로 벌리고 선다.'),('https://k5c201.p.ssafy.io/api/image/shoulderpress/shoulderpress1.PNG',2,4,'팔꿈치는 직각이 되도록, 어깨와 팔의 위쪽이 일직선이 되도록 덤벨을 들어올린다.'),('https://k5c201.p.ssafy.io/api/image/shoulderpress/shoulderpress2.PNG',3,4,'숨을 내쉬며 덤벨을 어깨 위로 들어올린다.'),('https://k5c201.p.ssafy.io/api/image/shoulderpress/shoulderpress3.PNG',4,4,'숨을 들이쉬며 다시 팔꿈치가 직각이 되도록 내린다.'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep1.PNG',1,7,'정자세로 서주세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep2.PNG',2,7,'오른쪽 다리를 벌려주세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep3.PNG',3,7,'우측으로 이동해 정자세로 서주세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep4.PNG',4,7,'중앙으로 돌아오세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep5.PNG',5,7,'좌측으로 이동해서 정자세로 서주세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep6.PNG',6,7,'왼쪽다리를 벌려주세요'),('https://k5c201.p.ssafy.io/api/image/sidestep/sidestep7.PNG',7,7,'중앙으로 돌아오세요'),('https://k5c201.p.ssafy.io/api/image/squat/squat1.PNG',1,5,'우측면을 기준으로 서주세요'),('https://k5c201.p.ssafy.io/api/image/squat/squat2.PNG',2,5,'가슴을 펴고 엉덩이를 뒤로 빼면서 앉아주세요'),('https://k5c201.p.ssafy.io/api/image/squat/squat3.PNG',3,5,'그대로 일어나주세요'),('https://k5c201.p.ssafy.io/api/image/thumbnail/dumbbellcurl.png',0,2,'덤벨컬썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/dumbbellrow.png',0,3,'덤벨로우썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/jumpingjack.png',0,6,'팔벌려높이뛰기썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/lunge.png',0,1,'런지썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/shoulderpress.png',0,4,'숄더프레스썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/sidestep.png',0,7,'사이드스텝썸네일'),('https://k5c201.p.ssafy.io/api/image/thumbnail/squat.png',0,5,'스쿼트썸네일');
/*!40000 ALTER TABLE `exercise_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_record`
--

DROP TABLE IF EXISTS `exercise_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_record` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `time` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `user_email` varchar(50) NOT NULL,
  `course_idx` int NOT NULL,
  `accuracy` int DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_exercise_record_user1_idx` (`user_email`),
  KEY `fk_exercise_record_course1_idx` (`course_idx`),
  CONSTRAINT `fk_exercise_record_course1_idx` FOREIGN KEY (`course_idx`) REFERENCES `course` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_exercise_record_user1_idx` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_record`
--

LOCK TABLES `exercise_record` WRITE;
/*!40000 ALTER TABLE `exercise_record` DISABLE KEYS */;
INSERT INTO `exercise_record` VALUES (1,60,'2021-10-31','rlaxogjs1234',1,88),(2,70,'2021-10-31','rlaxogjs1234',4,90),(3,90,'2021-11-01','aihello',1,92),(4,120,'2021-11-01','aihello',4,95),(5,60,'2021-11-03','aihello',1,88),(6,80,'2021-11-03','aihello',4,82),(7,4,'2021-11-17','aihello',9,50),(8,8,'2021-11-17','aihello',10,100),(9,17,'2021-11-17','aihello',9,100),(10,12,'2021-11-17','aihello',10,100),(11,8,'2021-11-17','jiyoung',9,0),(12,13,'2021-11-17','jiyoung',10,100),(13,6,'2021-11-18','jiyoung',9,50),(14,18,'2021-11-18','jiyoung',10,100),(15,7,'2021-11-19','aihello',9,50),(16,4,'2021-11-19','aihello',10,100),(17,7,'2021-11-19','aihello',9,50),(18,4,'2021-11-19','aihello',10,100);
/*!40000 ALTER TABLE `exercise_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `height` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','1234','admin',20,180,65,NULL,NULL),('aihello','1234','Kim',20,160,50,'남자',NULL),('jiyoung','ssafy1234!','심지',26,161,43,'여자',NULL),('rlaxogjs1234','1234','lee',20,182,70,'남',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-19 11:08:10
