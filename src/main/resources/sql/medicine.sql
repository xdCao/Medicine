/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : medicine

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-12-26 17:01:10
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
INSERT INTO `doctor` VALUES ('3', '王医生', '3', '科技路', '13390874532', '2017-11-13', '3', '3', '3', '0', '1', '111', '333', '1');
INSERT INTO `doctor` VALUES ('4', '赵医生', '4', '吉祥路', '14378650098', '2017-01-17', '0', '4', '4', '0', '0', '111', '433', '0');
INSERT INTO `doctor` VALUES ('5', '曹医生', '4', '西电老科', '13336758765', '2017-12-19', '3', '4', '1', '1', '1', '111', '434', '1');
INSERT INTO `doctor` VALUES ('6', 'caohao', '1', 'xian', '13337865544', '2017-12-20', '1', '1', '1', '1', '1', '123', '444', '1');
INSERT INTO `doctor` VALUES ('7', 'Ella', '4', '12', '12', '2017-12-21', '2', '0', '0', '2', '1', '12', '21', '0');
INSERT INTO `doctor` VALUES ('8', 'Frank', '4', '12', '12', '2017-12-21', '2', '0', '4', '2', '1', '12', '244', '1');
INSERT INTO `doctor` VALUES ('9', 'Alice', '1', '西安电子科技大学', '13476859976', '2017-12-05', '3', '2', '3', '1', '0', '111', '245', '0');
INSERT INTO `doctor` VALUES ('10', 'Bob', '2', '太白南路', '18747657765', '2017-12-18', '3', '4', '4', '2', '1', '111', '246', '1');
INSERT INTO `doctor` VALUES ('11', 'Cindy', '3', '科技路', '13390874532', '2017-11-13', '3', '0', '0', '0', '0', '111', '247', '0');
INSERT INTO `doctor` VALUES ('12', 'Danny', '4', '吉祥路', '14378650098', '2017-01-17', '0', '3', '4', '1', '0', '111', '248', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('1', '病人1', '9', '17765478996', '2017-12-20', '13', '0', 'nothing', '111', 'hhh', '0', '0', '0', '0', '2017-12-26 16:14:10');
INSERT INTO `patient` VALUES ('2', '张三', '2', '13338926199', '2017-12-23', '14', '0', '没有特殊病例', '111', '111', '37.5', '100', '80', '0', '2017-12-26 16:14:10');
INSERT INTO `patient` VALUES ('3', '赵四', '2', '17765478998', '2017-12-23', '15', '0', 'nothing', '111', '112', '0', '0', '0', '0', '2017-12-26 16:14:10');
INSERT INTO `patient` VALUES ('4', '王五', '5', '17765478998', '2017-12-25', '16', '0', 'nothing', '111', '113', '0', '0', '0', '0', '2017-12-26 16:14:09');
INSERT INTO `patient` VALUES ('5', '要求很高的病人', '12', '123', '2017-12-07', '17', '0', '0', '111', '114', '1', '1', '1', '0', '2017-12-26 16:30:25');
INSERT INTO `patient` VALUES ('6', '要求很低的病人', '3', '2232', '2017-12-07', '18', '0', '0', '111', '115', '1', '1', '1', '0', '2017-12-26 16:30:25');

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
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('18', '1', '张医生', '90', '诊断准确', '8', '2017-12-06');
INSERT INTO `sys_log` VALUES ('19', '2', '李医生', '70', '病历书写规范', '9', '2017-12-19');
INSERT INTO `sys_log` VALUES ('20', '3', '王医生', '60', 'bbbbb', '8', '2017-11-14');
INSERT INTO `sys_log` VALUES ('21', '10', 'Bob', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('22', '10', 'Bob', '16', 'a', '8', '2017-12-07');
INSERT INTO `sys_log` VALUES ('23', '10', 'Bob', '63', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('24', '10', 'Bob', '87', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('25', '10', 'Bob', '52', 'a', '8', '2017-12-06');
INSERT INTO `sys_log` VALUES ('26', '10', 'Bob', '71', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('28', '1', '张医生', '32', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('29', '1', '张医生', '20', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('30', '1', '张医生', '45', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('31', '1', '张医生', '10', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('32', '1', '张医生', '57', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('33', '1', '张医生', '83', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('34', '1', '张医生', '6', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('35', '1', '张医生', '29', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('36', '1', '张医生', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('37', '1', '张医生', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('38', '1', '张医生', '15', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('39', '1', '张医生', '86', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('40', '1', '张医生', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('41', '1', '张医生', '77', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('42', '1', '张医生', '81', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('43', '1', '张医生', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('44', '1', '张医生', '82', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('45', '1', '张医生', '79', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('46', '1', '张医生', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('47', '1', '张医生', '60', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('48', '2', '李医生', '80', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('49', '2', '李医生', '69', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('50', '2', '李医生', '44', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('51', '2', '李医生', '69', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('52', '2', '李医生', '91', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('53', '2', '李医生', '84', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('54', '2', '李医生', '62', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('55', '2', '李医生', '2', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('56', '2', '李医生', '76', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('57', '2', '李医生', '44', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('58', '2', '李医生', '18', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('59', '2', '李医生', '23', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('60', '2', '李医生', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('61', '2', '李医生', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('62', '2', '李医生', '88', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('63', '2', '李医生', '90', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('64', '2', '李医生', '10', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('65', '2', '李医生', '83', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('66', '2', '李医生', '66', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('67', '2', '李医生', '3', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('68', '3', '王医生', '65', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('69', '3', '王医生', '3', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('70', '3', '王医生', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('71', '3', '王医生', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('72', '3', '王医生', '13', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('73', '3', '王医生', '74', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('74', '3', '王医生', '7', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('75', '3', '王医生', '14', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('76', '3', '王医生', '41', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('77', '3', '王医生', '32', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('78', '3', '王医生', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('79', '3', '王医生', '8', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('80', '3', '王医生', '4', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('81', '3', '王医生', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('82', '3', '王医生', '12', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('83', '3', '王医生', '18', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('84', '3', '王医生', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('85', '3', '王医生', '66', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('86', '3', '王医生', '98', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('87', '3', '王医生', '25', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('88', '4', '赵医生', '0', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('89', '4', '赵医生', '25', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('90', '4', '赵医生', '20', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('91', '4', '赵医生', '41', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('92', '4', '赵医生', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('93', '4', '赵医生', '8', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('94', '4', '赵医生', '58', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('95', '4', '赵医生', '74', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('96', '4', '赵医生', '26', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('97', '4', '赵医生', '52', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('98', '4', '赵医生', '87', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('99', '4', '赵医生', '25', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('100', '4', '赵医生', '87', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('101', '4', '赵医生', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('102', '4', '赵医生', '5', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('103', '4', '赵医生', '99', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('104', '4', '赵医生', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('105', '4', '赵医生', '10', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('106', '4', '赵医生', '1', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('107', '4', '赵医生', '0', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('108', '5', '曹医生', '25', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('109', '5', '曹医生', '42', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('110', '5', '曹医生', '92', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('111', '5', '曹医生', '89', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('112', '5', '曹医生', '87', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('113', '5', '曹医生', '14', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('114', '5', '曹医生', '67', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('115', '5', '曹医生', '0', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('116', '5', '曹医生', '63', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('117', '5', '曹医生', '10', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('118', '5', '曹医生', '52', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('119', '5', '曹医生', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('120', '5', '曹医生', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('121', '5', '曹医生', '64', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('122', '5', '曹医生', '6', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('123', '5', '曹医生', '17', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('124', '5', '曹医生', '30', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('125', '5', '曹医生', '26', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('126', '5', '曹医生', '80', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('127', '5', '曹医生', '42', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('128', '6', 'caohao', '43', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('129', '6', 'caohao', '30', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('130', '6', 'caohao', '92', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('131', '6', 'caohao', '43', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('132', '6', 'caohao', '41', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('133', '6', 'caohao', '54', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('134', '6', 'caohao', '19', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('135', '6', 'caohao', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('136', '6', 'caohao', '13', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('137', '6', 'caohao', '27', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('138', '6', 'caohao', '96', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('139', '6', 'caohao', '73', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('140', '6', 'caohao', '73', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('141', '6', 'caohao', '72', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('142', '6', 'caohao', '8', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('143', '6', 'caohao', '82', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('144', '6', 'caohao', '62', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('145', '6', 'caohao', '46', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('146', '6', 'caohao', '61', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('147', '6', 'caohao', '73', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('148', '7', 'Ella', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('149', '7', 'Ella', '84', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('150', '7', 'Ella', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('151', '7', 'Ella', '42', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('152', '7', 'Ella', '48', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('153', '7', 'Ella', '55', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('154', '7', 'Ella', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('155', '7', 'Ella', '64', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('156', '7', 'Ella', '98', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('157', '7', 'Ella', '32', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('158', '7', 'Ella', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('159', '7', 'Ella', '7', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('160', '7', 'Ella', '56', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('161', '7', 'Ella', '1', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('162', '7', 'Ella', '98', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('163', '7', 'Ella', '65', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('164', '7', 'Ella', '50', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('165', '7', 'Ella', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('166', '7', 'Ella', '33', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('167', '7', 'Ella', '93', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('168', '8', 'Frank', '8', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('169', '8', 'Frank', '76', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('170', '8', 'Frank', '6', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('171', '8', 'Frank', '33', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('172', '8', 'Frank', '75', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('173', '8', 'Frank', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('174', '8', 'Frank', '48', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('175', '8', 'Frank', '49', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('176', '8', 'Frank', '80', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('177', '8', 'Frank', '52', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('178', '8', 'Frank', '49', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('179', '8', 'Frank', '91', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('180', '8', 'Frank', '2', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('181', '8', 'Frank', '20', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('182', '8', 'Frank', '36', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('183', '8', 'Frank', '28', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('184', '8', 'Frank', '25', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('185', '8', 'Frank', '38', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('186', '8', 'Frank', '34', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('187', '8', 'Frank', '41', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('188', '9', 'Alice', '61', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('189', '9', 'Alice', '63', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('190', '9', 'Alice', '23', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('191', '9', 'Alice', '24', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('192', '9', 'Alice', '99', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('193', '9', 'Alice', '63', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('194', '9', 'Alice', '75', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('195', '9', 'Alice', '26', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('196', '9', 'Alice', '96', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('197', '9', 'Alice', '27', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('198', '9', 'Alice', '47', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('199', '9', 'Alice', '42', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('200', '9', 'Alice', '4', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('201', '9', 'Alice', '12', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('202', '9', 'Alice', '47', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('203', '9', 'Alice', '4', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('204', '9', 'Alice', '69', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('205', '9', 'Alice', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('206', '9', 'Alice', '98', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('207', '9', 'Alice', '9', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('208', '10', 'Bob', '49', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('209', '10', 'Bob', '31', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('210', '10', 'Bob', '90', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('211', '10', 'Bob', '31', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('212', '10', 'Bob', '1', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('213', '10', 'Bob', '97', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('214', '10', 'Bob', '1', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('215', '10', 'Bob', '74', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('216', '10', 'Bob', '77', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('217', '10', 'Bob', '35', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('218', '10', 'Bob', '78', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('219', '10', 'Bob', '28', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('220', '10', 'Bob', '28', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('221', '10', 'Bob', '63', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('222', '10', 'Bob', '93', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('223', '10', 'Bob', '65', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('224', '10', 'Bob', '99', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('225', '10', 'Bob', '13', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('226', '10', 'Bob', '53', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('227', '10', 'Bob', '89', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('228', '11', 'Cindy', '90', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('229', '11', 'Cindy', '17', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('230', '11', 'Cindy', '0', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('231', '11', 'Cindy', '23', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('232', '11', 'Cindy', '57', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('233', '11', 'Cindy', '85', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('234', '11', 'Cindy', '29', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('235', '11', 'Cindy', '92', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('236', '11', 'Cindy', '96', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('237', '11', 'Cindy', '50', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('238', '11', 'Cindy', '73', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('239', '11', 'Cindy', '49', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('240', '11', 'Cindy', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('241', '11', 'Cindy', '34', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('242', '11', 'Cindy', '19', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('243', '11', 'Cindy', '50', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('244', '11', 'Cindy', '77', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('245', '11', 'Cindy', '59', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('246', '11', 'Cindy', '27', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('247', '11', 'Cindy', '68', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('248', '12', 'Danny', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('249', '12', 'Danny', '18', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('250', '12', 'Danny', '41', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('251', '12', 'Danny', '13', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('252', '12', 'Danny', '50', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('253', '12', 'Danny', '76', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('254', '12', 'Danny', '55', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('255', '12', 'Danny', '94', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('256', '12', 'Danny', '72', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('257', '12', 'Danny', '82', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('258', '12', 'Danny', '72', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('259', '12', 'Danny', '86', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('260', '12', 'Danny', '5', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('261', '12', 'Danny', '43', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('262', '12', 'Danny', '21', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('263', '12', 'Danny', '45', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('264', '12', 'Danny', '48', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('265', '12', 'Danny', '51', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('266', '12', 'Danny', '31', 'a', '8', '2017-12-13');
INSERT INTO `sys_log` VALUES ('267', '12', 'Danny', '58', 'a', '8', '2017-12-13');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trust_attr
-- ----------------------------
INSERT INTO `trust_attr` VALUES ('11', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('12', '1', '2', '3', '3');
INSERT INTO `trust_attr` VALUES ('13', '3', '2', '1', '1');
INSERT INTO `trust_attr` VALUES ('14', '0', '3', '2', '1');
INSERT INTO `trust_attr` VALUES ('15', '2', '0', '4', '0');
INSERT INTO `trust_attr` VALUES ('16', '1', '2', '3', '2');
INSERT INTO `trust_attr` VALUES ('17', '3', '4', '4', '2');
INSERT INTO `trust_attr` VALUES ('18', '3', '0', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('14', '1', '张医生', '90', '态度好', '2017-12-06', '1', '张三');
INSERT INTO `user_log` VALUES ('15', '2', '李医生', '80', '医德高尚', '2017-12-19', '1', '赵四');
INSERT INTO `user_log` VALUES ('16', '3', '王医生', '50', 'aaaa', '2017-11-14', '1', '王五');
INSERT INTO `user_log` VALUES ('17', '10', 'Bob', '60', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('18', '10', 'Bob', '70', 'bbb', '2017-12-07', '1', 'X');
INSERT INTO `user_log` VALUES ('19', '10', 'Bob', '35', 'bbb', '2017-12-15', '1', 'X');
INSERT INTO `user_log` VALUES ('20', '10', 'Bob', '45', 'bbb', '2017-12-15', '1', 'X');
INSERT INTO `user_log` VALUES ('21', '10', 'Bob', '90', 'bbb', '2017-12-21', '1', 'X');
INSERT INTO `user_log` VALUES ('23', '1', '张医生', '54', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('24', '1', '张医生', '91', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('25', '1', '张医生', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('26', '1', '张医生', '19', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('27', '1', '张医生', '14', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('28', '1', '张医生', '50', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('29', '1', '张医生', '56', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('30', '1', '张医生', '85', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('31', '1', '张医生', '70', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('32', '1', '张医生', '37', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('33', '1', '张医生', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('34', '1', '张医生', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('35', '1', '张医生', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('36', '1', '张医生', '26', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('37', '1', '张医生', '34', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('38', '1', '张医生', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('39', '1', '张医生', '35', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('40', '1', '张医生', '86', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('41', '1', '张医生', '86', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('42', '1', '张医生', '26', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('43', '2', '李医生', '62', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('44', '2', '李医生', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('45', '2', '李医生', '48', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('46', '2', '李医生', '20', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('47', '2', '李医生', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('48', '2', '李医生', '18', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('49', '2', '李医生', '86', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('50', '2', '李医生', '5', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('51', '2', '李医生', '35', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('52', '2', '李医生', '92', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('53', '2', '李医生', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('54', '2', '李医生', '90', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('55', '2', '李医生', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('56', '2', '李医生', '7', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('57', '2', '李医生', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('58', '2', '李医生', '46', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('59', '2', '李医生', '85', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('60', '2', '李医生', '90', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('61', '2', '李医生', '23', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('62', '2', '李医生', '96', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('63', '3', '王医生', '78', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('64', '3', '王医生', '33', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('65', '3', '王医生', '6', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('66', '3', '王医生', '59', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('67', '3', '王医生', '95', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('68', '3', '王医生', '78', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('69', '3', '王医生', '64', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('70', '3', '王医生', '52', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('71', '3', '王医生', '70', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('72', '3', '王医生', '66', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('73', '3', '王医生', '28', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('74', '3', '王医生', '71', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('75', '3', '王医生', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('76', '3', '王医生', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('77', '3', '王医生', '41', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('78', '3', '王医生', '10', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('79', '3', '王医生', '51', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('80', '3', '王医生', '99', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('81', '3', '王医生', '73', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('82', '3', '王医生', '30', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('83', '4', '赵医生', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('84', '4', '赵医生', '58', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('85', '4', '赵医生', '84', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('86', '4', '赵医生', '36', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('87', '4', '赵医生', '42', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('88', '4', '赵医生', '81', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('89', '4', '赵医生', '51', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('90', '4', '赵医生', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('91', '4', '赵医生', '12', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('92', '4', '赵医生', '91', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('93', '4', '赵医生', '96', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('94', '4', '赵医生', '78', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('95', '4', '赵医生', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('96', '4', '赵医生', '69', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('97', '4', '赵医生', '79', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('98', '4', '赵医生', '89', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('99', '4', '赵医生', '93', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('100', '4', '赵医生', '0', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('101', '4', '赵医生', '70', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('102', '4', '赵医生', '81', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('103', '5', '曹医生', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('104', '5', '曹医生', '0', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('105', '5', '曹医生', '19', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('106', '5', '曹医生', '75', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('107', '5', '曹医生', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('108', '5', '曹医生', '92', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('109', '5', '曹医生', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('110', '5', '曹医生', '34', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('111', '5', '曹医生', '15', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('112', '5', '曹医生', '10', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('113', '5', '曹医生', '25', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('114', '5', '曹医生', '40', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('115', '5', '曹医生', '77', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('116', '5', '曹医生', '66', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('117', '5', '曹医生', '19', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('118', '5', '曹医生', '21', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('119', '5', '曹医生', '5', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('120', '5', '曹医生', '18', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('121', '5', '曹医生', '67', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('122', '5', '曹医生', '72', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('123', '6', 'caohao', '74', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('124', '6', 'caohao', '85', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('125', '6', 'caohao', '80', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('126', '6', 'caohao', '89', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('127', '6', 'caohao', '15', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('128', '6', 'caohao', '18', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('129', '6', 'caohao', '1', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('130', '6', 'caohao', '62', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('131', '6', 'caohao', '49', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('132', '6', 'caohao', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('133', '6', 'caohao', '40', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('134', '6', 'caohao', '1', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('135', '6', 'caohao', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('136', '6', 'caohao', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('137', '6', 'caohao', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('138', '6', 'caohao', '80', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('139', '6', 'caohao', '1', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('140', '6', 'caohao', '14', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('141', '6', 'caohao', '81', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('142', '6', 'caohao', '84', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('143', '7', 'Ella', '33', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('144', '7', 'Ella', '24', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('145', '7', 'Ella', '31', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('146', '7', 'Ella', '94', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('147', '7', 'Ella', '15', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('148', '7', 'Ella', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('149', '7', 'Ella', '35', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('150', '7', 'Ella', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('151', '7', 'Ella', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('152', '7', 'Ella', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('153', '7', 'Ella', '11', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('154', '7', 'Ella', '83', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('155', '7', 'Ella', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('156', '7', 'Ella', '23', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('157', '7', 'Ella', '58', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('158', '7', 'Ella', '57', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('159', '7', 'Ella', '35', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('160', '7', 'Ella', '75', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('161', '7', 'Ella', '80', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('162', '7', 'Ella', '56', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('163', '8', 'Frank', '10', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('164', '8', 'Frank', '15', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('165', '8', 'Frank', '60', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('166', '8', 'Frank', '48', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('167', '8', 'Frank', '69', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('168', '8', 'Frank', '26', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('169', '8', 'Frank', '93', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('170', '8', 'Frank', '52', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('171', '8', 'Frank', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('172', '8', 'Frank', '95', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('173', '8', 'Frank', '6', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('174', '8', 'Frank', '5', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('175', '8', 'Frank', '60', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('176', '8', 'Frank', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('177', '8', 'Frank', '46', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('178', '8', 'Frank', '1', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('179', '8', 'Frank', '71', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('180', '8', 'Frank', '69', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('181', '8', 'Frank', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('182', '8', 'Frank', '18', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('183', '9', 'Alice', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('184', '9', 'Alice', '42', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('185', '9', 'Alice', '96', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('186', '9', 'Alice', '64', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('187', '9', 'Alice', '24', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('188', '9', 'Alice', '86', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('189', '9', 'Alice', '8', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('190', '9', 'Alice', '0', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('191', '9', 'Alice', '46', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('192', '9', 'Alice', '59', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('193', '9', 'Alice', '44', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('194', '9', 'Alice', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('195', '9', 'Alice', '41', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('196', '9', 'Alice', '34', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('197', '9', 'Alice', '45', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('198', '9', 'Alice', '74', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('199', '9', 'Alice', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('200', '9', 'Alice', '82', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('201', '9', 'Alice', '88', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('202', '9', 'Alice', '89', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('203', '10', 'Bob', '92', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('204', '10', 'Bob', '16', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('205', '10', 'Bob', '51', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('206', '10', 'Bob', '52', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('207', '10', 'Bob', '62', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('208', '10', 'Bob', '24', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('209', '10', 'Bob', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('210', '10', 'Bob', '17', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('211', '10', 'Bob', '71', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('212', '10', 'Bob', '81', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('213', '10', 'Bob', '23', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('214', '10', 'Bob', '61', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('215', '10', 'Bob', '92', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('216', '10', 'Bob', '62', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('217', '10', 'Bob', '80', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('218', '10', 'Bob', '34', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('219', '10', 'Bob', '10', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('220', '10', 'Bob', '66', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('221', '10', 'Bob', '1', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('222', '10', 'Bob', '78', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('223', '11', 'Cindy', '84', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('224', '11', 'Cindy', '54', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('225', '11', 'Cindy', '92', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('226', '11', 'Cindy', '90', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('227', '11', 'Cindy', '96', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('228', '11', 'Cindy', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('229', '11', 'Cindy', '68', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('230', '11', 'Cindy', '32', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('231', '11', 'Cindy', '75', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('232', '11', 'Cindy', '70', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('233', '11', 'Cindy', '26', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('234', '11', 'Cindy', '72', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('235', '11', 'Cindy', '70', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('236', '11', 'Cindy', '81', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('237', '11', 'Cindy', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('238', '11', 'Cindy', '26', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('239', '11', 'Cindy', '91', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('240', '11', 'Cindy', '59', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('241', '11', 'Cindy', '3', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('242', '11', 'Cindy', '24', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('243', '12', 'Danny', '84', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('244', '12', 'Danny', '22', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('245', '12', 'Danny', '93', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('246', '12', 'Danny', '9', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('247', '12', 'Danny', '38', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('248', '12', 'Danny', '54', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('249', '12', 'Danny', '85', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('250', '12', 'Danny', '2', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('251', '12', 'Danny', '36', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('252', '12', 'Danny', '25', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('253', '12', 'Danny', '58', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('254', '12', 'Danny', '7', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('255', '12', 'Danny', '90', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('256', '12', 'Danny', '4', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('257', '12', 'Danny', '65', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('258', '12', 'Danny', '52', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('259', '12', 'Danny', '85', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('260', '12', 'Danny', '47', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('261', '12', 'Danny', '90', 'bbb', '2017-12-25', '1', 'X');
INSERT INTO `user_log` VALUES ('262', '12', 'Danny', '60', 'bbb', '2017-12-25', '1', 'X');
SET FOREIGN_KEY_CHECKS=1;
