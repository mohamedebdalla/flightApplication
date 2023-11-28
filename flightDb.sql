-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: flightdb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `aircrafts`
--

DROP TABLE IF EXISTS `aircrafts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aircrafts` (
  `AircraftID` int NOT NULL,
  `OwnedBy` varchar(45) NOT NULL,
  PRIMARY KEY (`AircraftID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircrafts`
--

LOCK TABLES `aircrafts` WRITE;
/*!40000 ALTER TABLE `aircrafts` DISABLE KEYS */;
INSERT INTO `aircrafts` VALUES (1,'WestJet'),(2,'Delta'),(3,'Lufthansa'),(4,'Emirates'),(5,'Air Canada'),(6,'British Airways'),(7,'United Airlines'),(8,'Qatar Airways'),(9,'American Airlines'),(10,'Singapore Airlines');
/*!40000 ALTER TABLE `aircrafts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crews`
--

DROP TABLE IF EXISTS `crews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crews` (
  `CrewID` int NOT NULL,
  `FlightID` int NOT NULL,
  PRIMARY KEY (`CrewID`),
  KEY `FlightID_idx` (`FlightID`),
  CONSTRAINT `crewFlightID` FOREIGN KEY (`FlightID`) REFERENCES `flights` (`FlightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crews`
--

LOCK TABLES `crews` WRITE;
/*!40000 ALTER TABLE `crews` DISABLE KEYS */;
INSERT INTO `crews` VALUES (1,1),(2,2),(3,3),(4,3),(5,4),(6,5),(7,6),(8,7),(9,7),(10,8),(11,9),(12,10),(13,11),(14,12),(15,13),(16,14),(17,15),(18,15),(33,15),(34,15),(19,16),(35,16),(36,16),(20,17),(37,17),(38,17),(21,18),(39,18),(40,18),(22,19),(41,19),(42,19),(23,20),(24,20),(43,20),(44,20),(25,21),(45,21),(46,21),(26,22),(27,22),(47,22),(48,22),(28,23),(49,23),(50,23),(29,24),(51,24),(52,24),(30,25),(53,25),(54,25),(31,26),(32,26),(55,26),(56,26),(57,27),(58,27),(59,28),(60,28),(61,29),(62,29),(63,30),(64,30);
/*!40000 ALTER TABLE `crews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `FlightID` int NOT NULL AUTO_INCREMENT,
  `FlightNumber` varchar(45) NOT NULL,
  `Origin` varchar(45) NOT NULL,
  `Destination` varchar(70) NOT NULL,
  `DepartureDateTime` varchar(70) NOT NULL,
  `ArrivalDateTime` varchar(70) NOT NULL,
  `AircraftID` int NOT NULL,
  PRIMARY KEY (`FlightID`),
  KEY `AircraftID_idx` (`AircraftID`),
  CONSTRAINT `AircraftID` FOREIGN KEY (`AircraftID`) REFERENCES `aircrafts` (`AircraftID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,'FL001','New York','London','2023-12-23 08:00:00','2023-12-24 14:00:00',1),(2,'FL002','Los Angeles','Tokyo','2023-12-23 09:30:00','2023-12-24 15:30:00',2),(3,'FL003','Paris','Sydney','2023-12-23 11:00:00','2023-12-24 18:00:00',3),(4,'FL004','Dubai','New York','2023-12-23 13:00:00','2023-12-24 20:00:00',4),(5,'FL005','Beijing','Paris','2023-12-24 15:30:00','2023-12-25 22:30:00',5),(6,'FL006','London','Los Angeles','2023-12-24 17:00:00','2023-12-25 23:30:00',6),(7,'FL007','Sydney','New York','2023-12-24 19:45:00','2023-12-25 04:30:00',7),(8,'FL008','Tokyo','Paris','2023-12-25 21:00:00','2023-12-26 05:00:00',8),(9,'FL009','New York','Dubai','2023-12-25 23:15:00','2023-12-26 07:00:00',9),(10,'FL010','Paris','Beijing','2023-12-25 01:30:00','2023-12-26 10:45:00',10),(11,'FL011','Los Angeles','London','2023-12-25 03:45:00','2023-12-26 12:15:00',1),(12,'FL012','New York','Sydney','2023-12-26 06:00:00','2023-12-27 18:00:00',2),(13,'FL013','Tokyo','Dubai','2023-12-26 08:30:00','2023-12-27 15:45:00',3),(14,'FL014','Paris','Beijing','2023-12-26 10:15:00','2023-12-27 20:30:00',4),(15,'FL015','London','Sydney','2023-12-27 12:30:00','2023-12-28 02:30:00',5),(16,'FL016','Dubai','Los Angeles','2023-12-27 14:45:00','2023-12-28 22:00:00',6),(17,'FL017','Beijing','New York','2023-12-27 17:00:00','2023-12-28 23:30:00',7),(18,'FL018','Sydney','Paris','2023-12-28 19:15:00','2023-12-29 03:30:00',8),(19,'FL019','London','Dubai','2023-12-28 21:30:00','2023-12-29 04:45:00',9),(20,'FL020','Tokyo','Los Angeles','2023-12-28 23:45:00','2023-12-29 07:00:00',10),(21,'FL021','New York','London','2023-12-29 01:00:00','2023-12-30 09:00:00',1),(22,'FL022','Los Angeles','Tokyo','2023-12-29 23:15:00','2023-12-30 07:00:00',4),(23,'FL023','Toronto','London','2023-12-29 03:30:00','2023-12-30 12:45:00',2),(24,'FL024','Vancouver','Paris','2023-12-30 05:45:00','2023-12-31 15:00:00',3),(25,'FL025','Montreal','Dubai','2023-12-30 08:00:00','2023-12-31 18:30:00',4),(26,'FL026','Calgary','New York','2023-12-30 10:15:00','2023-12-30 22:30:00',5),(27,'FL027','Ottawa','Los Angeles','2023-12-31 12:30:00','2023-12-31 19:00:00',6),(28,'FL028','Edmonton','Sydney','2023-12-31 14:45:00','2024-01-01 23:30:00',7),(29,'FL029','Quebec City','Tokyo','2023-12-31 17:00:00','2024-01-01 02:15:00',8),(30,'FL030','Halifax','Beijing','2023-12-31 19:15:00','2024-01-02 05:45:00',9);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `PassengerID` int NOT NULL,
  `FlightID` int NOT NULL,
  `SeatNumber` varchar(45) NOT NULL,
  `TicketInsurance` varchar(45) NOT NULL,
  `PaymentStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`PassengerID`),
  KEY `FlightID_idx` (`FlightID`),
  CONSTRAINT `FlightID` FOREIGN KEY (`FlightID`) REFERENCES `flights` (`FlightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `UserType` varchar(45) NOT NULL,
  `Address` varchar(120) DEFAULT NULL,
  `Name` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'mohamed','1234','email','userType',NULL,NULL),(3,'test1','1111','email','registered','address','test1'),(4,'aya','rytyf','email','userType',NULL,NULL),(5,'raisa','ejfroweijf','email','registered','raisa@gmail.com','raisa');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26 20:52:47
