-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flightdatabase
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aircraft` (
  `aircraftId` int NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  `isFlying` tinyint DEFAULT NULL,
  `condition` varchar(45) DEFAULT NULL,
  `checkFlightStatus` tinyint DEFAULT NULL,
  PRIMARY KEY (`aircraftId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aircraft`
--

LOCK TABLES `aircraft` WRITE;
/*!40000 ALTER TABLE `aircraft` DISABLE KEYS */;
/*!40000 ALTER TABLE `aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airline_employee`
--

DROP TABLE IF EXISTS `airline_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline_employee` (
  `employeeId` int NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline_employee`
--

LOCK TABLES `airline_employee` WRITE;
/*!40000 ALTER TABLE `airline_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `airline_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airlinemembers`
--

DROP TABLE IF EXISTS `airlinemembers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airlinemembers` (
  `memberId` int NOT NULL,
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airlinemembers`
--

LOCK TABLES `airlinemembers` WRITE;
/*!40000 ALTER TABLE `airlinemembers` DISABLE KEYS */;
/*!40000 ALTER TABLE `airlinemembers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_crew`
--

DROP TABLE IF EXISTS `flight_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_crew` (
  `crewId` int NOT NULL,
  `assignedFlightsId` int DEFAULT NULL,
  PRIMARY KEY (`crewId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_crew`
--

LOCK TABLES `flight_crew` WRITE;
/*!40000 ALTER TABLE `flight_crew` DISABLE KEYS */;
/*!40000 ALTER TABLE `flight_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `flightId` int NOT NULL,
  `aircraftId` int DEFAULT NULL,
  `departureLocationId` int DEFAULT NULL,
  `arrivalLocationId` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `seatMapId` int DEFAULT NULL,
  PRIMARY KEY (`flightId`),
  KEY `aircraftId_idx` (`aircraftId`),
  KEY `departureLocationId_idx` (`departureLocationId`),
  KEY `arrivalLocationId_idx` (`arrivalLocationId`),
  CONSTRAINT `aircraftId` FOREIGN KEY (`aircraftId`) REFERENCES `aircraft` (`aircraftId`),
  CONSTRAINT `arrivalLocationId` FOREIGN KEY (`arrivalLocationId`) REFERENCES `location` (`locationId`),
  CONSTRAINT `departureLocationId` FOREIGN KEY (`departureLocationId`) REFERENCES `location` (`locationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `locationId` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `locationCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`locationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membershipcard`
--

DROP TABLE IF EXISTS `membershipcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membershipcard` (
  `membershipCardId` int NOT NULL,
  `cardNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`membershipCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membershipcard`
--

LOCK TABLES `membershipcard` WRITE;
/*!40000 ALTER TABLE `membershipcard` DISABLE KEYS */;
/*!40000 ALTER TABLE `membershipcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `purchaseId` int NOT NULL,
  `customerId` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `isLongDistance` tinyint DEFAULT NULL,
  `ticketId` int DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `customerId_idx` (`customerId`),
  KEY `ticketId_idx` (`ticketId`),
  CONSTRAINT `customerId` FOREIGN KEY (`customerId`) REFERENCES `registered_customer` (`customerId`),
  CONSTRAINT `ticketId` FOREIGN KEY (`ticketId`) REFERENCES `tickets` (`ticketId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_customer`
--

DROP TABLE IF EXISTS `registered_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registered_customer` (
  `customerId` int NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `membershipCardId` int DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  KEY `membershipCardId_idx` (`membershipCardId`),
  CONSTRAINT `membershipCardId` FOREIGN KEY (`membershipCardId`) REFERENCES `membershipcard` (`membershipCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_customer`
--

LOCK TABLES `registered_customer` WRITE;
/*!40000 ALTER TABLE `registered_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `registered_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seatId` int NOT NULL,
  `seatNumber` varchar(45) DEFAULT NULL,
  `isBooked` tinyint DEFAULT NULL,
  PRIMARY KEY (`seatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticketId` int NOT NULL,
  `departure` varchar(45) DEFAULT NULL,
  `arrival` varchar(45) DEFAULT NULL,
  `flightNumber` int DEFAULT NULL,
  `gate` varchar(45) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `seatId` int DEFAULT NULL,
  PRIMARY KEY (`ticketId`),
  KEY `seatId_idx` (`seatId`),
  CONSTRAINT `seatId` FOREIGN KEY (`seatId`) REFERENCES `seats` (`seatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-26  9:13:49
