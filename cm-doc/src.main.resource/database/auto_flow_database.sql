-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: auto_flow
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `af_flow`
--

DROP TABLE IF EXISTS `af_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `af_flow` (
  `ID` varchar(64) NOT NULL COMMENT '流程实例标识',
  `NAME` varchar(100) DEFAULT NULL COMMENT '流程实例名称',
  `PROCESSID` varchar(64) DEFAULT NULL COMMENT '该实例所使用的流程标识',
  `STATUS` varchar(20) DEFAULT NULL COMMENT '流程实例当前状态；\nActive：正在进行；\nFinish：成功完成；\nFailed：已失败',
  `VARIABLES` varchar(4000) DEFAULT NULL,
  `CREATETIME` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(32) DEFAULT NULL COMMENT '状态更新时间',
  `FINISHTIME` varchar(32) DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流程实例表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `af_flow`
--

LOCK TABLES `af_flow` WRITE;
/*!40000 ALTER TABLE `af_flow` DISABLE KEYS */;
INSERT INTO `af_flow` VALUES ('Flow-20161207-001','myTestFlow','Process-001','SATRT','','2016-12-07 15:14:57','2016-12-07 15:14:57',NULL),('Flow-20161207-002','myTestFlow','Process-002','FINISH','','2016-12-07 18:02:59','2016-12-07 18:15:03',NULL);
/*!40000 ALTER TABLE `af_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `af_process`
--

DROP TABLE IF EXISTS `af_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `af_process` (
  `ID` varchar(64) NOT NULL COMMENT '流程标识\n',
  `NAME` varchar(64) NOT NULL COMMENT '流程名字\n',
  `STATUS` varchar(20) NOT NULL COMMENT '流程状态，1为可用，0为不可用\n',
  `CONTENT` longtext COMMENT '流程具体内容\n',
  `CREATETIME` varchar(32) DEFAULT NULL COMMENT '创建时间\n',
  `UPDATETIME` varchar(32) DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流程定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `af_process`
--

LOCK TABLES `af_process` WRITE;
/*!40000 ALTER TABLE `af_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `af_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `af_schedule`
--

DROP TABLE IF EXISTS `af_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `af_schedule` (
  `ID` varchar(64) NOT NULL COMMENT '流程调度标识',
  `PROCESSID` varchar(64) DEFAULT NULL COMMENT '流程定义标识',
  `FLOWNAME` varchar(64) DEFAULT NULL COMMENT '流程实例名字',
  `CRON` varchar(64) DEFAULT NULL COMMENT '定时表达式',
  `VARIABLES` varchar(4000) DEFAULT NULL COMMENT '变量',
  `STATUS` varchar(30) DEFAULT NULL COMMENT '状态；1：可用；0：不可用；',
  `CREATETIME` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(32) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流程调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `af_schedule`
--

LOCK TABLES `af_schedule` WRITE;
/*!40000 ALTER TABLE `af_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `af_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `af_task`
--

DROP TABLE IF EXISTS `af_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `af_task` (
  `ID` varchar(64) NOT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `FLOWID` varchar(64) DEFAULT NULL,
  `PROCESSID` varchar(64) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `VARIABLES` varchar(4000) DEFAULT NULL,
  `NODE` varchar(64) DEFAULT NULL COMMENT '执行该任务的物理节点',
  `currentTimes` int(11) DEFAULT NULL,
  `maxtimes` int(11) DEFAULT NULL,
  `MESSAGE` varchar(200) DEFAULT NULL COMMENT '任务运行信息',
  `CREATETIME` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` varchar(32) DEFAULT NULL COMMENT '更新时间',
  `FINISHTIME` varchar(32) DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流程实例节点任务表，描述流程实例中每个节点的具体情况';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `af_task`
--

LOCK TABLES `af_task` WRITE;
/*!40000 ALTER TABLE `af_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `af_task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-08 14:12:56
