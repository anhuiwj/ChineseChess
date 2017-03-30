/*
Navicat MySQL Data Transfer

Source Server         : mjs
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : chinesechess

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-29 10:05:38
*/


CREATE DATABASE IF NOT EXISTS chinesechess default charset utf8 COLLATE utf8_general_ci; 

use chinesechess；

-- ----------------------------
-- Table structure for `chess`
-- ----------------------------
DROP TABLE IF EXISTS `chess`;
CREATE TABLE `chess` (
  `id` varchar(32) NOT NULL COMMENT '当前旗子',
  `dqqzi` varchar(32) NOT NULL,
  `dqlocation` varchar(32) NOT NULL COMMENT '当前棋子',
  `targetName` varchar(32) DEFAULT NULL COMMENT '目标旗子',
  `targeColor` varchar(32) DEFAULT NULL,
  `targetLocation` varchar(32) DEFAULT NULL COMMENT '目标坐标',
  `createDate` datetime NOT NULL,
  `createBy` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chess
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123');
INSERT INTO `user` VALUES ('1490348750673', 'jiejie', '123');
