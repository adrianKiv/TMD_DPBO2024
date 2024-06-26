/*
 Navicat Premium Data Transfer

 Source Server         : coba01
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : db_updown

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 21/06/2024 11:21:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tscore
-- ----------------------------
DROP TABLE IF EXISTS `tscore`;
CREATE TABLE `tscore`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `up` int NULL DEFAULT NULL,
  `down` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tscore
-- ----------------------------
INSERT INTO `tscore` VALUES ('hedi', '200', 22, 75);
INSERT INTO `tscore` VALUES ('lapu', '487', 41, 47);
INSERT INTO `tscore` VALUES ('defaultUser11', '1135', 9, 19);
INSERT INTO `tscore` VALUES ('defaultUser', '909', 11, 19);
INSERT INTO `tscore` VALUES ('agan', '871', 11, 5);
INSERT INTO `tscore` VALUES ('ade', '1238', 18, 26);
INSERT INTO `tscore` VALUES ('adenaufal', '1079', 12, 30);
INSERT INTO `tscore` VALUES ('aaa', '2001', 16, 54);
INSERT INTO `tscore` VALUES ('bbb', '2121', 20, 51);
INSERT INTO `tscore` VALUES ('sss', '5583', 31, 132);
INSERT INTO `tscore` VALUES ('dd', '10849', 72, 233);
INSERT INTO `tscore` VALUES ('yan', '8854', 40, 138);
INSERT INTO `tscore` VALUES ('kiv', '3160', 192, 65);
INSERT INTO `tscore` VALUES ('johan', '862', 6, 19);
INSERT INTO `tscore` VALUES ('kero', '2364', 13, 55);
INSERT INTO `tscore` VALUES ('lucifer', '914', 8, 20);
INSERT INTO `tscore` VALUES ('diablo', '3269', 20, 73);
INSERT INTO `tscore` VALUES ('hendra', '1051', 4, 26);
INSERT INTO `tscore` VALUES ('kuro', '3825', 236, 665);
INSERT INTO `tscore` VALUES ('yole', '5715', 647, 48);
INSERT INTO `tscore` VALUES ('gura', '1013', 888, 892);
INSERT INTO `tscore` VALUES ('nooel', '45454', 432, 112);
INSERT INTO `tscore` VALUES ('adrian', '660', 4, 15);

SET FOREIGN_KEY_CHECKS = 1;
