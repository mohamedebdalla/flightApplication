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
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `flightID` int NOT NULL,
  `email` varchar(120) DEFAULT NULL,
  `seatOption` int DEFAULT NULL,
  `ticketID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`flightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
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
  `DepartureDate` varchar(70) NOT NULL,
  `DepartureTime` varchar(45) NOT NULL,
  `ArrivalDate` varchar(70) NOT NULL,
  `ArrivalTime` varchar(45) NOT NULL,
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
INSERT INTO `flights` VALUES (0,'','','','','','','',0),(1,'FL001','Calgary','London','2023-12-23 ','08:00:00','2023-12-24 ','14:00:00',1),(2,'FL002','Calgary','Dubai','2023-12-24 ','09:30:00','2023-12-25 ','15:30:00',2),(3,'FL003','Calgary','Sydney','2023-12-25 ','11:00:00','2023-12-26 ','18:00:00',3),(4,'FL004','Calgary','New York','2023-12-26','13:00:00','2023-12-27 ','20:00:00',4),(5,'FL005','Vancouver','London','2023-12-23 ','15:30:00','2023-12-24 ','22:30:00',5),(6,'FL006','Vancouver','Dubai','2023-12-24 ','17:00:00','2023-12-25 ','23:30:00',6),(7,'FL007','Vancouver','Sydney','2023-12-25 ','19:45:00','2023-12-26 ','04:30:00',7),(8,'FL008','Vancouver','New York','2023-12-26 ','21:00:00','2023-12-26 ','05:00:00',8),(9,'FL009','Toronto','London','2023-12-23 ','23:15:00','2023-12-24 ','07:00:00',9),(10,'FL010','Toronto','Dubai','2023-12-24 ','01:30:00','2023-12-25 ','10:45:00',10),(11,'FL011','Toronto','Sydney','2023-12-25 ','03:45:00','2023-12-26 ','12:15:00',1),(12,'FL012','Toronto','New York','2023-12-26','19:15:00','2024-12-27','05:45:00',9),(13,'FL013','Vancouver','New York','2023-12-23','09:15:00','2023-12-23','18:35:00',2),(14,'FL014','Toronto','New York','2023-12-23','16:00:00','2023-12-24','03:00:00',2),(15,'FL015','Calgary','Dubai','2023-12-25','17:00:00','2023-12-26','09:15:00',5),(16,'FL016','Calgary','Sydney','2023-12-26','08:00:00','2023-12-26','10:00:00',1),(17,'FL017','Vancouver','Sydney','2023-12-26','09:30:00','2023-12-27','02:00:00',3),(18,'FL018','Vancouver','Dubai','2023-12-26','19:00:00','2023-12-27','08:20:00',4);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `PassengerName` varchar(100) NOT NULL,
  `FlightID` int NOT NULL,
  `SeatNumber` int NOT NULL,
  `TicketID` varchar(45) NOT NULL,
  `TicketInsurance` varchar(45) NOT NULL,
  KEY `FlightID_idx` (`FlightID`),
  CONSTRAINT `FlightID` FOREIGN KEY (`FlightID`) REFERENCES `flights` (`FlightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES ('Mohamed',1,23,'TICKET1701464945882','yes'),('Mohamed',6,21,'TICKET1701465246936','yes'),('Moe',2,11,'TICKET1701470245076','yes'),('Test',1,26,'TICKET1701470537282','yes'),('john',2,11,'TICKET1701470729858','yes'),('moa',1,11,'TICKET1701471792016','yes'),('m',5,22,'TICKET1701473021547','yes'),('BookingTest',2,22,'TICKET1701475024323','yes'),('john',1,24,'TICKET1701475108355','yes'),('Jer',2,21,'TICKET1701481065208','yes'),('Gana',15,2,'TICKET1701488663145','yes');
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `CardholderName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `CardNumber` varchar(45) NOT NULL,
  `ExpiryDate` varchar(45) NOT NULL,
  `CVV` varchar(45) NOT NULL,
  `TicketPrice` double NOT NULL,
  PRIMARY KEY (`CardholderName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('339829928','mohamed.ebdalla17@gmail.com','9928192','09/2025','192',140),('882178817','mohamed.ebdalla17@gmail.com','00192891','09/2023','2291',140),('fef','mohamed.ebdalla17@gmail.com','9928918','09/2024','112',100),('Gana Hamed','mohamed.ebdalla17@gmail.com','47362519','09/2025','716',250),('gjug','jjut','gjmgm','hgj','hg',100),('Jer','mohamed.ebdalla17@gmail.com','00293','09/2010','229',100),('john','mohamed.ebdalla17@gmail.com','291829','09/2020','019',100),('johnathan','mohamed.ebdalla17@gmail.com','01939','09/2023','392',100),('mahe','mohamed.ebdalla17@gmail.com','22918','92/10','991',140),('mohamed','mohamed.ebdalla17@gmail.com','9928217','29/20','819',100),('Mohamed Ebdalla','mohamed.ebdalla17@gmail.com','33948193','09/2025','628',100),('Mohamed mo','mohamed.ebdalla17@gmail.com','299281','09/2025','817',100);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Address` varchar(120) DEFAULT NULL,
  `UserType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'admin','admin','admin','admin@gmail.com','122 University Drive NW','admin'),(6,'Mohamed','mohamed123','password','mohamed.ebdalla17@gmail.com','122 University Drive NW','registered user'),(7,'staff','staff','staff','staff@gmail.com','122 University Drive NW','staff');
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

-- Dump completed on 2023-12-01 21:50:22
