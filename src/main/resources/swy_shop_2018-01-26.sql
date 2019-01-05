# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.1.73)
# Database: swy_shop
# Generation Time: 2018-01-26 07:46:31 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) DEFAULT NULL,
  `user_name` char(32) DEFAULT NULL,
  `nick_name` char(32) DEFAULT NULL,
  `password` char(64) DEFAULT NULL COMMENT 'md5之后的密码',
  `level` tinyint(4) DEFAULT NULL COMMENT '会员等级',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table model_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `model_type`;

CREATE TABLE `model_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type_id` int(11) DEFAULT NULL COMMENT '分类id',
  `type_name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `type_desc` varchar(128) DEFAULT NULL COMMENT '分类描述',
  `parent_type` int(11) DEFAULT NULL COMMENT '父级分类id',
  `level` int(4) DEFAULT NULL COMMENT '所在层级(方便查找)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='家具分类表';

LOCK TABLES `model_type` WRITE;
/*!40000 ALTER TABLE `model_type` DISABLE KEYS */;

INSERT INTO `model_type` (`id`, `type_id`, `type_name`, `type_desc`, `parent_type`, `level`, `create_time`, `update_time`)
VALUES
	(1,1,'椅子','家具组合',0,1,NULL,NULL),
	(2,2,'沙发','家具组合',0,1,NULL,NULL),
	(3,3,'床','各种样式',0,NULL,NULL,NULL),
	(4,4,'餐桌','样式大理石',0,1,NULL,NULL),
	(6,5,'1111','22222222',0,NULL,'2017-12-04 13:35:34','2017-12-04 13:35:34'),
	(7,6,'1111','22222222',0,NULL,'2017-12-04 13:35:35','2017-12-04 13:35:35'),
	(8,7,'1111','22222222',0,NULL,'2017-12-04 13:35:36','2017-12-04 13:35:36'),
	(9,8,'1111','22222222',0,NULL,'2017-12-04 13:35:37','2017-12-04 13:35:37'),
	(10,9,'1111','22222222',0,NULL,'2017-12-04 13:35:37','2017-12-04 13:35:37'),
	(11,10,'1111','22222222',0,NULL,'2017-12-04 13:36:15','2017-12-04 13:36:15'),
	(12,11,'2222','333333',0,NULL,'2017-12-04 13:38:14','2017-12-04 13:38:14'),
	(13,12,'33333','44444',0,NULL,'2017-12-11 19:51:59','2017-12-11 19:51:59');

/*!40000 ALTER TABLE `model_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seller_model
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seller_model`;

CREATE TABLE `seller_model` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `model_id` char(11) DEFAULT NULL COMMENT '模型id(规则:商家id_模型序号)',
  `model_name` varchar(128) DEFAULT NULL COMMENT '模型名称',
  `model_desc` varchar(1024) DEFAULT NULL COMMENT '模型描述',
  `model_size` int(11) DEFAULT NULL COMMENT '模型尺寸(单位bit)',
  `model_type` char(11) DEFAULT NULL COMMENT '模型文件类型(obj,dae,scn)',
  `seller_id` int(11) DEFAULT NULL COMMENT '所属商家id',
  `model_img_url` char(128) DEFAULT NULL COMMENT '模型简介图片url',
  `model_file_url` char(128) DEFAULT NULL COMMENT '模型压缩包下载url',
  `is_delete` int(4) DEFAULT '0' COMMENT '是否被删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '上传创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `type_id` int(11) DEFAULT NULL COMMENT '模型对应家具的类别id',
  `brand_id` int(11) DEFAULT NULL COMMENT '模型对应家具的品牌id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商家的模型表';

LOCK TABLES `seller_model` WRITE;
/*!40000 ALTER TABLE `seller_model` DISABLE KEYS */;

INSERT INTO `seller_model` (`id`, `model_id`, `model_name`, `model_desc`, `model_size`, `model_type`, `seller_id`, `model_img_url`, `model_file_url`, `is_delete`, `create_time`, `update_time`, `type_id`, `brand_id`)
VALUES
	(1,'1000_1','凳子模型1','材质：木质；抛光处理',4096,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',1,NULL,NULL,1,NULL),
	(2,'1000_2','凳子模型2','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,1,NULL),
	(4,'1000_3','椅子模型1','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,2,NULL),
	(5,'1000_4','椅子模型2','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,2,NULL),
	(6,'1000_5','家庭床模型1','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,3,NULL),
	(7,'1000_6','家庭床模型2','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',1,NULL,NULL,3,NULL),
	(8,'1000_7','家庭床模型3','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,3,NULL),
	(9,'1000_8','沙发1','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,4,NULL),
	(10,'1000_9','沙发2','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,4,NULL),
	(11,'1000_10','沙发3','材质：木质；抛光处理',2048,'obj',1000,'https://a.vpimg4.com/upload/flow/2016/12/22/77/14824030433732.jpg','https://www.baidu.com',NULL,NULL,NULL,4,NULL),
	(15,'1000_11','AAA',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_illumination.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,1,NULL),
	(16,'1000_12','AAAAA',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_illumination.jpg','http://127.0.0.1/2017/归档.zip',1,NULL,NULL,1,NULL),
	(17,'1000_13','AAAAA',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_illumination.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,3,NULL),
	(18,'1000_14','AAAAA',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_dDo_d_orange.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,4,NULL),
	(19,'1000_15','QQ520',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Interior_dDo_s.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,1,NULL),
	(20,'1000_16','QQ520',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Interior_dDo_s.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,2,NULL),
	(21,'1000_17','水电费水电费同',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_dDo_d_orange.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,3,NULL),
	(22,'1000_18','最新上传的',NULL,NULL,NULL,1000,'http://127.0.0.1/2017/Body_illumination.jpg','http://127.0.0.1/2017/归档.zip',NULL,NULL,NULL,NULL,NULL),
	(23,'1001_1','AAAAA',NULL,NULL,NULL,1001,'http://127.0.0.1/2017/Body_dDo_d_orange.jpg','http://127.0.0.1/2017/归档.zip',NULL,'2017-11-22 22:51:44','2017-11-22 22:51:44',NULL,NULL),
	(24,'1001_2','www',NULL,NULL,NULL,1001,'http://127.0.0.1/2017/Interior_dDo_s.jpg','http://127.0.0.1/2017/归档.zip',NULL,'2017-11-22 22:52:19','2017-11-22 22:52:19',NULL,NULL),
	(25,'1001_3','1111',NULL,NULL,NULL,1001,'http://127.0.0.1/2017/Body_dDo_g.jpg','http://127.0.0.1/2017/归档.zip',NULL,'2017-11-22 23:25:57','2017-11-22 23:25:57',NULL,NULL),
	(26,'1002_1','QQQQQ',NULL,NULL,NULL,1002,'http://127.0.0.1/2017/1002/Body_dDo_d_orange.jpg','http://127.0.0.1/2017/1002/归档.zip',NULL,'2017-11-26 21:49:55','2017-11-26 21:49:55',NULL,NULL),
	(28,'1003_2','AAA',NULL,NULL,NULL,1003,'http://127.0.0.1/2017/1003/AppStore4-es.jpg','http://127.0.0.1/2017/1003/申请微信联登.zip',NULL,'2017-11-29 18:29:08','2017-11-29 18:29:08',NULL,NULL),
	(29,'1003_2','666',NULL,NULL,NULL,1003,'http://127.0.0.1/2017/1003/guide1-1@2x.jpg','http://127.0.0.1/2017/1003/666.zip',NULL,'2017-11-30 19:01:44','2017-11-30 19:01:44',1,NULL),
	(30,'1003_3','666',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/广州地铁规划图.jpg','http://127.0.0.1/1003/666.zip',NULL,'2017-11-30 19:26:57','2017-11-30 19:26:57',1,2),
	(31,'1003_4','cartoon-obj',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/78D797D3563053ABCD3AADD12EE9847F.jpg','http://127.0.0.1/1003/cartoon.zip',NULL,'2017-12-01 20:40:39','2017-12-01 20:40:39',3,1),
	(32,'1003_5','TREE',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/1024x1024.jpg','http://127.0.0.1/1003/tree.zip',NULL,'2017-12-01 21:15:01','2017-12-01 21:15:01',4,1),
	(33,'1003_6','chair',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/Body_dDo_s.jpg','http://127.0.0.1/1003/chair.zip',NULL,'2017-12-01 21:38:58','2017-12-01 21:38:58',3,1),
	(34,'1003_7','双人椅子',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/沙发.jpg','http://127.0.0.1/1003/yizi.zip',NULL,'2017-12-03 11:35:44','2017-12-03 11:35:44',1,NULL),
	(35,'1003_8','测试椅子',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/hongxiangm.jpg','http://127.0.0.1/1003/yizi-1.zip',NULL,'2017-12-03 12:41:06','2017-12-03 12:41:06',1,NULL),
	(36,'1003_9','yizi-2',NULL,NULL,NULL,1003,'http://127.0.0.1/1003/hongxiangm.jpg','http://127.0.0.1/1003/yizi-2.zip',NULL,'2017-12-03 13:05:18','2017-12-03 13:05:18',1,NULL),
	(37,'1002_1','mmm',NULL,NULL,NULL,1002,'http://127.0.0.1/1002/hongxiangm.jpg','http://127.0.0.1/1002/yizi-2.zip',NULL,'2017-12-03 13:38:27','2017-12-03 13:38:27',1,NULL),
	(38,'1002_2','wwww',NULL,4621275,NULL,1002,'http://127.0.0.1/1002/沙发.jpg','http://127.0.0.1/1002/shafa.ZIP',0,'2017-12-05 13:09:07','2017-12-05 13:09:07',2,1),
	(39,'1000_17','666',NULL,2792035,NULL,1000,'','',0,'2017-12-11 13:18:50','2017-12-11 13:18:50',1,2),
	(40,'1000_16','111111111111111',NULL,44017668,NULL,1000,'','',0,'2017-12-11 13:23:08','2017-12-11 13:23:08',1,2),
	(41,'1005_1','cartoon-obj',NULL,2582091,NULL,1005,'','',0,'2017-12-11 13:25:33','2017-12-11 13:25:33',3,2),
	(42,'1005_2','666',NULL,2582091,NULL,1005,'','',0,'2017-12-11 19:54:33','2017-12-11 19:54:33',12,3),
	(43,'1005_3','111111111111111',NULL,2582091,NULL,1005,'','',1,'2017-12-11 19:59:30','2017-12-11 19:59:30',1,1),
	(44,'1005_4','chair',NULL,2792250,NULL,1005,'','',1,'2017-12-11 21:01:31','2017-12-11 21:01:31',1,1),
	(45,'1005_5','chair',NULL,2792250,NULL,1005,'http://192.168.1.105/1005/hongxiangmggg2.jpg','http://192.168.1.105/1005/chair.zip',1,'2017-12-11 21:04:07','2017-12-11 21:04:07',1,4),
	(46,'1005_3','AAAAA',NULL,2579252,NULL,1005,'http://192.168.1.102/file/1005/1515424866184-Interior_dDo_d_black.jpg','http://192.168.1.102/file/1005/1515424866184-归档.zip',0,'2018-01-08 23:20:41','2018-01-08 23:20:41',1,1);

/*!40000 ALTER TABLE `seller_model` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table share_example
# ------------------------------------------------------------

DROP TABLE IF EXISTS `share_example`;

CREATE TABLE `share_example` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '分享用户id',
  `example_url` varchar(64) DEFAULT NULL COMMENT '分享案例url',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `audit_status` varchar(16) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户分享录屏视频表';

LOCK TABLES `share_example` WRITE;
/*!40000 ALTER TABLE `share_example` DISABLE KEYS */;

INSERT INTO `share_example` (`id`, `user_id`, `example_url`, `create_time`, `update_time`, `audit_status`)
VALUES
	(1,111,'https://www.baidu.com','2017-11-22 22:52:19','2018-01-04 19:09:53','0'),
	(2,123,'http://v.youku.com/v_show/id_XMzI4ODc2OTc4OA==.html?spm=a2hww.20','2017-11-22 22:52:19','2018-01-21 22:35:45','1');

/*!40000 ALTER TABLE `share_example` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table shop_brand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop_brand`;

