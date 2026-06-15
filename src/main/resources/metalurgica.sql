CREATE DATABASE  IF NOT EXISTS `metalurgica_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `metalurgica_db`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: metalurgica_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `id_administrador` bigint NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_administrador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` VALUES (1,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',12345678,'admin@metalurgica.com','Carlos Rodríguez','1122334455');
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',87654321,'cliente1@ejemplo.com','Laura Gómez','1155667788'),(2,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',44556677,'cliente2@ejemplo.com','Miguel Torres','1144778899');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado_historial`
--

DROP TABLE IF EXISTS `empleado_historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado_historial` (
  `empleado_id` bigint NOT NULL,
  `tarea_id` bigint NOT NULL,
  KEY `FKjf61yhhuen0hwwe42xmukc4uu` (`tarea_id`),
  KEY `FK8o5icm374d22npftucee68hpp` (`empleado_id`),
  CONSTRAINT `FK8o5icm374d22npftucee68hpp` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`legajo`),
  CONSTRAINT `FKjf61yhhuen0hwwe42xmukc4uu` FOREIGN KEY (`tarea_id`) REFERENCES `tareas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado_historial`
--

LOCK TABLES `empleado_historial` WRITE;
/*!40000 ALTER TABLE `empleado_historial` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado_historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `tipo_acceso` varchar(31) NOT NULL,
  `legajo` bigint NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`legajo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('EMPLEADO',1,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',33445566,'empleado1@metalurgica.com','Juan Pérez','1166554433'),('EMPLEADO',2,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',22334455,'empleado2@metalurgica.com','Ana López','1177889900'),('GERENTE',3,'$2a$12$x8BpxtlXTB2qj7pTr3EdAeuhP682UXYAmg1HcIf/F/TCGgeTd5IOG',11223344,'gerente@metalurgica.com','Roberto Sánchez','1199887766');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_empleados`
--

DROP TABLE IF EXISTS `registro_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_empleados` (
  `registro_id` bigint NOT NULL,
  `empleado_id` bigint NOT NULL,
  KEY `FKhneg98l0vk6khi3d2n33r73ma` (`empleado_id`),
  KEY `FKe8gamkwqj5as304d0r6atsvpu` (`registro_id`),
  CONSTRAINT `FKe8gamkwqj5as304d0r6atsvpu` FOREIGN KEY (`registro_id`) REFERENCES `registros` (`id`),
  CONSTRAINT `FKhneg98l0vk6khi3d2n33r73ma` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`legajo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_empleados`
--

LOCK TABLES `registro_empleados` WRITE;
/*!40000 ALTER TABLE `registro_empleados` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registros`
--

DROP TABLE IF EXISTS `registros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registros` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `e_proceso` enum('ENPROCESO','ESPERA','TERMINADO') DEFAULT NULL,
  `publicado` bit(1) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `tarea_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4tsrx14tge2l7d0vs1641y2o` (`cliente_id`),
  KEY `FKitu4a5pmt6jbjb1pqxwsdyppt` (`tarea_id`),
  CONSTRAINT `FKitu4a5pmt6jbjb1pqxwsdyppt` FOREIGN KEY (`tarea_id`) REFERENCES `tareas` (`id`),
  CONSTRAINT `FKl4tsrx14tge2l7d0vs1641y2o` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registros`
--

LOCK TABLES `registros` WRITE;
/*!40000 ALTER TABLE `registros` DISABLE KEYS */;
INSERT INTO `registros` VALUES (1,'ESPERA',_binary '','Tanques para Planta Química',1,1),(2,'ENPROCESO',_binary '','Estructura Galpón',2,2),(3,'TERMINADO',_binary '\0','Puertas Oficina',1,3);
/*!40000 ALTER TABLE `registros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudes`
--

DROP TABLE IF EXISTS `solicitudes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  `e_estado_actividad` enum('ACTIVO','CANCELADO') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes`
--

LOCK TABLES `solicitudes` WRITE;
/*!40000 ALTER TABLE `solicitudes` DISABLE KEYS */;
INSERT INTO `solicitudes` VALUES (1,'Necesito cotización para estructura metálica de 200m²','ACTIVO','pablo.ruiz@gmail.com','Pablo Ruiz','1133445566'),(2,'Fabricación de rejas de seguridad para local comercial','ACTIVO','maria.lopez@hotmail.com','María López','1155778899');
/*!40000 ALTER TABLE `solicitudes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categorias` enum('LIGERO','MEDIANO','PESADO') DEFAULT NULL,
  `descripcion_general` text,
  `descripcion_material` text,
  `fecha_de_entrega` date DEFAULT NULL,
  `fecha_de_registro` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (1,'PESADO','Fabricación de tanques de 5000 litros','Acero inoxidable 304','2026-07-10',NULL),(2,'MEDIANO','Estructura para galpón industrial','Hierro galvanizado','2026-06-25',NULL),(3,'LIGERO','Puertas y ventanas para oficina','Aluminio','2026-07-05',NULL),(4,'PESADO','Base para maquinaria pesada','Acero al carbono','2026-08-01',NULL);
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-15 17:18:59
