/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80403
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80403
 File Encoding         : 65001

 Date: 03/01/2025 18:04:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系统配置名称',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置编码',
  `value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置值',
  `unit` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位',
  `rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'value值的正则规则',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '1-开启，0-关闭',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '记录创建者ID',
  `create_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '记录创建时间',
  `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后修改人ID',
  `last_modified_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人名称',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后修改人名称',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除   1:已删除   0:未删除',
  `sort` tinyint NOT NULL COMMENT '排序值',
  `type` tinyint NULL DEFAULT NULL COMMENT '分类编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES (1, '系统名称', 'systemName', '博客管理系统', NULL, '', 1, '1', '2024-12-10 17:18:05.000000', '1', '2024-12-10 17:18:13.000000', '张怀伟', '张怀伟', 0, 6, 1);
INSERT INTO `t_sys_config` VALUES (2, '系统图标', 'systemImage', '87430a9be036411d842897f5ddc11b52', NULL, '', 1, '1', '2024-12-10 17:21:47.000000', '1', '2024-12-10 17:21:50.000000', '张怀伟', '张怀伟', 0, 7, 1);
INSERT INTO `t_sys_config` VALUES (3, '登录验证码', 'verificationCode', 'true', NULL, '', 1, '1', '2024-12-10 17:29:10.000000', '1', '2024-12-10 17:29:02.000000', '张怀伟', '张怀伟', 0, 1, 0);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键UUID/菜单ID',
  `pid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由标识路径，第三方资源完整路径',
  `cmp_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端组件URL',
  `icons` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建者ID',
  `create_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '记录创建时间',
  `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人ID',
  `last_modified_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除   1:已删除   0:未删除',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序值',
  `type` tinyint NOT NULL COMMENT ' -1: 菜单 0: 菜单分组 1: 按钮 2：虚拟目录 3：外部资源',
  `source` tinyint NULL DEFAULT NULL COMMENT '资源来源，区别是本地资源，还是外部资源 （0代表本地；1代表外部）',
  `classify` tinyint NULL DEFAULT NULL COMMENT '分组：0、业务资源菜单；1、系统资源菜单',
  `hidden` tinyint NULL DEFAULT NULL COMMENT '是否隐藏路由0为隐藏；1为显示',
  `state` tinyint NULL DEFAULT NULL COMMENT '数据状态0为禁用，1为启用',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `is_lock` tinyint NULL DEFAULT NULL COMMENT '是否上锁: 0未上锁，1上锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('0637a3ef690e6776dd954fe6b4087e93', '12be5f7ad1c8060f641ae77d96f78643', '角色查询', 'sys_role_page', '', NULL, '', '1', '2024-12-31 10:19:23.917301', '1', '2024-12-31 10:19:23.917301', 'zhw', 'zhw', 0, NULL, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('08d44314129dd6fcc18e30db859e72c4', '12be5f7ad1c8060f641ae77d96f78643', '角色关联资源', 'sys_role_roleAndMenu', NULL, NULL, '', '1', '2024-12-31 10:19:24.605642', '1', '2024-12-31 10:19:24.605642', 'zhw', 'zhw', 0, 6, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('0e9a6b9e9a15e5a08d0d92cc742e6742', 'asdada12312312dazxzfhjjhgfdadajqq', '资源管理', 'sys_menu', '/permission-management/resource', '/Permission/ResourceManagement/index', 'Apple', '1', '2024-12-31 10:19:25.267153', '1', '2024-12-31 10:19:25.267153', '张怀伟', '张怀伟', 0, 3, -1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('12be5f7ad1c8060f641ae77d96f78643', 'asdada12312312dazxzfhjjhgfdadajqq', '角色管理', 'sys_role', '/permission-management/roles', '/RoleManagement/index', 'ColdDrink', '1', '2024-12-31 10:19:26.197652', '1', '2024-12-31 10:19:26.197652', '张怀伟', '张怀伟', 0, 4, -1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('13248758c85315a0b2bc587d200858e7', '4df4a6ddf45927eca92e6ff08f00e4e7', '用户修改', 'sys_user_update', NULL, NULL, NULL, '1', '2024-12-31 10:19:27.146760', '1', '2024-12-31 10:19:27.146760', 'zhw', 'zhw', 0, 2, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('1c5daf4e7e3de68bf231866b1de4d881', '0e9a6b9e9a15e5a08d0d92cc742e6742', '资源列表查询', 'sys_menu_tree', NULL, NULL, '', '1', '2024-12-31 10:19:27.939903', '1', '2024-12-31 10:19:27.939903', 'zhw', 'zhw', 0, 1, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('1f30dc81ca333cbb979227d188ea7849', '12be5f7ad1c8060f641ae77d96f78643', '角色删除', 'sys_role_delete', NULL, NULL, '', '1', '2024-12-31 10:19:28.744782', '1', '2024-12-31 10:19:28.744782', 'zhw', 'zhw', 0, 5, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('2b183e15a717efdb2e4cc42f150dcc75', '12be5f7ad1c8060f641ae77d96f78643', '角色修改', 'sys_role_update', NULL, NULL, '', '1', '2024-12-31 10:19:30.080281', '1', '2024-12-31 10:19:30.080281', 'zhw', 'zhw', 0, 4, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('4df4a6ddf45927eca92e6ff08f00e4e7', 'asdada12312312dazxzfhjjhgfdadajqq', '用户管理', 'sys_user', '/permission-management/users', '/Permission/UserManagement/index', 'HotWater', '1', '2024-12-31 10:19:30.931494', '1', '2024-12-31 10:19:30.931494', '张怀伟', '张怀伟', 0, 1, -1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('59e383621bd943d087c2ecc3fd96e118', '-1', '业务资源', 'blog', NULL, NULL, NULL, '1', '2024-12-31 10:19:31.835222', '1', '2024-12-31 10:19:31.835222', '张怀伟', '张怀伟', 0, 0, 0, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('74ef73e1bba2892fd0b328622bbc57da', '4df4a6ddf45927eca92e6ff08f00e4e7', '用户新增', 'sys_user_save', NULL, NULL, NULL, '1', '2024-12-31 10:19:32.828843', '1', '2024-12-31 10:19:32.828843', 'zhw', 'zhw', 0, 3, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('79c2e5094ee0ec945ef8430cb94355e7', '4df4a6ddf45927eca92e6ff08f00e4e7', '用户删除', 'sys_user_delete', NULL, NULL, NULL, '1', '2024-12-31 10:19:34.023362', '1', '2024-12-31 10:19:34.023362', 'zhw', 'zhw', 0, 4, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('87506228dabffa6d12597d40196f616b', '4df4a6ddf45927eca92e6ff08f00e4e7', '设置角色', 'sys_user_userAndRole', NULL, NULL, NULL, '1', '2024-12-31 10:19:34.903162', '1', '2024-12-31 10:19:34.903162', 'zhw', 'zhw', 0, 5, 1, 0, 1, 1, 1, '', 0);
