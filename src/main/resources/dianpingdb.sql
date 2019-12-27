/*
 Navicat Premium Data Transfer

 Source Server         : au_mac_pro_local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 127.0.0.1:3306
 Source Schema         : dianpingdb

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/12/2019 19:27:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `updated_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `telephone` varchar(40) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `nick_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;