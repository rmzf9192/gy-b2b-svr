-- MySQL dump 10.13  Distrib 5.7.13, for osx10.11 (x86_64)
--
-- Host: elrepdb1    Database: rep
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `S_COUNTERS`
--

DROP TABLE IF EXISTS `S_COUNTERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_COUNTERS` (
  `COUNTER_CODE` varchar(20) NOT NULL COMMENT '发号器分类码',
  `COUNTER_DESC` varchar(200) NOT NULL COMMENT '发号器分类描述',
  `COUNTER_KEY` varchar(20) NOT NULL COMMENT '发号器键值',
  `COUNTER_MAX` int unsigned NOT NULL COMMENT '计数最大值, 比如9999',
  `COUNTER_VAL` int unsigned NOT NULL COMMENT '计数当前值: 1开始',
  PRIMARY KEY (`COUNTER_CODE`,`COUNTER_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发号器';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_COUNTERS`
--

LOCK TABLES `S_COUNTERS` WRITE;
/*!40000 ALTER TABLE `S_COUNTERS` DISABLE KEYS */;
INSERT INTO `S_COUNTERS` VALUES
 ('LOCK','发号同步锁','SYNC',0,0);
/*!40000 ALTER TABLE `S_COUNTERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_DATA_AUDITS`
--

DROP TABLE IF EXISTS `S_DATA_AUDITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_DATA_AUDITS` (
  `TABLE_NAME` varchar(20) NOT NULL COMMENT '表名',
  `RECORD_ID` int unsigned NOT NULL COMMENT '更新记录的ID',
  `ACTION_CTG` char(1) NOT NULL COMMENT '操作分类(C:新增|U:更新|D:删除)',
  `ACTION_UID` int unsigned NOT NULL COMMENT '更新用户的ID',
  `ACTION_DTM` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `ACTION_PID` varchar(20) NOT NULL COMMENT '更新程序ID',
  `ACTION_NID` varchar(20) NOT NULL COMMENT '更新节点ID',
  PRIMARY KEY (`TABLE_NAME`,`RECORD_ID`,`ACTION_CTG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据更新审计'
/*!50100 PARTITION BY KEY ()
PARTITIONS 2 */;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `S_FUNCS`
--

DROP TABLE IF EXISTS `S_FUNCS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_FUNCS` (
  `_ENABLED` enum('Y','N') NOT NULL DEFAULT 'Y',
  `MENU_ID` varchar(30) NOT NULL COMMENT 'S_MENUS.MENU_ID',
  `FUNC_ID` varchar(20) NOT NULL COMMENT '功能ID',
  `FUNC_NAME` varchar(20) NOT NULL COMMENT '功能名',
  `FUNC_DESC` varchar(200) COMMENT '详细描述',
  `FUNC_API` varchar(100) NOT NULL COMMENT 'API访问路径或页面链接',
  `FUNC_TYPE` char(1) NOT NULL COMMENT 'A:API|L:链接',
  PRIMARY KEY (`FUNC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `S_MENUS`
--

DROP TABLE IF EXISTS `S_MENUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_MENUS` (
  `_ENABLED` enum('Y','N') NOT NULL DEFAULT 'Y',
  `MENU_ID` varchar(30) NOT NULL COMMENT '菜单ID',
  `MENU_TYPE` char(1) NOT NULL DEFAULT 'M' COMMENT 'M:可见菜单|C:不可见(仅用于API访问权限配置)',
  `MENU_LEVEL` tinyint unsigned NOT NULL COMMENT '菜单层级(一级菜单为1)',
  `MENU_ORDER` int unsigned NOT NULL COMMENT '显示顺序(需要注意菜单层级关系)',
  `MENU_LABEL` varchar(50) NOT NULL COMMENT '菜单文字',
  `MENU_URI` varchar(100) COMMENT '画面前端路由',
  `MENU_API` varchar(100) COMMENT 'API访问路径(支持Ant路径表达式)',
  `MENU_ICO` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单图标(fontawesome)',
  PRIMARY KEY (`MENU_ID`),
  UNIQUE KEY `S_MENUS_UI1` (`MENU_URI`),
  UNIQUE KEY `S_MENUS_UI2` (`MENU_API`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_MENUS`
--

LOCK TABLES `S_MENUS` WRITE;
/*!40000 ALTER TABLE `S_MENUS` DISABLE KEYS */;
INSERT INTO `S_MENUS` (MENU_ID, MENU_LEVEL, MENU_ORDER, MENU_LABEL, MENU_URI, MENU_API, MENU_ICO) VALUES
 ('E00',1,0,'首页','#app/home','/api/app/pub/**','cogs')
,('E90',1,9000,'系统管理',NULL,'/api/sec/**','cogs')
,('E9001',2,9010,'角色管理','#sys/auth/roles',NULL,'cog')
,('E9002',2,9020,'用户管理','#sys/auth/users',NULL,'cog')
,('E99',1,9900,'开发测试','#dev/demo','/api/dev/**','code');
/*!40000 ALTER TABLE `S_MENUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_ROLES`
--

DROP TABLE IF EXISTS `S_ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_ROLES` (
  `ROLE_ID` varchar(10) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(20) NOT NULL COMMENT '角色名',
  `ROLE_TYPE` char(1) NOT NULL DEFAULT 'B' COMMENT '角色类型(B:基础角色|C:自定义角色)',
  `ROLE_DESC` varchar(200) COMMENT '角色描述',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_ROLES`
--

LOCK TABLES `S_ROLES` WRITE;
/*!40000 ALTER TABLE `S_ROLES` DISABLE KEYS */;
INSERT INTO `S_ROLES` (ROLE_ID, ROLE_NAME) VALUES
 ('SYS','系统管理')
,('DEV','开发人员');
/*!40000 ALTER TABLE `S_ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_ROLE_FUNCS`
--

DROP TABLE IF EXISTS `S_ROLE_FUNCS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_ROLE_FUNCS` (
  `ROLE_ID` varchar(10) NOT NULL COMMENT 'S_ROLES.ROLE_ID',
  `FUNC_ID` varchar(20) NOT NULL COMMENT 'S_FUNCS.FUNC_ID',
  PRIMARY KEY (`ROLE_ID`,`FUNC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能权限配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_ROLE_FUNCS`
--

LOCK TABLES `S_ROLE_FUNCS` WRITE;
/*!40000 ALTER TABLE `S_ROLE_FUNCS` DISABLE KEYS */;
/*!40000 ALTER TABLE `S_ROLE_FUNCS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_ROLE_MENUS`
--

DROP TABLE IF EXISTS `S_ROLE_MENUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_ROLE_MENUS` (
  `ROLE_ID` varchar(10) NOT NULL COMMENT 'S_ROLES.ROLE_ID',
  `MENU_ID` varchar(30) NOT NULL COMMENT 'S_MENUS.MENU_ID',
  PRIMARY KEY (`ROLE_ID`,`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_ROLE_MENUS`
--

LOCK TABLES `S_ROLE_MENUS` WRITE;
/*!40000 ALTER TABLE `S_ROLE_MENUS` DISABLE KEYS */;
INSERT INTO `S_ROLE_MENUS` VALUES
 ('SYS','E90')
,('SYS','E9001')
,('SYS','E9002')
,('SYS','E9003')
,('SYS','E9004')
,('SYS','E99');
/*!40000 ALTER TABLE `S_ROLE_MENUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_DP_DEFS`
--

DROP TABLE IF EXISTS `S_DP_DEFS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_DP_DEFS` (
  `_ENABLED` enum('Y','N') NOT NULL DEFAULT 'Y',
  `DP_ID` varchar(20) NOT NULL COMMENT '数据权限ID',
  `DP_DESC` varchar(100) COMMENT '数据权限说明',
  `DP_OPEN` enum('Y','N') NOT NULL COMMENT '默认数据权限是开放还是关闭的(注：开发环境可以为Y，其他环境应设置为N)',
  PRIMARY KEY (`DP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限定义';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `S_DP_CFGS`
--

DROP TABLE IF EXISTS `S_DP_CFGS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_DP_CFGS` (
  `ROLE_ID` varchar(10) NOT NULL COMMENT '角色ID',
  `DP_ID` varchar(20) COMMENT '数据权限',
  `DP_WAY` varchar(10) NOT NULL COMMENT '数据过滤方法(参考: DemoDpWay)',
  PRIMARY KEY (`ROLE_ID`, `DP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `S_USERS`
--

-- ID <= 1000 为保留ID

DROP TABLE IF EXISTS `S_USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_USERS` (
  `_ENABLED` enum('Y','N') NOT NULL DEFAULT 'Y',
  `_VERSION` int unsigned NOT NULL DEFAULT 1,
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(40) COMMENT '邮箱(可用于登录)',
  `PHONE` varchar(20) COMMENT '电话(可用于登录)',
  `PASSWORD` char(64) NOT NULL COMMENT '登录密码(SHA256+SALT)',
  `SALT` char(16) NOT NULL COMMENT '密码的SALT',
  `USER_ROLE` varchar(10) NOT NULL COMMENT 'S_ROLES.ROLE_ID',
  `USER_NAME` varchar(40) NOT NULL COMMENT '用户名(用于画面显示)',
  `EMP_ID` int unsigned COMMENT 'T_EMP.ID(业务层面的用户ID)',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `S_USERS_UI1` (`EMAIL`),
  UNIQUE KEY `S_USERS_UI2` (`PHONE`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_USERS`
--

-- SALT generated by SimplePasswordSaltGeneratorTest.generateRandomSalt
-- PASSWORD generated by SimplePasswordSaltGeneratorTest.hashPassword
-- hashPassword('password', 'Ek9IaAamQMoi0eSp') -> '9be73aec844e3371b1cc7a0aefb8806505d586586930cfbb8a524540e3fe0392'

LOCK TABLES `S_USERS` WRITE;
/*!40000 ALTER TABLE `S_USERS` DISABLE KEYS */;
INSERT INTO `S_USERS` (ID,EMAIL,PHONE,PASSWORD,SALT,USER_ROLE,USER_NAME) VALUES
 (1,'sys@egluosi.com','18901010101','9be73aec844e3371b1cc7a0aefb8806505d586586930cfbb8a524540e3fe0392','Ek9IaAamQMoi0eSp','SYS','系统管理员')
,(2,'dev@egluosi.com','18902020202','9be73aec844e3371b1cc7a0aefb8806505d586586930cfbb8a524540e3fe0392','Ek9IaAamQMoi0eSp','DEV','系统开发者');
/*!40000 ALTER TABLE `S_USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_USER_AUDITS`
--

DROP TABLE IF EXISTS `S_USER_AUDITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_USER_AUDITS` (
  `ID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int unsigned NOT NULL COMMENT 'S_USERS.ID',
  `LOGIN_NO` varchar(40) NOT NULL COMMENT '登录号(手机或邮箱)',
  `LOGIN_TIME` datetime NOT NULL COMMENT '登录时间',
  `LOGOUT_TIME` datetime COMMENT '登出时间',
  `CLIENT_IP` varchar(24) NOT NULL COMMENT '访问的客户端IP地址',
  `CLIENT_UA` varchar(512) NOT NULL COMMENT '访问的浏览器环境信息',
  `ROUTE_TRACE` text COMMENT '访问路径跟踪',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户访问审计';
/*!40101 SET character_set_client = @saved_cs_client */;


--------------
--   DEMO   --
--------------

DROP TABLE IF EXISTS `S_DEMO_MSGS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `S_DEMO_MSGS` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `EMPL_ID` int unsigned NOT NULL,
  `DEPT_ID` int unsigned NOT NULL,
  `SUBJECT` varchar(100) NOT NULL,
  `CONTENT` varchar(1000) NOT NULL,
  `POST_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `COST` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限DEMO用';
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `S_DEMO_MSGS` WRITE;
/*!40000 ALTER TABLE `S_DEMO_MSGS` DISABLE KEYS */;
INSERT INTO `S_DEMO_MSGS` (EMPL_ID,DEPT_ID,SUBJECT,CONTENT) VALUES
 (1, 101, 'hello 1', 'something 1 ...')
,(11, 111, 'hello 11', 'somthing 11 ...')
,(12, 112, 'hello 12', 'something 12 ...')
;
/*!40000 ALTER TABLE `S_DEMO_MSGS` ENABLE KEYS */;
UNLOCK TABLES;