INSERT INTO `t_sys_menu` VALUES ('8d7cb8583c22349c3a975d29f3de3998', '0e9a6b9e9a15e5a08d0d92cc742e6742', '资源新增', 'sys_menu_save', NULL, NULL, '', '1', '2024-12-31 10:19:35.718523', '1', '2024-12-31 10:19:35.718523', 'zhw', 'zhw', 0, 2, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('8f4ca131411046a8082722db4b8eecbb', '0e9a6b9e9a15e5a08d0d92cc742e6742', '资源修改', 'sys_menu_update', NULL, NULL, '', '1', '2024-12-31 10:19:36.566897', '1', '2024-12-31 10:19:36.566897', 'zhw', 'zhw', 0, 3, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('9a7537414ec0553251c22aa65c5bce29', '12be5f7ad1c8060f641ae77d96f78643', '角色信息导出', 'sys_role_export', NULL, NULL, '', '1', '2024-12-31 10:19:37.468346', '1', '2024-12-31 10:19:37.468346', 'zhw', 'zhw', 0, 8, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('asdada12312312dazxzfhjjhgfdadajqq', 'de28f19098eb4037b3a24ff4615d5dce', '系统管理', 'sys_systemManagement', '/systemManagement', NULL, 'Menu', '1', '2024-12-31 10:19:38.850483', '1', '2024-12-31 10:19:38.850483', '张怀伟', '张怀伟', 0, 3, 0, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('cc80b20e5989438dad96d995926ffc85', '0e9a6b9e9a15e5a08d0d92cc742e6742', '资源删除', 'sys_menu_delete', NULL, NULL, '', '1', '2024-12-31 10:19:40.372499', '1', '2024-12-31 10:19:40.372499', 'zhw', 'zhw', 0, 4, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('de1931dad9763616bec2f795e8c7c3a6', '4df4a6ddf45927eca92e6ff08f00e4e7', '用户查询', 'sys_user_pageUser', NULL, NULL, '', '1', '2024-12-31 10:19:41.217319', '1', '2024-12-31 10:19:41.217319', 'zhw', 'zhw', 0, 1, 1, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('de28f19098eb4037b3a24ff4615d5dce', '-1', '系统资源', 'sys', NULL, NULL, NULL, '1', '2024-12-31 10:19:42.001835', '1', '2024-12-31 10:19:42.001835', '张怀伟', '张怀伟', 0, 1, 0, 0, 1, 1, 1, NULL, 0);
INSERT INTO `t_sys_menu` VALUES ('f1e7f05f89b77c077d469b2a673197e4', '12be5f7ad1c8060f641ae77d96f78643', '角色新增', 'sys_role_save', NULL, NULL, '', '1', '2024-12-31 10:19:42.847090', '1', '2024-12-31 10:19:42.847090', 'zhw', 'zhw', 0, 3, 1, 0, 1, 1, 1, NULL, 0);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID   (主键UUID)',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_type` tinyint NOT NULL COMMENT '角色类型，0角色，1分组',
  `state` tinyint NULL DEFAULT 1 COMMENT '开启标识 （0为禁用，1为启用）',
  `classify` tinyint NULL DEFAULT 0 COMMENT '角色分组类型（只有自定义分组才可编辑）\r\n0：博客管理角色\r\n1：博客pc客户端角色\r\n2：博客app客户端角色\r\n3：博客小程序客户端角色\r\n4：自定义角色',
  `sort` tinyint NOT NULL COMMENT '排序值',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人ID',
  `last_modified_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父节点id',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除   0:未删除 1:已删除   '
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '超级管理员', 'superAdmin', '系统中的超级管理员', 0, 1, 0, 1, '1', '2024-12-30 16:00:51', 'zhw', '1', '2024-12-30 16:01:12', 'zhw', NULL, 0);
INSERT INTO `t_sys_role` VALUES ('8a1fbdc0ad2d90fc97712eb7cc09a4d0', '普通管理员', 'normalAdmin', '系统中的普通管理员', 0, 1, 0, 2, '1', '2024-12-30 16:05:26', 'zhw', '1', '2024-12-30 16:05:35', 'zhw', NULL, 0);
INSERT INTO `t_sys_role` VALUES ('2c2b676407e49c809bfad1aa89e9dbb4', '游客', 'notAdmin', '游客', 0, 1, 0, 3, '1', '2024-12-30 16:07:42', 'zhw', '1', '2024-12-30 16:07:51', 'zhw', NULL, 0);

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人ID',
  `last_modified_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
  `operate` tinyint NOT NULL DEFAULT 0 COMMENT '操作权限，0操作，1只读，2管理',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除   1:已删除   0:未删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('2c2b676407e49c809bfad1aa89e9dbb4', '4df4a6ddf45927eca92e6ff08f00e4e7', '1', '2024-12-30 17:45:09', '张怀伟', '1', '2024-12-30 17:45:29', '张怀伟', 1, 0);
INSERT INTO `t_sys_role_menu` VALUES ('2c2b676407e49c809bfad1aa89e9dbb4', 'de1931dad9763616bec2f795e8c7c3a6', '1', '2024-12-30 17:47:50', '张怀伟', '1', '2024-12-30 17:48:41', '张怀伟', 1, 0);
INSERT INTO `t_sys_role_menu` VALUES ('8a1fbdc0ad2d90fc97712eb7cc09a4d0', '59e383621bd943d087c2ecc3fd96e118', '1', '2024-12-30 17:51:09', '张怀伟', '1', '2024-12-30 17:51:22', '张怀伟', 0, 0);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员ID、主键ID',
  `login_id` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像【存放seaweedfs图片信息】',
  `is_admin` tinyint NULL DEFAULT 0 COMMENT '是否为超级管理员     1:是   0:否 ',
  `state` tinyint NOT NULL DEFAULT 1 COMMENT '1-启动，0-禁用',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建者ID',
  `create_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '记录创建时间',
  `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人ID',
  `last_modified_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除   1:已删除   0:未删除',
  `source` tinyint NULL DEFAULT 0 COMMENT '登录用户来源 0：后台管理员；1：客户端用户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_id`(`login_id` ASC) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统（管理端和客户端）用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'zhw', '张怀伟', '$2a$10$AszfcvqfTFVtgt2GsM9OwemUGrikzdd1rP3iZmxbh0N1m8eFznIiq', '15592320353', NULL, 1, 1, '1', '2024-12-30 17:59:56.884346', '1', '2024-12-30 17:59:56.884346', '张怀伟', '张怀伟', 0, 0);
INSERT INTO `t_sys_user` VALUES ('3b4f3f8ce018f45178d0bf7eb661dcc9', 'test', '游客', '$10$AszfcvqfTFVtgt2GsM9OwemUGrikzdd1rP3iZmxbh0N1m8eFznIiq', NULL, NULL, 0, 1, '1', '2024-12-30 17:59:57.537894', '1', '2024-12-30 17:59:57.537894', '张怀伟', '张怀伟', 0, 0);
INSERT INTO `t_sys_user` VALUES ('686e8115f9a130ab37178bcf9b34ee14', 'normal', '普通管理员', '$10$AszfcvqfTFVtgt2GsM9OwemUGrikzdd1rP3iZmxbh0N1m8eFznIiq', NULL, NULL, 0, 1, '1', '2024-12-30 17:59:59.143474', '1', '2024-12-30 17:59:59.143474', '张怀伟', '张怀伟', 0, 0);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录创建时间',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录创建人',
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人ID',
  `last_modified_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除   1:已删除   0:未删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '1', '1', '2024-12-30 16:09:03', '张怀伟', '1', '2024-12-30 16:09:13', '张怀伟', 0);
INSERT INTO `t_sys_user_role` VALUES ('3b4f3f8ce018f45178d0bf7eb661dcc9', '2c2b676407e49c809bfad1aa89e9dbb4', '1', '2024-12-30 16:09:52', '张怀伟', '1', '2024-12-30 16:10:00', '张怀伟', 0);
INSERT INTO `t_sys_user_role` VALUES ('686e8115f9a130ab37178bcf9b34ee14', '8a1fbdc0ad2d90fc97712eb7cc09a4d0', '1', '2024-12-30 16:11:00', '张怀伟', '1', '2024-12-30 16:11:06', '张怀伟', 0);

SET FOREIGN_KEY_CHECKS = 1;
