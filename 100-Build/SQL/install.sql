USE project-manager;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: project-manager
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table project
--

DROP TABLE IF EXISTS project;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE project (
  CODE varchar(30) CHARACTER SET latin1 NOT NULL,
  TYPE varchar(45) NOT NULL COMMENT 'PROJECT or TMA',
  REFERENTIAL varchar(45) NOT NULL COMMENT 'BNPP, CSN',
  PERIOD_INFO date DEFAULT NULL COMMENT 'La periode de récuparation des valeurs Charge INIT, RAF, Reestime, ...',
  CHARGE_INIT decimal(7,2) DEFAULT NULL,
  RAF decimal(7,2) DEFAULT NULL,
  REESTIME decimal(7,2) DEFAULT NULL,
  NB_LIVRAISON int(11) DEFAULT NULL,
  NB_LIVRAISON_OK int(11) DEFAULT NULL,
  ANO_R7_NB_JOUR_PRJ int(11) DEFAULT NULL,
  ANO_R7_NB_JOUR_PRJ_ST int(11) DEFAULT NULL,
  ANO_R7_BLK_MAJ_NB int(11) DEFAULT NULL,
  ANO_R7_BLK_MAJ_NB_ST int(11) DEFAULT NULL,
  ANO_R7_BLK_NB int(11) DEFAULT NULL,
  ANO_R7_BLK_NB_ST int(11) DEFAULT NULL,
  ANO_R7_ALL_NB int(11) DEFAULT NULL,
  ANO_R7_ALL_NB_ST int(11) DEFAULT NULL,
  PRIMARY KEY (CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table preferences
--

DROP TABLE IF EXISTS preferences;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE preferences (
  CODE varchar(45) NOT NULL,
  PROP_KEY varchar(150) NOT NULL,
  PROP_VALUE varchar(150) NOT NULL,
  PREF_GROUP varchar(45) NOT NULL,
  PREF_ORDER int(11) NOT NULL,
  PRIMARY KEY (CODE,PROP_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table workspace
--

DROP TABLE IF EXISTS workspace;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE workspace (
  CODE varchar(45) NOT NULL,
  PROJECT_CODE varchar(45) NOT NULL DEFAULT '' COMMENT 'Can be Project or TMA',
  TYPE varchar(45) NOT NULL COMMENT 'COMMUN, CUSTOM',
  WKS_ORDER int(11) NOT NULL,
  LAYOUT blob,
  VISIBILITY varchar(45) NOT NULL,
  USERCRE varchar(45) NOT NULL,
  DATCRE timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  USERMAJ varchar(45) DEFAULT NULL,
  DATMAJ timestamp NULL DEFAULT NULL,
  PRIMARY KEY (CODE,PROJECT_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table view
--

DROP TABLE IF EXISTS view;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE view (
  CODE varchar(45) NOT NULL,
  PROJECT_CODE varchar(45) NOT NULL COMMENT 'Project can be null if the workspace is generic',
  WORKSPACE_CODE varchar(45) NOT NULL,
  PROP_KEY varchar(45) NOT NULL,
  PROP_VALUE varchar(255) NOT NULL,
  PRIMARY KEY (CODE,PROJECT_CODE,WORKSPACE_CODE,PROP_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table perspective
--

DROP TABLE IF EXISTS perspective;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE perspective (
  USER_LOGIN varchar(45) NOT NULL,
  PROJECT_CODE varchar(45) NOT NULL,
  PRIMARY KEY (USER_LOGIN,PROJECT_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table users
--

DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE users (
  LOGIN varchar(20) NOT NULL,
  SURNAME varchar(30) NOT NULL,
  NAME varchar(30) NOT NULL,
  PASSWORD varchar(30) NOT NULL,
  EMAIL_1 varchar(45) NOT NULL,
  EMAIL_2 varchar(45) NOT NULL,
  PRIMARY KEY (LOGIN)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-11 18:46:22
