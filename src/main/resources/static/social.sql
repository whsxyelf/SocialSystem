-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: social
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `collection` (
  `collection_id` int(11) NOT NULL COMMENT '收藏id',
  `user_no` varchar(11) NOT NULL COMMENT '用户编号',
  `collection_type` int(2) DEFAULT NULL COMMENT '收藏类型 新闻/动态',
  `collected_id` int(11) DEFAULT NULL COMMENT '动态的唯一标识',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL COMMENT '评论id',
  `comment_type` int(2) DEFAULT NULL COMMENT '评论类型',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `commented_id` int(11) DEFAULT NULL COMMENT '被评论id',
  `comment_content` varchar(100) DEFAULT NULL COMMENT '评论内容',
  `create_time` date NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concern`
--

DROP TABLE IF EXISTS `concern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `concern` (
  `concern_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户关注对象的唯一标识',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `concerned_id` int(11) DEFAULT NULL COMMENT '被关注用户编号',
  `create_time` date DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`concern_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concern`
--

LOCK TABLES `concern` WRITE;
/*!40000 ALTER TABLE `concern` DISABLE KEYS */;
/*!40000 ALTER TABLE `concern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `essay`
--

DROP TABLE IF EXISTS `essay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `essay` (
  `essay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户发表动态的唯一标识',
  `user_no` varchar(11) NOT NULL COMMENT '用户编号',
  `essay_content` text COMMENT '用户文字动态',
  `essay_photo` varchar(50) DEFAULT NULL COMMENT '用户图片动态',
  `essay_theme_no` varchar(50) DEFAULT NULL COMMENT '用户动态主题编号',
  `essay_collection` int(11) DEFAULT NULL COMMENT '用户动态被收藏次数',
  `essay_comment` int(11) DEFAULT NULL COMMENT '用户动态被评论次数',
  `create_time` date NOT NULL COMMENT '动态发布时间',
  PRIMARY KEY (`essay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `essay`
--

LOCK TABLES `essay` WRITE;
/*!40000 ALTER TABLE `essay` DISABLE KEYS */;
INSERT INTO `essay` VALUES (1,'U00001','2019年习主席出访美国~2019年习主席出访美国~2019年习主席出访美国~2019年习主席出访美国~\r\n','','1',0,1,'2019-04-01'),(2,'U00002','阿飞三个额shah但是 上轨道恢复经济',NULL,'3',0,0,'2019-04-01'),(3,'U00001','三国杀归属感华盛顿号阿尔电话或者电话和',NULL,'2',1,0,'2019-04-01');
/*!40000 ALTER TABLE `essay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `essay_theme`
--

DROP TABLE IF EXISTS `essay_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `essay_theme` (
  `essay_theme_no` int(15) NOT NULL AUTO_INCREMENT COMMENT '动态主题编号',
  `essay_theme_content` varchar(50) DEFAULT NULL COMMENT '动态主题内容',
  PRIMARY KEY (`essay_theme_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `essay_theme`
--

LOCK TABLES `essay_theme` WRITE;
/*!40000 ALTER TABLE `essay_theme` DISABLE KEYS */;
INSERT INTO `essay_theme` VALUES (1,'2019年习主席出访美国'),(2,'三国杀归属感华盛顿号'),(3,'轨道恢复经济');
/*!40000 ALTER TABLE `essay_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `false`
--

DROP TABLE IF EXISTS `false`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `false` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `false`
--

LOCK TABLES `false` WRITE;
/*!40000 ALTER TABLE `false` DISABLE KEYS */;
/*!40000 ALTER TABLE `false` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `history` (
  `user_no` varchar(11) NOT NULL COMMENT '唯一标识用户',
  `news_id` int(11) DEFAULT NULL COMMENT '唯一标识新闻',
  `score` float DEFAULT NULL COMMENT '用户对该条新闻的评分',
  `create_time` date DEFAULT NULL COMMENT '访问时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户私信id',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `concerned_no` varchar(11) DEFAULT NULL COMMENT '被关注用户编号',
  `content` text COMMENT '私信内容',
  `create_time` date DEFAULT NULL COMMENT '私信时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `news` (
  `news_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `news_from` varchar(10) DEFAULT NULL COMMENT '标识新闻来源',
  `news_title` varchar(100) DEFAULT NULL COMMENT '新闻标题',
  `news_url` varchar(100) DEFAULT NULL COMMENT '新闻url',
  `length` int(20) DEFAULT NULL COMMENT '统计新闻的数字',
  `create_time` date DEFAULT NULL COMMENT '新闻发布的时间',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'新浪新闻','娱乐','/15552352',425,NULL),(2,'新浪新闻','体育','/14235636',300,NULL);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_no` varchar(11) NOT NULL COMMENT '用户编号',
  `user_nick` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `user_photo` varchar(50) DEFAULT NULL COMMENT '用户头像',
  `user_email` varchar(30) NOT NULL COMMENT '用户登录邮箱',
  `password` varchar(16) NOT NULL COMMENT '用户密码',
  `sex` int(2) DEFAULT '0' COMMENT '用户性别（男/女/保密）',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `signature` varchar(50) DEFAULT NULL COMMENT '用户个性签名',
  `permission` int(2) DEFAULT '1' COMMENT '用户类型：管理员/普通用户',
  `user_state` int(2) DEFAULT '1' COMMENT '用户当前状态',
  `create_time` date DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('U00001','小明','123124124','sada','123456',1,'1231313','12sss',1,1,'2019-05-04'),('U00005',NULL,NULL,'1352823595@qq.com','337zuiniubi',NULL,NULL,NULL,1,1,NULL);
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

-- Dump completed on 2019-04-02 21:33:36
