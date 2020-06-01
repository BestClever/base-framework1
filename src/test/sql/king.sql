/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : king

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 01/06/2020 15:06:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_appoint
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_appoint`;
CREATE TABLE `t_sys_appoint`  (
  `appoint_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '年月日+流水号\r\n            \r\n            也可以计算 前面还有多少人',
  `user_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `user_name` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `outpatient_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `appoint_date` date NULL DEFAULT NULL,
  `appoint_stage` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appoint_stage_name` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `appoint_status` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `cancel_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '可以是医生 也可以是用户',
  `cancel_name` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `medical_advice` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '医嘱',
  `patient_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者类型:1门诊2回诊',
  `location` int(20) NULL DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`appoint_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_appoint
-- ----------------------------
INSERT INTO `t_sys_appoint` VALUES ('05945b98076145bda96466d4dd8ccfc1', '1234', 'admin', '001', '2020-05-30', NULL, '2020-05-30', '5', '123', 'admin', NULL, '门诊', 1);
INSERT INTO `t_sys_appoint` VALUES ('2adf9fd5778041ec8940db6b2b5a8dce', '123', 'admin', '001', '2020-05-30', '', '2020-05-30', '6', '123', 'admin', NULL, '回诊', 1);
INSERT INTO `t_sys_appoint` VALUES ('3b23a06ed0f54e419186f77e6e16cbfc', '123', 'admin', '001', '2020-05-30', NULL, '2020-05-30', '4', '123', 'admin', NULL, '门诊', 2);

-- ----------------------------
-- Table structure for t_sys_autoincrement
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_autoincrement`;
CREATE TABLE `t_sys_autoincrement`  (
  `seq_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `current_val` int(11) NULL DEFAULT NULL,
  `increment_val` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`seq_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_department
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_department`;
CREATE TABLE `t_sys_department`  (
  `department_Id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department_profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`department_Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_department
-- ----------------------------
INSERT INTO `t_sys_department` VALUES ('101', '发热科', '100');
INSERT INTO `t_sys_department` VALUES ('102', '内科', '50');
INSERT INTO `t_sys_department` VALUES ('103', '外科', '25');

-- ----------------------------
-- Table structure for t_sys_information
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_information`;
CREATE TABLE `t_sys_information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '医院简介',
  `announcement` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '医院公告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_information
-- ----------------------------
INSERT INTO `t_sys_information` VALUES (1, '北京大学第三医院(简称“北医三院”)建于1958年,国家卫生健康委委管医院,集医疗、教学、科研、预防、康复与保健为一体的综合性三甲医院。', '长沙市第一医院位于湖南省长沙市开福区营盘路311号，始建于1920年，占地面积210余亩，是一所集医疗、教学、科研、预防保健、康复和公共卫生救治于一体的三级综合性医院。');

-- ----------------------------
-- Table structure for t_sys_outpatient
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_outpatient`;
CREATE TABLE `t_sys_outpatient`  (
  `outpatient_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `doctor_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `doctor_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outpatient_date` date NULL DEFAULT NULL,
  `outpatient_number` int(11) NULL DEFAULT NULL,
  `outpatient_notice` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `current_num` int(11) NULL DEFAULT NULL,
  `dept_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`outpatient_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_outpatient
-- ----------------------------
INSERT INTO `t_sys_outpatient` VALUES ('001', '124', '张强', '2020-05-30', 100, '常规检查', 1, '103');
INSERT INTO `t_sys_outpatient` VALUES ('124a135c7f9743cab9a910edfc0f5113', '124', '张强', '2020-06-01', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('226618c7645b4257ba7b94de6605964d', '123', 'admin', '2020-06-03', 50, NULL, 0, '103');
INSERT INTO `t_sys_outpatient` VALUES ('237e322670024e3081aabc105a2bd867', '123', 'admin', '2020-06-06', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('26dceaccb926423d9f3508645cb319a5', '123', 'admin', '2020-06-05', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('2d3182f4d9f14a54971adbddff1cefc7', '123', 'admin', '2020-06-06', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('31d931d1cc5a48a8a3e1d59a2e14d6c4', '124', '张强', '2020-06-08', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('3d67d4913f05437e987dafc8218a8fe1', '123', 'admin', '2020-06-07', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('40711cf43d214036b4a1bb50e59ae9b5', '123', 'admin', '2020-06-01', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('4586937ebf734f8ea27c732167e6e658', '123', 'admin', '2020-05-31', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('46555b2fb1c842cf84eb608c511c2566', '124', '张强', '2020-06-04', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('4a7c6843cb954f7091ec2a8103d656d4', '124', '张强', '2020-06-06', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('6b1b813774ce4198aa41cdff7217ac7a', '123', 'admin', '2020-06-04', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('6c9713aefdcd484c924ca822a239275a', '123', 'admin', '2020-06-07', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('b72c7280332140668881543317ac19e3', '123', 'admin', '2020-06-04', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('ba47eb4dc571444d9857af18e293a236', '124', '张强', '2020-06-07', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('be46ccf0f49e49d58be42e0afdfafc36', '123', 'admin', '2020-06-08', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('c18e247dd92040c3bd06d27b47b4739e', '124', '张强', '2020-06-02', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('cdbb92de5f0248fd8435fb0acec03ce8', '124', '张强', '2020-06-05', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('df232beb88cb492e8dfc6f53f10a962f', '123', 'admin', '2020-06-02', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('e5c57fd39d1144af8339944eb698f989', '123', 'admin', '2020-06-02', 50, '常规检查', NULL, NULL);
INSERT INTO `t_sys_outpatient` VALUES ('ea72d374172646a788d4d0df84813afe', '124', '张强', '2020-06-03', 50, '常规检查', NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('ea9398e287494b17b9d0a51f2ba821ab', '123', 'admin', '2020-06-01', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('ec720970c21b49c2b07878d51b54913d', '123', 'admin', '2020-06-05', 50, NULL, NULL, '103');
INSERT INTO `t_sys_outpatient` VALUES ('f71734e85b184d6dbfd33ccf8f541b80', '123', 'admin', '2020-06-03', 50, '常规检查', NULL, NULL);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `user_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `user_sex` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `birth_day` date NULL DEFAULT NULL,
  `phone` varchar(13) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role_code` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('123', 'admin', '123456', '1', '2020-05-22', '12247222669', '3', '管理员', NULL);
INSERT INTO `t_sys_user` VALUES ('124', '张强', '123456', '1', '2020-05-29', '12222222222', '2', '医生', '103');

SET FOREIGN_KEY_CHECKS = 1;
