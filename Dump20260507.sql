CREATE DATABASE  IF NOT EXISTS `gimnasio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gimnasio`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gimnasio
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` int NOT NULL,
  `fecha_alta` date NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `rol` varchar(20) NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'admin','admin@admin.com',123456789,'2026-05-07','$2a$10$JbOkMpaXVfQ.Ve0aYydiFOTXkX/MB/SLWv//NkPYVZ0IK4/Jc68di','ADMIN'),(2,'pepe','pepe@gmail.com',123456788,'2026-05-07','$2a$10$8ryxqzw8IsguTJoDS33yZuuIxO/Rxt4F1XSoMurmD/Jb.sWd4hx1.','CLIENTE');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_SEQ`
--

DROP TABLE IF EXISTS `cliente_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_SEQ`
--

LOCK TABLES `cliente_SEQ` WRITE;
/*!40000 ALTER TABLE `cliente_SEQ` DISABLE KEYS */;
INSERT INTO `cliente_SEQ` VALUES (51);
/*!40000 ALTER TABLE `cliente_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_rutina`
--

DROP TABLE IF EXISTS `cliente_rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_rutina` (
  `id_cliente` int NOT NULL,
  `id_rutina` int NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `observaciones` text,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`id_cliente`,`id_rutina`),
  KEY `id_rutina` (`id_rutina`),
  KEY `FKb6cwbcvgstbop2jcigs8j80bl` (`idCliente`),
  CONSTRAINT `cliente_rutina_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE,
  CONSTRAINT `cliente_rutina_ibfk_2` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`) ON DELETE CASCADE,
  CONSTRAINT `FKb6cwbcvgstbop2jcigs8j80bl` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_rutina`
--

LOCK TABLES `cliente_rutina` WRITE;
/*!40000 ALTER TABLE `cliente_rutina` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dificultad`
--

DROP TABLE IF EXISTS `dificultad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dificultad` (
  `id_dificultad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_dificultad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dificultad`
--

LOCK TABLES `dificultad` WRITE;
/*!40000 ALTER TABLE `dificultad` DISABLE KEYS */;
INSERT INTO `dificultad` VALUES (1,'Principiante'),(2,'Intermedio'),(3,'Avanzado');
/*!40000 ALTER TABLE `dificultad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejercicio`
--

DROP TABLE IF EXISTS `ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejercicio` (
  `idEjercicio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `video` varchar(255) NOT NULL,
  `id_grupo_muscular` int NOT NULL,
  PRIMARY KEY (`idEjercicio`),
  KEY `id_grupo_muscular` (`id_grupo_muscular`),
  CONSTRAINT `ejercicio_ibfk_1` FOREIGN KEY (`id_grupo_muscular`) REFERENCES `grupo_muscular` (`id_grupo_muscular`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejercicio`
--

LOCK TABLES `ejercicio` WRITE;
/*!40000 ALTER TABLE `ejercicio` DISABLE KEYS */;
INSERT INTO `ejercicio` VALUES (1,'Press banca','Ejercicio de pecho','https://fitcron.com/wp-content/uploads/2021/03/00251301-Barbell-Bench-Press_Chest-FIX_720.gif',1),(2,'Dominadas','Ejercicio de espalda','https://fitcron.com/wp-content/uploads/2021/04/18661301-Wide-Grip-Pull-Up-on-Dip-Cage_Back_720.gif',2),(3,'Sentadillas','Ejercicio de piernas','https://fitcron.com/wp-content/uploads/2021/04/00431301-Barbell-Full-Squat_Thighs_720.gif',3),(4,'Curl bíceps','Ejercicio de brazos','https://homeworkouts.org/wp-content/uploads/anim-dumbbell-bicep-curls.gif',4);
/*!40000 ALTER TABLE `ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenador`
--

DROP TABLE IF EXISTS `entrenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador` (
  `idEntrenador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `especialidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEntrenador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
INSERT INTO `entrenador` VALUES (1,'Carlos',''),(2,'Ana',''),(3,'Luis','');
/*!40000 ALTER TABLE `entrenador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenador_SEQ`
--

DROP TABLE IF EXISTS `entrenador_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador_SEQ`
--

LOCK TABLES `entrenador_SEQ` WRITE;
/*!40000 ALTER TABLE `entrenador_SEQ` DISABLE KEYS */;
INSERT INTO `entrenador_SEQ` VALUES (1);
/*!40000 ALTER TABLE `entrenador_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad` (
  `id_especialidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id_especialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` VALUES (1,'Fuerza','Entrenamiento de fuerza y musculación'),(2,'Cardio','Resistencia cardiovascular'),(3,'Rehabilitación','Recuperación de lesiones');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_muscular`
--

DROP TABLE IF EXISTS `grupo_muscular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_muscular` (
  `id_grupo_muscular` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_grupo_muscular`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_muscular`
--

LOCK TABLES `grupo_muscular` WRITE;
/*!40000 ALTER TABLE `grupo_muscular` DISABLE KEYS */;
INSERT INTO `grupo_muscular` VALUES (1,'Pecho','Músculos del pecho'),(2,'Espalda','Músculos dorsales'),(3,'Piernas','Músculos inferiores'),(4,'Brazos','Bíceps y tríceps');
/*!40000 ALTER TABLE `grupo_muscular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_muscular_SEQ`
--

DROP TABLE IF EXISTS `grupo_muscular_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_muscular_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_muscular_SEQ`
--

LOCK TABLES `grupo_muscular_SEQ` WRITE;
/*!40000 ALTER TABLE `grupo_muscular_SEQ` DISABLE KEYS */;
INSERT INTO `grupo_muscular_SEQ` VALUES (1);
/*!40000 ALTER TABLE `grupo_muscular_SEQ` ENABLE KEYS */;
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
-- Table structure for table `progreso`
--

