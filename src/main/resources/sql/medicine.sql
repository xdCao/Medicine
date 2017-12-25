/*
Navicat MySQL Data Transfer

Source Server         : xdCao
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : medicine

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-12-25 19:14:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'root', '123');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
INSERT INTO `doctor` VALUES ('9', 'Alice', '1', '西安电子科技大学', '13476859976', '2017-12-05', '3', '5', '3', '1', '0', '111', '123', '0');
INSERT INTO `doctor` VALUES ('10', 'Bob', '2', '太白南路', '18747657765', '2017-12-18', '3', '3', '2', '3', '0', '111', '233', '0');
INSERT INTO `doctor` VALUES ('11', 'Cindy', '3', '科技路', '13390874532', '2017-11-13', '3', '2', '1', '3', '0', '111', '333', '0');
INSERT INTO `doctor` VALUES ('12', 'Danny', '4', '吉祥路', '14378650098', '2017-01-17', '4', '5', '4', '4', '0', '111', '433', '0');

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
  `temperature` double DEFAULT NULL,
  `blood_pressure` double DEFAULT NULL,
  `heart_beat` int(11) DEFAULT NULL,
  `is_in_emergency` tinyint(1) DEFAULT NULL,
  `emergTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_trust_attr` (`trustAttr_id`),
  KEY `fk_doctor` (`doctor_id`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trust_attr` FOREIGN KEY (`trustAttr_id`) REFERENCES `trust_attr` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('10', '测试MT的病人', '9', '17765478996', '2017-12-20', '13', '0', 'nothing', '111', 'hhh', '0', '0', '0', '0', null);
INSERT INTO `patient` VALUES ('11', '张三', '2', '13338926199', '2017-12-23', '14', '0', '没有特殊病例', '111', '111', '37.5', '100', '80', '0', null);
INSERT INTO `patient` VALUES ('13', '赵四', '2', '17765478998', '2017-12-23', '15', '0', 'nothing', '111', '112', '0', '0', '0', '0', null);
INSERT INTO `patient` VALUES ('14', '王五', '5', '17765478998', '2017-12-25', '16', '0', 'nothing', '111', '113', '0', '0', '0', '0', null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) unsigned NOT NULL,
  `doctor_name` varchar(30) NOT NULL,
  `evaluate_value` tinyint(4) NOT NULL,
  `operation_content` varchar(255) DEFAULT NULL,
  `patient_id` int(11) unsigned NOT NULL,
  `evaluate_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('18', '1', '张医生', '90', '诊断准确', '8', '2017-12-06');
INSERT INTO `sys_log` VALUES ('19', '2', '李医生', '70', '病历书写规范', '9', '2017-12-19');
INSERT INTO `sys_log` VALUES ('20', '3', '王医生', '60', 'bbbbb', '8', '2017-11-14');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trust_attr
-- ----------------------------
INSERT INTO `trust_attr` VALUES ('11', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('12', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('13', '3', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('14', '4', '3', '2', '1');
INSERT INTO `trust_attr` VALUES ('15', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('16', '1', '2', '3', '3');

-- ----------------------------
-- Table structure for user_log
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) unsigned NOT NULL,
  `doctor_name` varchar(30) NOT NULL,
  `evaluate_value` tinyint(4) NOT NULL,
  `evaluate_content` varchar(255) DEFAULT NULL,
  `evaluate_date` date NOT NULL,
  `user_type` int(1) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('14', '1', '张医生', '90', '态度好', '2017-12-06', '1', '张三');
INSERT INTO `user_log` VALUES ('15', '2', '李医生', '80', '医德高尚', '2017-12-19', '1', '赵四');
INSERT INTO `user_log` VALUES ('16', '3', '王医生', '50', 'aaaa', '2017-11-14', '1', '王五');
