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

 Date: 11/20/2017 20:41:25 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `history`
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `user_num` int(4) unsigned zerofill NOT NULL COMMENT '卡号',
  `date` date NOT NULL COMMENT '日期',
  `time` time NOT NULL COMMENT '时间',
  `operation` char(50) NOT NULL COMMENT '操作',
  `opreator` int(10) unsigned zerofill NOT NULL COMMENT '操作人卡号',
  `balance` float NOT NULL COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_num` int(18) unsigned zerofill NOT NULL COMMENT '身份证号',
  `user_num` int(4) unsigned zerofill NOT NULL COMMENT '卡号',
  `user_password` int(6) unsigned zerofill NOT NULL COMMENT '密码',
  `loss` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否挂失',
  `balance` float unsigned zerofill NOT NULL DEFAULT '000000000000' COMMENT '余额',
  PRIMARY KEY (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
