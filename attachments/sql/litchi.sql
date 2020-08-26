/*
 Navicat Premium Data Transfer

 Source Server         : 荔枝园
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 127.0.0.1:3306
 Source Schema         : litchi

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 22/03/2020 21:21:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lz_administrator
-- ----------------------------
DROP TABLE IF EXISTS `lz_administrator`;
CREATE TABLE `lz_administrator`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_administrator
-- ----------------------------

-- ----------------------------
-- Table structure for lz_alarm_log
-- ----------------------------
DROP TABLE IF EXISTS `lz_alarm_log`;
CREATE TABLE `lz_alarm_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) NULL DEFAULT NULL COMMENT '节点id',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '规则组id',
  `threshold` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则组阈值',
  `monitor_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监控处理所得数据',
  `message` varchar(1023) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_alarm_log
-- ----------------------------

-- ----------------------------
-- Table structure for lz_harm
-- ----------------------------
DROP TABLE IF EXISTS `lz_harm`;
CREATE TABLE `lz_harm`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危害类别 1：虫害 2：病害',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危害名称',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危害情况',
  `feature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特征',
  `rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发生规律',
  `measure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '防治方法',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_harm
-- ----------------------------

-- ----------------------------
-- Table structure for lz_limit
-- ----------------------------
DROP TABLE IF EXISTS `lz_limit`;
CREATE TABLE `lz_limit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生长时期',
  `tu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '温度上限',
  `td` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '温度下限',
  `hu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '湿度上限',
  `hd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '湿度下限',
  `lu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '光照强度上限',
  `ld` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '光照强度下限',
  `wu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '土壤含水率上限',
  `wd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '土壤含水率下限',
  `vd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电池电压下限',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_limit
-- ----------------------------

-- ----------------------------
-- Table structure for lz_litchi
-- ----------------------------
DROP TABLE IF EXISTS `lz_litchi`;
CREATE TABLE `lz_litchi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '荔枝id',
  `litchi_type_id` bigint(20) NOT NULL COMMENT '荔枝品种类型id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '荔枝种类名称',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_litchi
-- ----------------------------

-- ----------------------------
-- Table structure for lz_litchi_type
-- ----------------------------
DROP TABLE IF EXISTS `lz_litchi_type`;
CREATE TABLE `lz_litchi_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '荔枝品种类型id',
  `type` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '荔枝品种类型名称',
  `jan` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '1月工作日历',
  `feb` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '2月工作日历',
  `mar` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '3月工作日历',
  `apr` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '4月工作日历',
  `may` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '5月工作日历',
  `jun` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '6月工作日历',
  `jul` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '7月工作日历',
  `aug` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '8月工作日历',
  `sept` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '9月工作日历',
  `oct` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '10月工作日历',
  `nov` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '11月工作日历',
  `dece` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '12月工作日历',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_litchi_type
-- ----------------------------

-- ----------------------------
-- Table structure for lz_monitor_regulation_group
-- ----------------------------
DROP TABLE IF EXISTS `lz_monitor_regulation_group`;
CREATE TABLE `lz_monitor_regulation_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orchard_id` bigint(20) NULL DEFAULT NULL COMMENT '果园id',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '规则状态',
  `message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建议',
  `begin_day` int(11) NULL DEFAULT NULL COMMENT '规则生效时间范围起点，一年中的第几天',
  `end_day` int(11) NULL DEFAULT NULL COMMENT '规则生效时间范围结束，一年中的第几天',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_monitor_regulation_group
-- ----------------------------

-- ----------------------------
-- Table structure for lz_monitor_regulation_item
-- ----------------------------
DROP TABLE IF EXISTS `lz_monitor_regulation_item`;
CREATE TABLE `lz_monitor_regulation_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '所属规则组id',
  `index` int(10) NULL DEFAULT NULL COMMENT '序号',
  `threshold_type` int(5) NULL DEFAULT NULL COMMENT '阈值类型：1，平均值 ；2，最小值；3，最大值',
  `data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影响因素，水分，温度等',
  `operator` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作符[\">\",\"<\",\"=\"]，表示 大于，小于，等于 阈值的情况。',
  `threshold` double NULL DEFAULT NULL COMMENT '阈值',
  `keep_minutes` bigint(20) NULL DEFAULT NULL COMMENT '触发告警所需现象的维持时间，单位是分钟。',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否应用',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_monitor_regulation_item
-- ----------------------------

-- ----------------------------
-- Table structure for lz_node
-- ----------------------------
DROP TABLE IF EXISTS `lz_node`;
CREATE TABLE `lz_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `node_type_id` int(11) NOT NULL COMMENT '节点类型id',
  `orchard_id` bigint(20) NOT NULL COMMENT '所属果园id',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点上传数据的凭证',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_node
-- ----------------------------

-- ----------------------------
-- Table structure for lz_node_data
-- ----------------------------
DROP TABLE IF EXISTS `lz_node_data`;
CREATE TABLE `lz_node_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `limit_id` bigint(20) NOT NULL COMMENT '限制id',
  `node_id` bigint(20) NOT NULL COMMENT '所属节点id',
  `time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间',
  `temp` double(255, 0) NULL DEFAULT NULL COMMENT '温度',
  `humi` double(255, 0) NULL DEFAULT NULL COMMENT '湿度',
  `lx` double(255, 0) NULL DEFAULT NULL COMMENT '光照强度',
  `tlx` double(255, 0) NULL DEFAULT NULL COMMENT '光照时长',
  `water` double(255, 0) NULL DEFAULT NULL COMMENT '土壤含水量',
  `co2` double(255, 0) NULL DEFAULT NULL COMMENT 'co2',
  `wind_direction` double(4, 0) NULL DEFAULT NULL COMMENT '风向',
  `wind_strength` double(255, 0) NULL DEFAULT NULL COMMENT '风力',
  `rainfall` double(255, 0) NULL DEFAULT NULL COMMENT '降雨量',
  `volt` double(255, 0) NULL DEFAULT NULL COMMENT '电池电压',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `time`(`time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 274 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_node_data
-- ----------------------------
INSERT INTO `lz_node_data` VALUES (226, 1, 2, '2020-03-15 22:36:02', 2, 9, 1, 1, 9, 10, 2, 1, 6, 5);
INSERT INTO `lz_node_data` VALUES (227, 1, 0, '2020-03-15 22:36:05', 6, 8, 5, 8, 9, 1, 0, 2, 10, 0);
INSERT INTO `lz_node_data` VALUES (228, 1, 1, '2020-03-15 22:36:06', 3, 9, 5, 5, 2, 0, 2, 8, 1, 6);
INSERT INTO `lz_node_data` VALUES (229, 1, 1, '2020-03-15 22:36:08', 10, 3, 2, 8, 7, 4, 2, 3, 3, 5);
INSERT INTO `lz_node_data` VALUES (230, 1, 0, '2020-03-15 22:36:11', 4, 10, 6, 8, 8, 2, 3, 6, 1, 10);
INSERT INTO `lz_node_data` VALUES (231, 1, 0, '2020-03-15 22:36:12', 2, 4, 4, 5, 4, 6, 3, 1, 3, 4);
INSERT INTO `lz_node_data` VALUES (232, 1, 1, '2020-03-15 22:36:15', 9, 8, 5, 7, 1, 3, 2, 5, 8, 5);
INSERT INTO `lz_node_data` VALUES (233, 1, 2, '2020-03-15 22:36:18', 6, 0, 4, 2, 7, 8, 1, 10, 8, 4);
INSERT INTO `lz_node_data` VALUES (234, 1, 0, '2020-03-15 22:36:18', 2, 8, 6, 2, 2, 2, 2, 3, 5, 8);
INSERT INTO `lz_node_data` VALUES (235, 1, 0, '2020-03-15 22:36:21', 4, 4, 1, 5, 4, 3, 0, 4, 9, 3);
INSERT INTO `lz_node_data` VALUES (236, 1, 1, '2020-03-15 22:36:24', 9, 7, 5, 1, 9, 9, 1, 2, 4, 3);
INSERT INTO `lz_node_data` VALUES (237, 1, 2, '2020-03-15 22:36:24', 7, 7, 3, 1, 6, 3, 1, 8, 3, 9);
INSERT INTO `lz_node_data` VALUES (238, 1, 1, '2020-03-15 22:36:27', 8, 0, 7, 3, 2, 4, 0, 4, 9, 7);
INSERT INTO `lz_node_data` VALUES (239, 1, 0, '2020-03-22 11:59:59', 0, 4, 7, 5, 8, 2, 2, 1, 6, 4);
INSERT INTO `lz_node_data` VALUES (240, 1, 2, '2020-03-22 12:00:02', 2, 6, 4, 4, 6, 7, 1, 1, 6, 7);
INSERT INTO `lz_node_data` VALUES (241, 1, 1, '2020-03-22 12:00:03', 1, 5, 0, 3, 1, 10, 3, 5, 6, 0);
INSERT INTO `lz_node_data` VALUES (242, 1, 2, '2020-03-22 12:00:05', 5, 6, 9, 7, 10, 9, 3, 8, 0, 4);
INSERT INTO `lz_node_data` VALUES (243, 1, 0, '2020-03-22 12:00:08', 4, 7, 9, 7, 6, 6, 1, 8, 2, 3);
INSERT INTO `lz_node_data` VALUES (244, 1, 2, '2020-03-22 12:00:09', 1, 7, 1, 10, 2, 1, 2, 8, 8, 3);
INSERT INTO `lz_node_data` VALUES (245, 1, 2, '2020-03-22 12:00:11', 3, 1, 4, 6, 0, 4, 1, 5, 8, 9);
INSERT INTO `lz_node_data` VALUES (246, 1, 1, '2020-03-22 12:00:14', 9, 7, 4, 2, 5, 9, 1, 5, 10, 6);
INSERT INTO `lz_node_data` VALUES (247, 1, 0, '2020-03-22 12:00:15', 2, 5, 3, 8, 7, 0, 1, 3, 4, 7);
INSERT INTO `lz_node_data` VALUES (248, 1, 0, '2020-03-22 12:00:17', 5, 5, 9, 1, 2, 1, 0, 9, 8, 3);
INSERT INTO `lz_node_data` VALUES (249, 1, 0, '2020-03-22 12:00:20', 9, 0, 4, 9, 0, 7, 2, 2, 6, 8);
INSERT INTO `lz_node_data` VALUES (250, 1, 2, '2020-03-22 12:00:21', 3, 7, 7, 6, 2, 2, 1, 6, 6, 6);
INSERT INTO `lz_node_data` VALUES (251, 1, 0, '2020-03-22 12:00:23', 0, 2, 7, 5, 10, 2, 1, 10, 6, 3);
INSERT INTO `lz_node_data` VALUES (252, 1, 2, '2020-03-22 12:00:27', 9, 4, 5, 9, 1, 7, 1, 7, 2, 9);
INSERT INTO `lz_node_data` VALUES (253, 1, 2, '2020-03-22 12:00:27', 5, 2, 7, 2, 1, 6, 3, 8, 7, 2);
INSERT INTO `lz_node_data` VALUES (254, 1, 1, '2020-03-22 12:00:30', 9, 7, 2, 8, 6, 8, 0, 5, 9, 6);
INSERT INTO `lz_node_data` VALUES (255, 1, 2, '2020-03-22 12:00:31', 3, 4, 4, 2, 7, 5, 2, 9, 1, 5);
INSERT INTO `lz_node_data` VALUES (256, 1, 1, '2020-03-22 12:00:33', 3, 9, 8, 8, 6, 1, 1, 3, 9, 7);
INSERT INTO `lz_node_data` VALUES (257, 1, 1, '2020-03-22 12:00:36', 3, 2, 0, 6, 9, 6, 0, 0, 0, 6);
INSERT INTO `lz_node_data` VALUES (258, 1, 2, '2020-03-22 12:00:37', 6, 8, 1, 1, 8, 6, 0, 7, 7, 6);
INSERT INTO `lz_node_data` VALUES (259, 1, 0, '2020-03-22 12:00:39', 1, 6, 8, 5, 4, 5, 1, 2, 0, 0);
INSERT INTO `lz_node_data` VALUES (260, 1, 2, '2020-03-22 12:00:42', 6, 5, 4, 9, 8, 8, 1, 9, 4, 6);
INSERT INTO `lz_node_data` VALUES (261, 1, 2, '2020-03-22 12:00:43', 1, 6, 10, 1, 2, 6, 1, 1, 5, 2);
INSERT INTO `lz_node_data` VALUES (262, 1, 0, '2020-03-22 12:00:45', 5, 9, 7, 5, 7, 5, 1, 1, 9, 8);
INSERT INTO `lz_node_data` VALUES (263, 1, 0, '2020-03-22 12:00:48', 6, 8, 7, 10, 7, 2, 1, 2, 2, 2);
INSERT INTO `lz_node_data` VALUES (264, 1, 2, '2020-03-22 12:00:49', 8, 5, 0, 1, 0, 5, 2, 0, 5, 9);
INSERT INTO `lz_node_data` VALUES (265, 1, 2, '2020-03-22 12:00:51', 2, 3, 8, 5, 6, 2, 0, 3, 2, 7);
INSERT INTO `lz_node_data` VALUES (266, 1, 0, '2020-03-22 12:00:54', 2, 6, 7, 3, 9, 0, 3, 5, 7, 2);
INSERT INTO `lz_node_data` VALUES (267, 1, 0, '2020-03-22 12:00:55', 10, 9, 1, 5, 2, 10, 1, 1, 4, 2);
INSERT INTO `lz_node_data` VALUES (268, 1, 1, '2020-03-22 12:00:57', 6, 1, 2, 3, 6, 7, 0, 6, 8, 9);
INSERT INTO `lz_node_data` VALUES (269, 1, 0, '2020-03-22 12:01:00', 6, 1, 9, 4, 9, 1, 3, 6, 7, 4);
INSERT INTO `lz_node_data` VALUES (270, 1, 2, '2020-03-22 12:01:01', 9, 0, 2, 1, 9, 2, 3, 9, 9, 9);
INSERT INTO `lz_node_data` VALUES (271, 1, 1, '2020-03-22 12:01:04', 3, 2, 1, 6, 6, 8, 3, 8, 9, 9);
INSERT INTO `lz_node_data` VALUES (272, 1, 2, '2020-03-22 12:01:05', 7, 4, 2, 8, 1, 6, 2, 1, 1, 9);
INSERT INTO `lz_node_data` VALUES (273, 1, 0, '2020-03-22 12:01:07', 8, 4, 6, 2, 6, 0, 1, 10, 6, 3);

-- ----------------------------
-- Table structure for lz_node_data_picture
-- ----------------------------
DROP TABLE IF EXISTS `lz_node_data_picture`;
CREATE TABLE `lz_node_data_picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `node_id` bigint(20) NOT NULL COMMENT '节点id',
  `message_id` bigint(20) NOT NULL COMMENT '提示信息id',
  `harm_id` bigint(20) NULL DEFAULT NULL COMMENT '危害信息id',
  `limit_id` bigint(20) NOT NULL COMMENT '数据限制值id',
  `time` timestamp(0) NULL DEFAULT NULL COMMENT '时间',
  `volt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电池电压',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `num` int(11) NULL DEFAULT NULL COMMENT '果实总数量',
  `young_num` int(11) NULL DEFAULT NULL COMMENT '幼果数量',
  `immature_num` int(11) NULL DEFAULT NULL COMMENT '未成熟果数量',
  `mature_num` int(11) NULL DEFAULT NULL COMMENT '成熟果数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_node_data_picture
-- ----------------------------

-- ----------------------------
-- Table structure for lz_node_type
-- ----------------------------
DROP TABLE IF EXISTS `lz_node_type`;
CREATE TABLE `lz_node_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点类型id ',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_node_type
-- ----------------------------

-- ----------------------------
-- Table structure for lz_orchard
-- ----------------------------
DROP TABLE IF EXISTS `lz_orchard`;
CREATE TABLE `lz_orchard`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '果园id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '果园名称',
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '果园负责人名称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_orchard
-- ----------------------------

-- ----------------------------
-- Table structure for lz_orchard_picture
-- ----------------------------
DROP TABLE IF EXISTS `lz_orchard_picture`;
CREATE TABLE `lz_orchard_picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '果园图片id',
  `orc_id` bigint(20) NOT NULL COMMENT '果园id',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '果园图片',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_orchard_picture
-- ----------------------------

-- ----------------------------
-- Table structure for lz_system_config
-- ----------------------------
DROP TABLE IF EXISTS `lz_system_config`;
CREATE TABLE `lz_system_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_system_config
-- ----------------------------

-- ----------------------------
-- Table structure for lz_text
-- ----------------------------
DROP TABLE IF EXISTS `lz_text`;
CREATE TABLE `lz_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '荔枝文本信息id',
  `text_type_id` bigint(20) NOT NULL COMMENT '荔枝文本信息类型id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文本标题',
  `message` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文本信息',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_text
-- ----------------------------

-- ----------------------------
-- Table structure for lz_text_type
-- ----------------------------
DROP TABLE IF EXISTS `lz_text_type`;
CREATE TABLE `lz_text_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '荔枝文本信息类型id',
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '荔枝文本信息父类型id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '荔枝文本信息类型名字',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lz_text_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_user');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '用户状态： 1,正常 0,暂停使用',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '$2a$10$DTFj0r5LvwUgHm90pV4CbOshh60E0q.ueyJVJxgVi0djvfDovANxi', NULL, NULL, NULL, 1, '2020-03-22 17:25:03', '2020-03-22 17:25:09');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `sys_user_id` bigint(20) NOT NULL,
  `sys_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sys_user_id`, `sys_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);

-- ----------------------------
-- Table structure for tb_administrator
-- ----------------------------
DROP TABLE IF EXISTS `tb_administrator`;
CREATE TABLE `tb_administrator`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_administrator
-- ----------------------------

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地区id',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '地区所属上级地区id',
  `area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_area
-- ----------------------------

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题描述',
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片2',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  INDEX `updated`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_content
-- ----------------------------

-- ----------------------------
-- Table structure for tb_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_category`;
CREATE TABLE `tb_content_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) NULL DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) NULL DEFAULT 1 COMMENT '该类目是否为父类目，1为true，0为false',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`, `status`) USING BTREE,
  INDEX `sort_order`(`sort_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '内容分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_content_category
-- ----------------------------

-- ----------------------------
-- Table structure for tb_franchiser
-- ----------------------------
DROP TABLE IF EXISTS `tb_franchiser`;
CREATE TABLE `tb_franchiser`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码、加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '用户状态：1、正常 0、暂停使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_franchiser
-- ----------------------------

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item`  (
  `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `sell_point` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品卖点',
  `price` bigint(20) NOT NULL COMMENT '商品价格，单位为：分',
  `num` int(10) NOT NULL COMMENT '库存数量',
  `barcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品条形码',
  `image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(20) NOT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '商品状态，1-正常，2-下架，3-删除',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL COMMENT '更新时间',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '所属卖家id',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `promote` tinyint(4) NULL DEFAULT NULL COMMENT '同城推广，1-同城推广，2-不同城推广',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `updated`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_cat`;
CREATE TABLE `tb_item_cat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目名称',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) NULL DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) NULL DEFAULT 1 COMMENT '该类目是否为父类目，1为true，0为false',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`, `status`) USING BTREE,
  INDEX `sort_order`(`sort_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item_cat
-- ----------------------------

-- ----------------------------
-- Table structure for tb_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc`  (
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `item_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品描述表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------

-- ----------------------------
-- Table structure for tb_item_param
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param`;
CREATE TABLE `tb_item_param`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_cat_id` bigint(20) NULL DEFAULT NULL COMMENT '商品类目ID',
  `param_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数数据，格式为json格式',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_cat_id`(`item_cat_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规则参数' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item_param
-- ----------------------------

-- ----------------------------
-- Table structure for tb_item_param_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param_item`;
CREATE TABLE `tb_item_param_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `param_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数数据，格式为json格式',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格和商品的关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item_param_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `payment` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `payment_type` int(2) NULL DEFAULT NULL COMMENT '支付类型，1、在线支付，2、货到付款',
  `post_fee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` timestamp(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` timestamp(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` timestamp(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `buyer_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家昵称',
  `buyer_rate` int(2) NULL DEFAULT NULL COMMENT '买家是否已经评价',
  `vendor_id` bigint(20) NULL DEFAULT NULL COMMENT '卖家id',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE,
  INDEX `buyer_nick`(`buyer_nick`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `payment_type`(`payment_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) NULL DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品标题',
  `price` bigint(50) NULL DEFAULT NULL COMMENT '商品单价',
  `total_fee` bigint(50) NULL DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_shipping`;
CREATE TABLE `tb_order_shipping`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '移动电话',
  `receiver_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码,如：310001',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_order_shipping
-- ----------------------------

-- ----------------------------
-- Table structure for tb_vender
-- ----------------------------
DROP TABLE IF EXISTS `tb_vender`;
CREATE TABLE `tb_vender`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `idcard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `status` tinyint(4) NOT NULL COMMENT '用户状态：1，正常 0，暂停使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_vender
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