CREATE TABLE `shop_brand` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `brand_id` int(11) NOT NULL DEFAULT '0' COMMENT '品牌id',
  `brand_name` varchar(64) DEFAULT NULL COMMENT '品牌名称',
  `brand_desc` varchar(512) DEFAULT NULL COMMENT '品牌描述',
  `brand_logo` varchar(64) DEFAULT NULL COMMENT '品牌logo图标',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='家具的品牌表';

LOCK TABLES `shop_brand` WRITE;
/*!40000 ALTER TABLE `shop_brand` DISABLE KEYS */;

INSERT INTO `shop_brand` (`id`, `brand_id`, `brand_name`, `brand_desc`, `brand_logo`, `create_time`, `update_time`)
VALUES
	(1,1,'金海马家具','大型卖场','https://a.vpimg4.com/upload/merchandise/pdcvis/2016/10/27/61/33c',NULL,NULL),
	(2,2,'阳光生活','个性品牌','https://a.vpimg4.com/upload/merchandise/pdcvis/2016/10/27/61/33c',NULL,NULL),
	(3,3,'大众定制','定制品牌','https://a.vpimg4.com/upload/merchandise/pdcvis/2016/10/27/61/33c',NULL,NULL),
	(4,4,'11111','2222','https://a.vpimg4.com/upload/merchandise/pdcvis/2016/10/27/61/33c','2017-12-11 20:23:15','2017-12-11 20:23:15');

