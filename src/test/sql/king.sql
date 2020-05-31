/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost
 Source Database       : king

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : utf-8

 Date: 06/01/2020 01:50:49 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_sys_appoint`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_appoint`;
CREATE TABLE `t_sys_appoint` (
  `appoint_id` varchar(255) NOT NULL COMMENT '年月日+流水号\r\n            \r\n            也可以计算 前面还有多少人',
  `user_id` varchar(32) DEFAULT NULL,
  `user_name` varchar(15) DEFAULT NULL,
  `outpatient_id` varchar(32) DEFAULT NULL,
  `appoint_date` date DEFAULT NULL,
  `appoint_stage` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `appoint_stage_name` varchar(15) DEFAULT NULL,
  `appoint_status` char(1) DEFAULT NULL,
  `cancel_id` varchar(32) DEFAULT NULL COMMENT '可以是医生 也可以是用户',
  `cancel_name` varchar(15) DEFAULT NULL,
  `medical_advice` varchar(255) DEFAULT NULL COMMENT '医嘱',
  `patient_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '患者类型:1门诊2回诊',
  `location` int(20) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`appoint_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_sys_appoint`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_appoint` VALUES ('05945b98076145bda96466d4dd8ccfc1', '1234', 'admin', '001', '2020-05-30', null, '2020-05-30', '5', '123', 'admin', null, '门诊', '1'), ('2adf9fd5778041ec8940db6b2b5a8dce', '123', 'admin', '001', '2020-05-30', '', '2020-05-30', '6', '123', 'admin', null, '回诊', '1'), ('3b23a06ed0f54e419186f77e6e16cbfc', '123', 'admin', '001', '2020-05-30', null, '2020-05-30', '4', '123', 'admin', null, '门诊', '2');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_autoincrement`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_autoincrement`;
CREATE TABLE `t_sys_autoincrement` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) DEFAULT NULL,
  `increment_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_department`;
CREATE TABLE `t_sys_department` (
  `department_Id` varchar(255) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `department_profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_sys_department`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_department` VALUES ('101', '发热科', '100'), ('102', '内科', '50'), ('103', '外科', '25');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_information`;
CREATE TABLE `t_sys_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduction` text COMMENT '医院简介',
  `announcement` text COMMENT '医院公告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_sys_information`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_information` VALUES ('1', '北京大学第三医院(简称“北医三院”)建于1958年,国家卫生健康委委管医院,集医疗、教学、科研、预防、康复与保健为一体的综合性三甲医院。', '长沙市第一医院位于湖南省长沙市开福区营盘路311号，始建于1920年，占地面积210余亩，是一所集医疗、教学、科研、预防保健、康复和公共卫生救治于一体的三级综合性医院。');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_outpatient`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_outpatient`;
CREATE TABLE `t_sys_outpatient` (
  `outpatient_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `doctor_id` varchar(32) DEFAULT NULL,
  `doctor_name` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `outpatient_date` date DEFAULT NULL,
  `outpatient_number` int(11) DEFAULT NULL,
  `outpatient_notice` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `current_num` int(11) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`outpatient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_sys_outpatient`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_outpatient` VALUES ('001', '124', '张强', '2020-05-30', '100', '常规检查', '1', '103'), ('226618c7645b4257ba7b94de6605964d', '123', 'admin', '2020-06-03', '50', null, '0', '103'), ('237e322670024e3081aabc105a2bd867', '123', 'admin', '2020-06-06', '50', null, null, '103'), ('3d67d4913f05437e987dafc8218a8fe1', '123', 'admin', '2020-06-07', '50', null, null, '103'), ('4586937ebf734f8ea27c732167e6e658', '123', 'admin', '2020-05-31', '50', null, null, '103'), ('6b1b813774ce4198aa41cdff7217ac7a', '123', 'admin', '2020-06-04', '50', null, null, '103'), ('df232beb88cb492e8dfc6f53f10a962f', '123', 'admin', '2020-06-02', '50', null, null, '103'), ('ea9398e287494b17b9d0a51f2ba821ab', '123', 'admin', '2020-06-01', '50', null, null, '103'), ('ec720970c21b49c2b07878d51b54913d', '123', 'admin', '2020-06-05', '50', null, null, '103');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  `user_sex` char(1) DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `role_code` char(1) DEFAULT NULL,
  `role_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `department_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_user` VALUES ('123', 'admin', '123456', '1', '2020-05-22', '12247222669', '3', '管理员', null), ('124', '张强', '123456', '1', '2020-05-29', '12222222222', '2', '医生', '103');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
