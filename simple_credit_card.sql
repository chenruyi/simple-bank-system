/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : simple_credit_card

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 11/29/2017 17:50:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `history`
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `user_num` char(19) NOT NULL COMMENT '卡号',
  `date` char(50) NOT NULL COMMENT '日期',
  `time` char(50) NOT NULL COMMENT '时间',
  `operation` char(50) NOT NULL COMMENT '操作',
  `opreator` char(19) NOT NULL COMMENT '操作人卡号',
  `balance` float NOT NULL COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_num` char(30) NOT NULL COMMENT '身份证号',
  `user_num` char(19) NOT NULL COMMENT '卡号',
  `user_password` char(6) NOT NULL COMMENT '密码',
  `loss` char(1) NOT NULL DEFAULT '0' COMMENT '是否挂失',
  `balance` float unsigned NOT NULL COMMENT '余额',
  PRIMARY KEY (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('320113199705072411', '6222023202055190523', '123456', '0', '100000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