/*!40000 ALTER TABLE `shop_brand` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table shop_seller
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop_seller`;

CREATE TABLE `shop_seller` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `seller_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商家id',
  `seller_name` varchar(128) DEFAULT NULL COMMENT '商家名称',
  `seller_desc` varchar(1024) DEFAULT NULL COMMENT '商家描述',
  `member_level` int(4) NOT NULL DEFAULT '0' COMMENT '商家会员等级',
  `is_disable` int(4) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `seller_address` varchar(256) DEFAULT NULL COMMENT '商家地址',
  `seller_website` char(64) DEFAULT NULL COMMENT '商家自己的网站地址',
  `seller_mobile` char(11) DEFAULT NULL COMMENT '联系手机号码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='售卖商家表';

LOCK TABLES `shop_seller` WRITE;
/*!40000 ALTER TABLE `shop_seller` DISABLE KEYS */;

INSERT INTO `shop_seller` (`id`, `seller_id`, `seller_name`, `seller_desc`, `member_level`, `is_disable`, `seller_address`, `seller_website`, `seller_mobile`, `create_time`, `update_time`)
VALUES
	(1,1000,'广州宜家有限公司','分公司',1,0,'广东省广州市','https://www.vipme.com','18688205123','2017-11-11 16:00:00',NULL),
	(2,1001,'广州家具卖场','场地：西门口地铁站附近',0,0,'广东省广州市','https://www.vipme.com','18688205123','2017-11-12 16:00:00',NULL),
	(3,1002,'佛山家具生产厂','佛山顺德',0,0,'广东省佛山市','https://www.baidu.com','18688205123','2017-11-13 16:00:00',NULL),
	(4,1003,'11111','22222',1,0,'33333','https://www.baidu.com',NULL,'2017-11-26 21:57:45','2017-11-26 21:57:45'),
	(5,1004,'AAAAA','AAAA',1,0,'AAAA',NULL,NULL,'2017-12-11 13:12:35','2017-12-11 13:12:35'),
	(6,1005,'BBB','AAAA',1,0,'谷阳','https://','123424','2017-12-11 13:17:30','2017-12-11 13:17:30');

/*!40000 ALTER TABLE `shop_seller` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