DROP TABLE IF EXISTS `progreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progreso` (
  `id_progreso` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_ejercicio` int NOT NULL,
  `peso_utilizado` double NOT NULL,
  `repeticiones` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_progreso`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_ejercicio` (`id_ejercicio`),
  CONSTRAINT `progreso_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE,
  CONSTRAINT `progreso_ibfk_2` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicio` (`idEjercicio`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progreso`
--

LOCK TABLES `progreso` WRITE;
/*!40000 ALTER TABLE `progreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `progreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rutina`
--

DROP TABLE IF EXISTS `rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutina` (
  `id_rutina` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `id_dificultad` int NOT NULL,
  PRIMARY KEY (`id_rutina`),
  KEY `id_dificultad` (`id_dificultad`),
  CONSTRAINT `rutina_ibfk_1` FOREIGN KEY (`id_dificultad`) REFERENCES `dificultad` (`id_dificultad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rutina`
--

LOCK TABLES `rutina` WRITE;
/*!40000 ALTER TABLE `rutina` DISABLE KEYS */;
INSERT INTO `rutina` VALUES (1,'Rutina básica','Inicio en el gimnasio',1),(2,'Rutina intermedia','Mejora física general',2),(3,'Rutina avanzada','Alta intensidad',3);
/*!40000 ALTER TABLE `rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rutina_ejercicio`
--

DROP TABLE IF EXISTS `rutina_ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rutina_ejercicio` (
  `id_rutina` int NOT NULL,
  `id_ejercicio` int NOT NULL,
  `series` int NOT NULL,
  `repeticiones` int NOT NULL,
  `peso_recomendado` double NOT NULL,
  `tiempo_ejecucion` time NOT NULL,
  `tiempo_descanso` time NOT NULL,
  `orden` int NOT NULL,
  PRIMARY KEY (`id_rutina`,`id_ejercicio`),
  KEY `id_ejercicio` (`id_ejercicio`),
  CONSTRAINT `rutina_ejercicio_ibfk_1` FOREIGN KEY (`id_rutina`) REFERENCES `rutina` (`id_rutina`) ON DELETE CASCADE,
  CONSTRAINT `rutina_ejercicio_ibfk_2` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicio` (`idEjercicio`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rutina_ejercicio`
--

LOCK TABLES `rutina_ejercicio` WRITE;
/*!40000 ALTER TABLE `rutina_ejercicio` DISABLE KEYS */;
INSERT INTO `rutina_ejercicio` VALUES (1,1,3,10,20,'00:01:00','00:00:30',1),(1,4,3,12,10,'00:01:00','00:00:30',2),(2,1,4,8,40,'00:01:30','00:00:45',1),(2,2,4,8,0,'00:01:30','00:00:45',2),(3,3,5,6,80,'00:02:00','00:01:00',1);
/*!40000 ALTER TABLE `rutina_ejercicio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-07 12:23:11
