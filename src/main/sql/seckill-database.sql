/*
Navicat MySQL Data Transfer

Source Server         : liuyu7177
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-05-10 19:17:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '98', '2019-05-10 12:02:19', '2019-05-09 00:00:00', '2019-05-06 16:14:03');
INSERT INTO `seckill` VALUES ('1001', '500元秒杀iphone6', '191', '2019-05-10 19:15:29', '2019-05-11 00:00:00', '2019-05-06 16:14:03');
INSERT INTO `seckill` VALUES ('1002', '300元秒杀iphone6', '300', '2019-05-11 00:00:00', '2019-05-12 00:00:00', '2019-05-06 16:14:03');
INSERT INTO `seckill` VALUES ('1003', '200元秒杀iphone6', '399', '2019-05-10 14:49:38', '2019-05-11 00:00:00', '2019-05-06 16:14:03');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标示：-1：无效 0：成功 1：已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1000', '13800138000', '-1', '2019-05-07 18:19:55');
INSERT INTO `success_killed` VALUES ('1000', '15989211016', '0', '2019-05-08 16:45:03');
INSERT INTO `success_killed` VALUES ('1000', '15989211017', '0', '2019-05-08 16:54:47');
INSERT INTO `success_killed` VALUES ('1001', '13800138000', '-1', '2019-05-10 19:06:45');
INSERT INTO `success_killed` VALUES ('1001', '13800138050', '-1', '2019-05-10 19:08:06');
INSERT INTO `success_killed` VALUES ('1001', '15918740017', '1', '2019-05-10 19:15:30');
INSERT INTO `success_killed` VALUES ('1001', '15951211014', '-1', '2019-05-10 18:25:57');
INSERT INTO `success_killed` VALUES ('1001', '15959211014', '-1', '2019-05-10 18:23:22');
INSERT INTO `success_killed` VALUES ('1001', '15989211014', '-1', '2019-05-10 18:23:08');
INSERT INTO `success_killed` VALUES ('1001', '15989211016', '0', '2019-05-10 15:02:57');
INSERT INTO `success_killed` VALUES ('1001', '15989211018', '0', '2019-05-10 17:41:07');
INSERT INTO `success_killed` VALUES ('1001', '19800138050', '1', '2019-05-10 19:11:36');
INSERT INTO `success_killed` VALUES ('1003', '15989211016', '0', '2019-05-10 14:49:38');

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`liuyu7177`@`%` PROCEDURE `execute_seckill`(IN v_seckill_id BIGINT,
	IN v_phone BIGINT,
	IN v_kill_time TIMESTAMP,
	OUT r_result INT)
BEGIN
	DECLARE
		insert_count INT DEFAULT 0;

START TRANSACTION;

INSERT IGNORE INTO success_killed (
	seckill_id,
	user_phone,
	create_time,
   state
)
VALUES
	(
		v_seckill_id,
		v_phone,
		v_kill_time,
    1
	);

SELECT
	ROW_COUNT() INTO insert_count;


IF (insert_count = 0) THEN
	ROLLBACK;


SET r_result =-1;


ELSEIF (insert_count = 0) THEN
	ROLLBACK;


SET r_result =-2;


ELSE
	UPDATE seckill
SET number = number - 1
WHERE
	seckill_id = v_seckill_id
AND end_time > v_kill_time
AND start_time < v_kill_time
AND number > 0;

SELECT
	ROW_COUNT() INTO insert_count;


IF (INsert_count = 0) THEN
	ROLLBACK;


SET r_result = 0;


ELSEIF (insert_count < 0) THEN
	ROLLBACK;


SET r_result =-2;


ELSE
COMMIT;

SET r_result =1;
END
IF;


END
IF;


END
;;
DELIMITER ;
