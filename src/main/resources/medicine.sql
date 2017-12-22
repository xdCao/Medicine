/*
Navicat MySQL Data Transfer

Source Server         : xdCao
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : medicine

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-21 22:56:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `position` tinyint(4) NOT NULL,
  `office_location` varchar(50) NOT NULL,
  `office_phone` char(11) NOT NULL,
  `registry_date` date NOT NULL,
  `department` tinyint(4) NOT NULL,
  `title` tinyint(4) NOT NULL,
  `workage` tinyint(4) NOT NULL,
  `degree` tinyint(4) NOT NULL,
  `is_free` tinyint(1) NOT NULL,
  `password` varchar(20) NOT NULL,
  `account` varchar(30) NOT NULL,
  `isIn` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '张医生', '1', '西安电子科技大学', '13476859976', '2017-12-05', '1', '1', '1', '1', '0', '111', '123', '0');
INSERT INTO `doctor` VALUES ('2', '李医生', '2', '太白南路', '18747657765', '2017-12-18', '2', '2', '2', '2', '0', '111', '233', '0');
INSERT INTO `doctor` VALUES ('3', '王医生', '3', '科技路', '13390874532', '2017-11-13', '3', '3', '3', '3', '0', '111', '333', '0');
INSERT INTO `doctor` VALUES ('4', '赵医生', '4', '吉祥路', '14378650098', '2017-01-17', '4', '4', '4', '4', '0', '111', '433', '0');
INSERT INTO `doctor` VALUES ('5', '曹医生', '4', '西电老科', '13336758765', '2017-12-19', '3', '4', '1', '3', '1', '111', '433', '0');
INSERT INTO `doctor` VALUES ('6', 'caohao', '1', 'xian', '13337865544', '2017-12-20', '1', '1', '1', '1', '1', '123', '444', '0');
INSERT INTO `doctor` VALUES ('7', '12', '4', '12', '12', '2017-12-21', '2', '0', '4', '2', '1', '12', '21', '0');
INSERT INTO `doctor` VALUES ('8', '12', '4', '12', '12', '2017-12-21', '2', '0', '4', '2', '1', '12', '244', '0');

-- ----------------------------
-- Table structure for others
-- ----------------------------
DROP TABLE IF EXISTS `others`;
CREATE TABLE `others` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `age` int(11) NOT NULL,
  `job` tinyint(4) NOT NULL,
  `company` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone` char(11) NOT NULL,
  `is_send_request` tinyint(1) NOT NULL,
  `password` varchar(20) NOT NULL,
  `account` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of others
-- ----------------------------

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `doctor_id` int(11) unsigned NOT NULL,
  `phone` char(11) NOT NULL,
  `registry_date` date NOT NULL,
  `trustAttr_id` int(11) unsigned NOT NULL,
  `sense_aware` tinyint(1) NOT NULL,
  `illness_condition` varchar(255) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `account` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trust_attr` (`trustAttr_id`),
  KEY `fk_doctor` (`doctor_id`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trust_attr` FOREIGN KEY (`trustAttr_id`) REFERENCES `trust_attr` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('8', 'lalal', '1', '17765478998', '2017-12-20', '11', '0', 'nothing', '111', '633');
INSERT INTO `patient` VALUES ('9', '哈哈', '1', '17765478996', '2017-12-20', '12', '0', 'nothing', '111', 'hhh');

-- ----------------------------
-- Table structure for trust_attr
-- ----------------------------
DROP TABLE IF EXISTS `trust_attr`;
CREATE TABLE `trust_attr` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department` tinyint(4) NOT NULL,
  `demand_title` tinyint(4) NOT NULL,
  `demand_workage` tinyint(4) NOT NULL,
  `demand_degree` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trust_attr
-- ----------------------------
INSERT INTO `trust_attr` VALUES ('11', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('12', '1', '2', '3', '3');



/*2017.12.22新增， by liubotao*/

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;                                /*用户日志表*/    /*在对应的service中一定要有一个List<UserLog> getUserLogByDoctorId方法！！！*/
CREATE TABLE `user_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,		/*日志id*/
  `doctor_id` int(11) unsigned NOT NULL,			/*被评价的医生id*/
  `doctor_name` varchar(30) NOT NULL,				/*被评价的医生姓名*/
  `evaluate_value` tinyint(4) NOT NULL,				/*评价分数，用户输入和在数据库中存储时用百分制，计算RCM时量化为0-1的值*/   /*在前端输入时需要控制取值范围为0-100！！！*/
  `evaluate_content` varchar(255) DEFAULT NULL,                 /*评价内容*/
  `evaluate_date` date NOT NULL,				/*评价时间*/
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('14','1', '张医生', '90', '态度好',  '2017-12-06');
INSERT INTO `user_log` VALUES ('15','2', '李医生', '80', '医德高尚', '2017-12-19');
INSERT INTO `user_log` VALUES ('16','3', '王医生', '50', 'aaaa',  '2017-11-14');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;                                /*系统日志表*/    /*在对应的service中一定要有一个List<SysLog> getSysLogByDoctorId方法！！！*/
CREATE TABLE `sys_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,		/*日志id*/
  `doctor_id` int(11) unsigned NOT NULL,			/*操作医生id*/
  `doctor_name` varchar(30) NOT NULL,				/*操作医生姓名*/
  `evaluate_value` tinyint(4) NOT NULL,				/*系统评价分数，系统生成分数和在数据库中存储时用百分制，计算REP时量化为0-1的值*/   
  `operation_content` varchar(255) DEFAULT NULL,                /*医生操作内容*/
  `patient_id` int(11) unsigned NOT NULL,			/*客体病人id*/
  `evaluate_date` date NOT NULL,				/*系统评价时间*/
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('18','1', '张医生', '90', '诊断准确', '8' , '2017-12-06');
INSERT INTO `sys_log` VALUES ('19','2', '李医生', '70', '病历书写规范', '9' , '2017-12-19');
INSERT INTO `sys_log` VALUES ('20','3', '王医生', '60', 'bbbbb', '8' , '2017-11-14');

