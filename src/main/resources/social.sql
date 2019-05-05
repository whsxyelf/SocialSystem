/*
SQLyog Ultimate v8.32 
MySQL - 5.7.17-log : Database - social
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`social` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `social`;

/*Table structure for table `collect` */

DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_no` varchar(11) NOT NULL COMMENT '用户编号',
  `collection_type` int(2) DEFAULT NULL COMMENT '收藏类型 新闻/动态',
  `collected_id` int(11) DEFAULT NULL COMMENT '动态的唯一标识',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `collect` */

insert  into `collect`(`collection_id`,`user_no`,`collection_type`,`collected_id`) values (1,'U00001',1,2),(2,'U00001',1,5),(3,'U00002',1,7);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_type` int(2) DEFAULT NULL COMMENT '评论类型 动态/新闻',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `commented_id` int(11) DEFAULT NULL COMMENT '被评论id',
  `comment_content` varchar(100) DEFAULT NULL COMMENT '评论内容',
  `create_time` date NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`comment_id`,`comment_type`,`user_no`,`commented_id`,`comment_content`,`create_time`) values (6,1,'U00001',7,'我也想你了！','2019-04-04'),(7,1,'U00002',7,'fuck!','2019-04-04');

/*Table structure for table `concern` */

DROP TABLE IF EXISTS `concern`;

CREATE TABLE `concern` (
  `concern_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户关注对象的唯一标识',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `concerned_id` varchar(11) DEFAULT NULL COMMENT '被关注用户编号',
  `create_time` date DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`concern_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `concern` */

insert  into `concern`(`concern_id`,`user_no`,`concerned_id`,`create_time`) values (1,'U00001','U00002',NULL),(2,'U00001','U00003',NULL),(3,'U00002','U00001',NULL),(4,'U00002','U00003',NULL),(7,'U00003','U00001','2019-04-03'),(9,'U00003','U00002','2019-04-03');

/*Table structure for table `essay` */

DROP TABLE IF EXISTS `essay`;

CREATE TABLE `essay` (
  `essay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户发表动态的唯一标识',
  `user_no` varchar(11) NOT NULL COMMENT '用户编号',
  `essay_content` text COMMENT '用户文字动态',
  `essay_photo` varchar(50) DEFAULT NULL COMMENT '用户图片动态',
  `essay_theme_no` varchar(50) DEFAULT NULL COMMENT '用户动态主题编号',
  `essay_collection` int(11) DEFAULT '0' COMMENT '用户动态被收藏次数',
  `essay_comment` int(11) DEFAULT '0' COMMENT '用户动态被评论次数',
  `create_time` date NOT NULL COMMENT '动态发布时间',
  PRIMARY KEY (`essay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `essay` */

insert  into `essay`(`essay_id`,`user_no`,`essay_content`,`essay_photo`,`essay_theme_no`,`essay_collection`,`essay_comment`,`create_time`) values (2,'U00002','阿飞三个额shah但是 上轨道恢复经济',NULL,'3',0,0,'2019-04-01'),(4,'U00003','过来看了那个路口年我国文物；个',NULL,NULL,0,0,'2019-04-04'),(5,'U00002','和昏天黑地股东分红合法合规俄国',NULL,NULL,0,0,'2019-04-04'),(7,'U00001','今天心情真糟糕！','',NULL,0,0,'2019-04-03');

/*Table structure for table `essay_theme` */

DROP TABLE IF EXISTS `essay_theme`;

CREATE TABLE `essay_theme` (
  `essay_theme_no` int(15) NOT NULL AUTO_INCREMENT COMMENT '动态主题编号',
  `essay_theme_content` varchar(50) DEFAULT NULL COMMENT '动态主题内容',
  PRIMARY KEY (`essay_theme_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `essay_theme` */

insert  into `essay_theme`(`essay_theme_no`,`essay_theme_content`) values (1,'2019年习主席出访美国'),(2,'三国杀归属感华盛顿号'),(3,'轨道恢复经济');

/*Table structure for table `false` */

DROP TABLE IF EXISTS `false`;

CREATE TABLE `false` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `false` */

/*Table structure for table `history` */

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
  `user_no` varchar(11) NOT NULL COMMENT '唯一标识用户',
  `news_id` int(11) DEFAULT NULL COMMENT '唯一标识新闻',
  `score` float DEFAULT NULL COMMENT '用户对该条新闻的评分',
  `create_time` date DEFAULT NULL COMMENT '访问时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `history` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户私信id',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `concerned_no` varchar(11) DEFAULT NULL COMMENT '被关注用户编号',
  `content` text COMMENT '私信内容',
  `create_time` date DEFAULT NULL COMMENT '私信时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `news_from` varchar(10) DEFAULT NULL COMMENT '标识新闻来源',
  `news_title` varchar(100) DEFAULT NULL COMMENT '新闻标题',
  `news_url` varchar(100) DEFAULT NULL COMMENT '新闻url',
  `length` int(20) DEFAULT NULL COMMENT '统计新闻的数字',
  `create_time` date DEFAULT NULL COMMENT '新闻发布的时间',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `news` */

insert  into `news`(`news_id`,`news_from`,`news_title`,`news_url`,`length`,`create_time`) values (1,'新浪新闻','娱乐','/15552352',425,NULL),(2,'新浪新闻','体育','/14235636',300,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

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

/*Data for the table `user` */

insert  into `user`(`user_no`,`user_nick`,`user_photo`,`user_email`,`password`,`sex`,`phone`,`signature`,`permission`,`user_state`,`create_time`) values ('U00001','小明','123124124','sada','123456',1,'1231313','12sss',1,1,'2019-05-04'),('U00002','张小三','/1111003','1176851359@qq.com','1243677',0,'','一波退婚流！',1,2,NULL),('U00003','嘘嘘',NULL,'1352823595@qq.com','hujin12345',0,NULL,NULL,1,1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
