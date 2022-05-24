/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 24/05/2022 21:29:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delicacy
-- ----------------------------
DROP TABLE IF EXISTS `delicacy`;
CREATE TABLE `delicacy`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '美食id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '美食名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '美食描述',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '美食图片',
  `popular` tinyint(0) NOT NULL COMMENT '展示优先级',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delicacy
-- ----------------------------
INSERT INTO `delicacy` VALUES ('1528260176480604161', '蟹黄包', '很好吃', 'qaqwwa', 1, 0, '2022-05-22 14:23:14', '2022-05-22 15:04:23');
INSERT INTO `delicacy` VALUES ('1529088864428240897', '米糕', '很好吃', 'qqqqq', 0, 0, '2022-05-24 21:16:09', '2022-05-24 21:16:09');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈id',
  `mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游客邮箱',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈内容',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1528361382464978945', 'wq@163.com', '卧槽', 0, '2022-05-22 21:05:24', '2022-05-22 21:05:24');
INSERT INTO `feedback` VALUES ('1528617766280605698', 'wq@163.com', '卧槽', 0, '2022-05-23 14:04:10', '2022-05-23 14:04:10');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '旅馆id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '旅馆名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '旅馆介绍',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '旅馆图片',
  `popular` tinyint(0) NOT NULL COMMENT '展示优先级(用户主网站展示)',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1528227447567302657', '狮王旅馆', 'lion\'s pride', 'qaqawa', 1, 0, '2022-05-22 12:13:11', '2022-05-22 14:54:59');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员手机号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除(1为删除，0为没删除)',
  `create_time` datetime(0) NOT NULL COMMENT '记录添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('0', 'admin', '17075256495', '$2a$10$bzlXeNhRppIN5eS1eEbdge..MV5No00W/5N8jXwv66NE2Bm6qStGu', 0, '2022-05-24 09:07:27', '2022-05-24 09:07:32');
INSERT INTO `manager` VALUES ('1528213171205128194', 'admin2', '12345678910', '$2a$10$CYX9OMv0yO8wR8rE19N2fOaXDJondci5uR68k2eQJm50q8ESsDMlC', 0, '2022-05-22 11:16:28', '2022-05-22 14:52:32');
INSERT INTO `manager` VALUES ('1528358307016765442', 'admin1', '12345678911', '$2a$10$CYX9OMv0yO8wR8rE19N2fOaXDJondci5uR68k2eQJm50q8ESsDMlC', 0, '2022-05-22 20:53:11', '2022-05-22 20:53:11');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告描述',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告图片',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1528276227691917314', '公告AA', '今天下雨a', 'qaq', 0, '2022-05-22 15:27:01', '2022-05-22 15:29:17');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '0', 1);
INSERT INTO `permission` VALUES (2, '1528358307016765442', 2);
INSERT INTO `permission` VALUES (4, '0', 2);
INSERT INTO `permission` VALUES (5, '0', 3);
INSERT INTO `permission` VALUES (6, '0', 4);
INSERT INTO `permission` VALUES (7, '0', 8);
INSERT INTO `permission` VALUES (8, '0', 6);
INSERT INTO `permission` VALUES (9, '0', 7);
INSERT INTO `permission` VALUES (10, '0', 8);
INSERT INTO `permission` VALUES (11, '1528213171205128194', 4);

-- ----------------------------
-- Table structure for photos
-- ----------------------------
DROP TABLE IF EXISTS `photos`;
CREATE TABLE `photos`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片id',
  `mark_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片所属类型id',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片链接',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photos
