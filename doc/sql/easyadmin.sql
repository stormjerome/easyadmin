/*
 Navicat Premium Data Transfer

 Source Server         : home
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : easyadmin

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 24/11/2020 11:25:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_login_log`;
CREATE TABLE `sys_admin_login_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录ip地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录地址',
  `user_agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '终端信息',
  `login_time` datetime(0) DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_login_log
-- ----------------------------
INSERT INTO `sys_admin_login_log` VALUES (1, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:16:31');
INSERT INTO `sys_admin_login_log` VALUES (2, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:22:42');
INSERT INTO `sys_admin_login_log` VALUES (3, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:25:28');
INSERT INTO `sys_admin_login_log` VALUES (4, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:27:06');
INSERT INTO `sys_admin_login_log` VALUES (5, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:28:18');
INSERT INTO `sys_admin_login_log` VALUES (6, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-23 17:29:51');
INSERT INTO `sys_admin_login_log` VALUES (7, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-24 10:50:24');
INSERT INTO `sys_admin_login_log` VALUES (8, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-24 10:51:42');
INSERT INTO `sys_admin_login_log` VALUES (9, 'admin', '127.0.0.1', '内网IP', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36', '2020-11-24 11:18:42');

-- ----------------------------
-- Table structure for sys_admin_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_operate_log`;
CREATE TABLE `sys_admin_operate_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `operate_type` int(2) NOT NULL DEFAULT 0 COMMENT '操作类型{0:其他,1:新增,2:修改,3:删除,4:查询}',
  `user_id` bigint(20) NOT NULL COMMENT '操作用户ID',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作用户名',
  `operate_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作ip地址',
  `operate_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作地区',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法名',
  `request_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求类型',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求路径',
  `request_param` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
  `created_at` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `created_by` bigint(20) DEFAULT 0,
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新日期',
  `updated_by` bigint(20) DEFAULT 0,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_user`;
CREATE TABLE `sys_admin_user`  (
  `admin_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态{0:停用,1:正常}',
  `deleted_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记{0:未删,1:删除}',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登陆IP',
  `login_time` datetime(0) DEFAULT NULL COMMENT '最后登陆时间',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  `created_by` bigint(20) DEFAULT 0 COMMENT '创建者ID',
  `updated_by` bigint(20) DEFAULT 0 COMMENT '更新者ID',
  `deleted_by` bigint(20) DEFAULT 0 COMMENT '删除者ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_user
-- ----------------------------
INSERT INTO `sys_admin_user` VALUES (1, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '卡卡罗特', 1, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-11-24 11:18:42', '2020-10-17 16:16:16', '2020-11-24 11:18:42', NULL, 0, 0, 0, '超级管理');
INSERT INTO `sys_admin_user` VALUES (2, 'singlezhang', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '张三', 1, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通管理');
INSERT INTO `sys_admin_user` VALUES (3, 'jiekema2', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '杰克马2', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (4, 'xiaoli', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '小李', 3, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (5, 'jiekema3', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '杰克马3', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (6, 'yiming', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '一鸣', 3, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (7, 'lisan', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '李三', 4, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (8, 'laoqian', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '老钱', 3, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (9, 'xiaomi', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '小米', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (10, 'jiekema', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '杰克马', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (11, 'jiekema1', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '杰克马1', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-10-17 16:16:18', NULL, 0, 0, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (12, 'jiekema4', '$2a$10$Ioz0DtlGIDeS0lKwKDeRmOnqZ4rHUDHwwiTJ7VLhSFhZWfxFySvcK', '杰克马4', 5, '123456', 'admin@qq.com', NULL, 1, 0, '127.0.0.1', '2020-10-17 16:16:11', '2020-10-17 16:16:16', '2020-11-17 14:29:06', NULL, 0, 1, 0, '普通员工');
INSERT INTO `sys_admin_user` VALUES (14, 'ccess', '$2a$10$gf9V5KR0a46QAJJkp14G0efIy9LApBHKOvrNUKXCWaz7.z8JYlMZy', '张ccc', 4, '15858585858', 'admin@163.com', NULL, 1, 0, NULL, NULL, '2020-11-19 17:00:25', '2020-11-20 15:20:16', NULL, 1, 1, 0, 'ddddddd');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父级ID',
  `level_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '等级路径',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门名称',
  `sort` int(4) DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态(0:停用,1:正常)',
  `deleted_flag` tinyint(1) DEFAULT 0 COMMENT '删除状态(0:未删除,1:已删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '部门描述',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  `created_by` bigint(20) DEFAULT 0 COMMENT '创建者id',
  `updated_by` bigint(20) DEFAULT 0 COMMENT '更新者id',
  `deleted_by` bigint(20) DEFAULT 0 COMMENT '删除这id',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '0', '总公司', 1, 1, 0, '系统管理', '2020-10-17 17:08:44', '2020-11-13 10:19:55', NULL, 0, 1, 0);
INSERT INTO `sys_dept` VALUES (2, 1, '0,1', 'demo公司', 2, 1, 0, 'demo公司', '2020-10-17 17:10:17', '2020-11-13 10:19:29', NULL, 1, 1, 0);
INSERT INTO `sys_dept` VALUES (3, 2, '0,1,2', '财务部', 3, 1, 0, '财务部', '2020-10-17 17:11:35', '2020-11-14 10:37:12', NULL, 1, 1, 0);
INSERT INTO `sys_dept` VALUES (4, 2, '0,1,2', '研发部', 4, 1, 0, '研发部', '2020-10-17 17:12:01', '2020-11-14 10:37:16', NULL, 1, 1, 0);
INSERT INTO `sys_dept` VALUES (5, 2, '0,1,2', '编辑部', 12, 1, 0, '编辑部1234', '2020-10-17 17:12:31', '2020-11-14 10:36:52', NULL, 1, 1, 0);
INSERT INTO `sys_dept` VALUES (7, 4, '0,1,2,4', 'B2B研发一部', 50, 0, 1, 'B2B研发一部 开发B2B平台', '2020-11-13 16:54:33', '2020-11-14 09:41:31', '2020-11-14 09:41:31', 1, 1, 1);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典名',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典编码',
  `status` tinyint(2) DEFAULT 0 COMMENT '状态{0:停用,1:启用}',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `created_by` bigint(20) DEFAULT 0 COMMENT '创建者',
  `updated_by` bigint(20) DEFAULT 0 COMMENT '更新者',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '用户性别', 'sys_user_sex', 1, '用户性别', '2020-11-20 16:12:54', '2020-11-20 16:12:57', 1, 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `dict_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典项ID',
  `dict_id` bigint(20) DEFAULT NULL COMMENT '所属字典ID',
  `item_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项标签',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项值',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态{0:停用,1:启用}',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`dict_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '男', '1', 1, '性别:男', '2020-11-20 16:14:14', '2020-11-20 16:14:16', 1, 1);
INSERT INTO `sys_dict_item` VALUES (2, 1, '女', '2', 1, '性别:女', '2020-11-20 16:14:35', '2020-11-20 16:14:37', 1, 1);
INSERT INTO `sys_dict_item` VALUES (3, 1, '未知', '3', 1, '性别:未知', '2020-11-20 16:15:20', '2020-11-20 16:15:22', 1, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父ID',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '路由地址',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '菜单图标',
  `sort` int(4) DEFAULT 0 COMMENT '排序',
  `is_view` tinyint(1) DEFAULT 1 COMMENT '显示状态{0:隐藏,1:显示}',
  `is_link` tinyint(1) DEFAULT 0 COMMENT '是否外部链接{0:否,1:是}',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态{0:停用,1:正常}',
  `menu_type` tinyint(1) DEFAULT NULL COMMENT '菜单类型{1:菜单2:按钮}',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `deleted_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
  `created_at` datetime(0) DEFAULT NULL,
  `updated_at` datetime(0) DEFAULT NULL,
  `deleted_at` datetime(0) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT 0,
  `updated_by` bigint(20) DEFAULT 0,
  `deleted_by` bigint(20) DEFAULT 0,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统首页', 0, '/index', '', 'icon-home', 1, 1, 0, 1, 1, '系统首页', 0, '2020-10-17 16:41:36', '2020-11-06 15:20:12', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (2, '系统设置', 0, '', '', 'icon-cogs', 1, 1, 0, 1, 1, '系统设置', 0, '2020-10-17 16:41:36', '2020-10-17 16:41:36', NULL, 0, 0, 0);
INSERT INTO `sys_menu` VALUES (3, '权限管理', 0, '', '', 'icon-user', 1, 1, 0, 1, 1, '权限管理', 0, '2020-10-17 16:41:36', '2020-10-17 16:41:36', NULL, 0, 0, 0);
INSERT INTO `sys_menu` VALUES (4, '用户列表', 3, '/user/index', 'sys:user:query', '', 0, 1, 0, 1, 1, '用户列表', 0, '2020-10-17 16:41:36', '2020-10-17 16:41:36', NULL, 0, 0, 0);
INSERT INTO `sys_menu` VALUES (5, '菜单列表', 2, '/menu/index', 'sys:menu:query', '', 1, 1, 0, 1, 1, '菜单列表', 0, '2020-10-17 16:41:36', '2020-11-06 16:27:45', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (6, '部门列表', 3, '/dept/index', 'sys:dept:query', '', 0, 1, 0, 1, 1, '部门列表', 0, '2020-10-17 16:41:36', '2020-10-17 16:41:36', NULL, 0, 0, 0);
INSERT INTO `sys_menu` VALUES (7, '角色列表', 3, '/role/index', 'sys:role:query', '', 0, 1, 0, 1, 1, '角色列表', 0, '2020-10-17 16:41:36', '2020-10-30 16:49:57', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (8, 'icon列表', 2, '/icons', '', '', 0, 1, 0, 1, 1, 'icon列表', 0, '2020-10-17 16:41:36', '2020-10-17 16:41:36', NULL, 0, 0, 0);
INSERT INTO `sys_menu` VALUES (9, '测试用菜单', 2, '/icons', 'sys:menu:edit', '', 12, 0, 0, 0, 1, '测试用菜单', 1, '2020-10-17 16:41:36', '2020-11-13 10:16:40', '2020-11-13 10:16:40', 0, 1, 1);
INSERT INTO `sys_menu` VALUES (10, '菜单-新增', 5, '', 'sys:menu:add', '', 2, 0, 0, 1, 2, '菜单新增按钮', 0, '2020-10-17 16:41:36', '2020-11-06 16:28:13', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (11, '菜单-编辑', 5, '', 'sys:menu:edit', '', 3, 0, 0, 1, 2, '菜单编辑按钮', 0, '2020-10-17 16:41:36', '2020-11-12 14:15:36', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (12, '菜单-删除', 5, '', 'sys:menu:delete', '', 1, 0, 0, 1, 2, '菜单删除按钮', 0, '2020-10-17 16:41:36', '2020-11-06 16:28:18', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (18, '字典管理', 2, '/dictionary/index', 'sys:dict:query', '', 22, 1, 0, 1, 1, NULL, 0, '2020-11-10 15:57:57', '2020-11-11 17:30:07', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (19, '部门新增', 6, '/dept/add', 'sys:dept:add', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-12 15:55:57', '2020-11-12 15:55:57', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (20, '部门编辑', 6, '/dept/edit', 'sys:dept:edit', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-12 15:55:57', '2020-11-12 15:55:57', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (21, '部门删除', 6, '/dept/delete', 'sys:dept:delete', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-12 15:55:57', '2020-11-12 15:55:57', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (22, 'swagger接口文档', 2, '/swagger-ui.html', '', '', 1, 1, 1, 1, 1, '接口文档', 0, '2020-10-17 16:41:36', '2020-11-06 15:20:12', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (23, '数据源监控', 2, '/druid/index.html', '', '', 1, 1, 1, 1, 1, '数据源监控', 0, '2020-10-17 16:41:36', '2020-11-06 15:20:12', NULL, 0, 1, 0);
INSERT INTO `sys_menu` VALUES (24, '角色新增', 7, '/role/add', 'sys:role:add', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:10:12', '2020-11-14 15:10:12', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (25, '角色编辑', 7, '/role/edit', 'sys:role:edit', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:10:47', '2020-11-14 15:10:47', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (26, '角色删除', 7, '/role/delete', 'sys:role:delete', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:11:20', '2020-11-14 15:11:20', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (27, '用户新增', 4, '/user/add', 'sys:user:add', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:11:20', '2020-11-14 15:11:20', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (28, '用户编辑', 4, '/user/edit', 'sys:user:edit', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:11:20', '2020-11-14 15:11:20', NULL, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (29, '用户删除', 4, '/user/delete', 'sys:user:delete', '', 50, 1, 0, 1, 2, NULL, 0, '2020-11-14 15:11:20', '2020-11-14 15:11:20', NULL, 1, 1, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '角色标识',
  `sort` int(4) DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态{0:停用,1:正常}',
  `deleted_flag` tinyint(1) DEFAULT 0 COMMENT '删除状态{0:未删除,1:删除}',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created_at` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime(0) DEFAULT NULL COMMENT '删除时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新着ID',
  `deleted_by` bigint(20) DEFAULT NULL COMMENT '删除者ID',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', 1, 1, 0, '系统管理员', '2020-10-17 17:17:03', '2020-11-14 16:43:01', NULL, 0, 1, 0);
INSERT INTO `sys_role` VALUES (2, '普通管理员', 'common', 2, 1, 0, '普通管理员', '2020-10-17 17:17:03', '2020-11-17 11:10:26', NULL, 0, 1, 0);
INSERT INTO `sys_role` VALUES (3, '测试用户', 'test', 23, 0, 1, '普通管理员', '2020-10-17 17:17:03', '2020-11-14 16:42:55', '2020-11-14 16:42:55', 0, 1, 1);
INSERT INTO `sys_role` VALUES (4, '配置管理员', 'sysConfig', 3, 1, 0, '系统设置31', '2020-11-16 15:23:52', '2020-11-17 15:29:15', NULL, 1, 1, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 8);
INSERT INTO `sys_role_menu` VALUES (4, 18);
INSERT INTO `sys_role_menu` VALUES (4, 22);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (2, 3);
INSERT INTO `sys_user_role` VALUES (2, 4);
INSERT INTO `sys_user_role` VALUES (13, 4);
INSERT INTO `sys_user_role` VALUES (14, 4);

SET FOREIGN_KEY_CHECKS = 1;
