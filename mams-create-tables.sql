-- MySQL dump 10.13  Distrib 5.7.17, for osx10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: california
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `mams_auth`
--

DROP TABLE IF EXISTS `mams_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mams_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kid` varchar(16) NOT NULL,
  `type` varchar(16) NOT NULL COMMENT '权限类型，如管理员的权限’admin’，代理的权限’agent’，用户的权限’user’等。',
  `name` varchar(32) NOT NULL,
  `uri` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `kid_UNIQUE` (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='定义权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mams_auth`
--

LOCK TABLES `mams_auth` WRITE;
/*!40000 ALTER TABLE `mams_auth` DISABLE KEYS */;
INSERT INTO `mams_auth` VALUES (6,'07D0FAF9','admin','添加管理员','/mams/auth/home','2018-10-24 09:39:04','2018-10-24 09:39:04','添加管理员权限');
/*!40000 ALTER TABLE `mams_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mams_group`
--

DROP TABLE IF EXISTS `mams_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mams_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kid` varchar(16) NOT NULL,
  `type` varchar(16) NOT NULL COMMENT '权限类型，如管理员的权限’admin’，代理的权限’agent’，用户的权限’user’等。',
  `name` varchar(32) NOT NULL,
  `members` json NOT NULL COMMENT '组内的成员',
  `menus` json NOT NULL COMMENT '组内的成员',
  `auths` json NOT NULL COMMENT '组内的成员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kid_UNIQUE` (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='定义组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mams_group`
--

LOCK TABLES `mams_group` WRITE;
/*!40000 ALTER TABLE `mams_group` DISABLE KEYS */;
INSERT INTO `mams_group` VALUES (3,'1F30CCFA','admin','超级管理员','[\"Admin3\", \"Admin2\", \"Admin1\"]','[\"1DD9AEAF\"]','[\"07D0FAF9\"]','2018-10-24 16:33:04','2018-10-24 17:37:32','拥有系统所有管理权限');
/*!40000 ALTER TABLE `mams_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mams_menu`
--

DROP TABLE IF EXISTS `mams_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mams_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `kid` varchar(16) NOT NULL,
  `type` varchar(16) NOT NULL COMMENT '权限类型，如管理员的权限’admin’，代理的权限’agent’，用户的权限’user’等。',
  `p1_kid` varchar(16) DEFAULT NULL COMMENT '上级父菜单kid',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `uri` varchar(100) NOT NULL COMMENT '菜单对应的uri',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT '权重，权重越大，越靠前，权重相同的，按照uri字符串排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kid_UNIQUE` (`kid`),
  UNIQUE KEY `uri_UNIQUE` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='定义菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mams_menu`
--

LOCK TABLES `mams_menu` WRITE;
/*!40000 ALTER TABLE `mams_menu` DISABLE KEYS */;
INSERT INTO `mams_menu` VALUES (9,'1DD9AEAF','admin',NULL,'系统管理','/mams/menu/system-managers-1',0,'2018-10-24 17:36:53','2018-10-24 17:36:53','系统管理页面');
/*!40000 ALTER TABLE `mams_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-24 17:54:32