-- ----------------------------
INSERT INTO `photos` VALUES ('1528602196071964674', '1528602195874832386', 'https://edu-19527.oss-cn-nanjing.aliyuncs.com/travel_GaoChun/d48d95a4a352415d9b9240f3fed25a6c9866091_carolin-zhou_1641393112.png', 1, '2022-05-23 13:02:18', '2022-05-23 13:02:18');
INSERT INTO `photos` VALUES ('1528617602232987649', '1528617602061021186', 'https://edu-19527.oss-cn-nanjing.aliyuncs.com/travel_GaoChun/aed4f410e81d4a018e3c995f1888b4589866091_carolin-zhou_1641393112.png', 0, '2022-05-23 14:03:31', '2022-05-23 14:03:31');
INSERT INTO `photos` VALUES ('1528617766343520258', '1528617766280605698', 'https://edu-19527.oss-cn-nanjing.aliyuncs.com/travel_GaoChun/776bdf0d08cf4b60bd9696fb44409f239866091_carolin-zhou_1634606834.png', 1, '2022-05-23 14:04:10', '2022-05-23 14:04:10');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_desc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_MANAGER', '超级管理员管理普通管理员');
INSERT INTO `role` VALUES (2, 'ROLE_HOTEL', '管理旅馆信息');
INSERT INTO `role` VALUES (3, 'ROLE_SCENIC', '管理景点信息');
INSERT INTO `role` VALUES (4, 'ROLE_DELICACY', '管理美食信息');
INSERT INTO `role` VALUES (5, 'ROLE_NOTICE', '管理公告信息');
INSERT INTO `role` VALUES (6, 'ROLE_ROUTE', '管理旅游路线');
INSERT INTO `role` VALUES (7, 'ROLE_NOTE', '管理游记信息');
INSERT INTO `role` VALUES (8, 'ROLE_FEEDBACK', '管理反馈信息');

-- ----------------------------
-- Table structure for route_detail
-- ----------------------------
DROP TABLE IF EXISTS `route_detail`;
CREATE TABLE `route_detail`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点id',
  `route_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线路id',
  `site` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点名称',
  `sort` tinyint(0) NOT NULL COMMENT '站点排序',
  `start_time` datetime(0) NOT NULL COMMENT '站点活动开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '站点活动结束时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点活动详情描述',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route_detail
-- ----------------------------
INSERT INTO `route_detail` VALUES ('1528329486771171330', '1528314711727235073', '高淳老街', 1, '2022-05-22 12:59:57', '2022-05-22 17:59:57', '古老美丽', 0, '2022-05-22 18:58:39', '2022-05-22 18:58:39');
INSERT INTO `route_detail` VALUES ('1528329689679015937', '1528314711727235073', '游子山', 2, '2022-05-23 08:59:57', '2022-05-23 17:59:57', '4A景区qq', 0, '2022-05-22 18:59:28', '2022-05-22 19:01:57');

-- ----------------------------
-- Table structure for scenic_spot
-- ----------------------------
DROP TABLE IF EXISTS `scenic_spot`;
CREATE TABLE `scenic_spot`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点介绍',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '景点图片',
  `popular` tinyint(0) NOT NULL COMMENT '展示优先级',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_spot
-- ----------------------------
INSERT INTO `scenic_spot` VALUES ('1528251322355183618', '游子山景区', '国家4A旅游景区', 'sqaq', 1, 0, '2022-05-22 13:48:03', '2022-05-22 14:57:35');

-- ----------------------------
-- Table structure for tourist_route
-- ----------------------------
DROP TABLE IF EXISTS `tourist_route`;
CREATE TABLE `tourist_route`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线路id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线路类型(比如一日游，两日游)',
  `title` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线路标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '线路描述',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐原因',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tourist_route
-- ----------------------------
INSERT INTO `tourist_route` VALUES ('1528314711727235073', '一日游', '快乐一日游', 'qaqqq', 'qqq', 0, '2022-05-22 17:59:57', '2022-05-22 18:02:50');

-- ----------------------------
-- Table structure for travel_note
-- ----------------------------
DROP TABLE IF EXISTS `travel_note`;
CREATE TABLE `travel_note`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游记id',
  `nickName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游客昵称',
  `mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游客邮箱',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游记内容',
  `is_deleted` tinyint(0) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_note
-- ----------------------------
INSERT INTO `travel_note` VALUES ('1528359301754765313', 'zj', 'qqq@qq.com', '奥里给', 0, '2022-05-22 20:57:08', '2022-05-22 20:57:08');
INSERT INTO `travel_note` VALUES ('1528602195874832386', 'zj', 'qqq@qq.com', '奥里给', 1, '2022-05-23 13:02:18', '2022-05-23 13:02:18');
INSERT INTO `travel_note` VALUES ('1528617602061021186', 'zj', 'qqq@qq.com', '奥里给', 0, '2022-05-23 14:03:31', '2022-05-23 14:03:31');

SET FOREIGN_KEY_CHECKS = 1;
