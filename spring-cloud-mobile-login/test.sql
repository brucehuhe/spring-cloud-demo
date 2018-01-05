/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-05 12:46:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `resUrl` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` varchar(1) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父资源',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `create_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '系统设置', '/system', '0', '0', '1', '2017-12-28 16:44:33');
INSERT INTO `resources` VALUES ('10', '删除资源', '/resources/delete', '2', '4', '10', '2017-12-28 16:44:37');
INSERT INTO `resources` VALUES ('11', '分配角色', '/users/saveUserRoles', '2', '2', '11', '2017-12-28 16:44:41');
INSERT INTO `resources` VALUES ('13', '分配权限', '/roles/saveRoleResources', '2', '3', '12', '2017-12-28 16:44:45');
INSERT INTO `resources` VALUES ('2', '用户管理', '/usersPage', '1', '1', '2', '2017-12-28 16:44:49');
INSERT INTO `resources` VALUES ('3', '角色管理', '/rolesPage', '1', '1', '3', '2017-12-28 16:44:51');
INSERT INTO `resources` VALUES ('4', '资源管理', '/resourcesPage', '1', '1', '4', '2017-12-28 16:44:54');
INSERT INTO `resources` VALUES ('5', '添加用户', '/users/add', '2', '2', '5', '2017-12-28 16:44:58');
INSERT INTO `resources` VALUES ('6', '删除用户', '/users/delete', '2', '2', '6', '2017-12-28 16:45:00');
INSERT INTO `resources` VALUES ('7', '添加角色', '/roles/add', '2', '3', '7', '2017-12-28 16:45:03');
INSERT INTO `resources` VALUES ('8', '删除角色', '/roles/delete', '2', '3', '8', '2017-12-28 16:45:06');
INSERT INTO `resources` VALUES ('9', '添加资源', '/resources/add', '2', '4', '9', '2017-12-28 16:45:08');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('071e32bb219a4d80a84ab5ae49d3b1de', '普通会员', '2018-01-01 03:20:19');
INSERT INTO `role` VALUES ('1ceca805acd04d34b1abafeb73fdc38a', '用户管理', '2018-01-02 13:27:39');
INSERT INTO `role` VALUES ('3044baa315464b9d905ce35bf9081171', '资源管理', '2018-01-02 13:27:46');
INSERT INTO `role` VALUES ('37338430d51043049e0ca751916380f6', '77', '2018-01-01 05:21:47');
INSERT INTO `role` VALUES ('39ae1bc649c7454d9be22d2732a70970', '445', '2018-01-02 10:58:11');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `roleId` varchar(32) NOT NULL,
  `resourcesId` varchar(32) NOT NULL,
  PRIMARY KEY (`roleId`,`resourcesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('2', '2');
INSERT INTO `role_resources` VALUES ('9', '9');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(33) DEFAULT NULL,
  `password` varchar(33) DEFAULT NULL,
  `enable` varchar(1) DEFAULT '1' COMMENT '是否启用:0--停用，1--启用',
  `login_time` varchar(20) DEFAULT NULL,
  `is_use` varchar(1) DEFAULT '0' COMMENT '是否已登录',
  `login_state` varchar(1) DEFAULT '0' COMMENT '1--系统登录，2--手机登录',
  `user_pic` varchar(150) DEFAULT NULL COMMENT '个人头像地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '928bfd2577490322a6e19b793691467e', '1', '2017-12-28 02:43:37', '1', '2', null);
INSERT INTO `user` VALUES ('22', 'test', '098f6bcd4621d373cade4e832627b4f6', '1', '2017-12-29 11:04:14', '0', '0', null);
INSERT INTO `user` VALUES ('3', 'bruce', 'e8315caa4eb8c2a2625d4e97dbba100a', '1', '2017-12-29 11:03:22', '0', '0', null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `con_info` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  `cn_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('0382ab4c1767421987e6bc7309dbf390', '999999', '男', '999', '9', '1', '2018-01-01 05:26:35', null, null, null);
INSERT INTO `user_info` VALUES ('25fee4e7a10b4b31a15cbd3c5f074926', '5643', '女', '54', '56', '1', '2018-01-01 05:26:25', null, null, null);
INSERT INTO `user_info` VALUES ('264be94a4c2a4ac7a526c4a6348f5f08', '000', '男', '00000', '0000000', '1', '2018-01-01 05:26:47', null, null, null);
INSERT INTO `user_info` VALUES ('410251b320e04ecba972f0da43a34799', '64536', '女', '563', '543', '1', '2018-01-01 05:26:04', null, null, null);
INSERT INTO `user_info` VALUES ('5ed69121be7240bc8bd2fdaf6ffbe1c7', '563', '男', '565', '54', '1', '2018-01-01 05:26:13', null, null, null);
INSERT INTO `user_info` VALUES ('923cb6d5ca954d28bb93b2238f09de39', '43', '男', '342', '234', '1', '2018-01-01 05:25:36', null, null, null);
INSERT INTO `user_info` VALUES ('afeece5824e34276bca30211e1dba355', '652', '女', '45643', '335', '1', '2018-01-01 05:25:46', null, null, null);
INSERT INTO `user_info` VALUES ('d8fe69f553c24e21899960013a28d87d', '563', '男', '5666664', '456', '1', '2018-01-01 05:25:55', null, null, null);
INSERT INTO `user_info` VALUES ('e0a98bbbe0834f319426aa9ef7c839c9', 'd', '男', '3241', '134', '1', '2018-01-01 04:46:16', null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('22', '2');
INSERT INTO `user_role` VALUES ('3', '2');
INSERT INTO `user_role` VALUES ('4ca2c181f7064dd1be5b6a9ca5f893cd', '071e32bb219a4d80a84ab5ae49d3b1de');
INSERT INTO `user_role` VALUES ('196c6bec3e0e40db92275669eefa15ee', '071e32bb219a4d80a84ab5ae49d3b1de');
INSERT INTO `user_role` VALUES ('50f1feae549a42a7be778cdf74c64d19', '071e32bb219a4d80a84ab5ae49d3b1de');

-- ----------------------------
-- Procedure structure for init_shiro_demo
-- ----------------------------
DROP PROCEDURE IF EXISTS `init_shiro_demo`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `init_shiro_demo`()
BEGIN	
/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.6.16-log : Database - 
*********************************************************************
*/
/*表结构插入*/
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*Table structure for table `u_role` */
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*Table structure for table `u_role_permission` */
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*Table structure for table `u_user` */
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*Table structure for table `u_user_role` */
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.6.16-log : Database - i_wenyiba_com
*********************************************************************
*/
/*所有的表数据插入*/
/*Data for the table `u_permission` */
insert  into `u_permission`(`id`,`url`,`name`) values (4,'/permission/index.shtml','权限列表'),(6,'/permission/addPermission.shtml','权限添加'),(7,'/permission/deletePermissionById.shtml','权限删除'),(8,'/member/list.shtml','用户列表'),(9,'/member/online.shtml','在线用户'),(10,'/member/changeSessionStatus.shtml','用户Session踢出'),(11,'/member/forbidUserById.shtml','用户激活&禁止'),(12,'/member/deleteUserById.shtml','用户删除'),(13,'/permission/addPermission2Role.shtml','权限分配'),(14,'/role/clearRoleByUserIds.shtml','用户角色分配清空'),(15,'/role/addRole2User.shtml','角色分配保存'),(16,'/role/deleteRoleById.shtml','角色列表删除'),(17,'/role/addRole.shtml','角色列表添加'),(18,'/role/index.shtml','角色列表'),(19,'/permission/allocation.shtml','权限分配'),(20,'/role/allocation.shtml','角色分配');
/*Data for the table `u_role` */
insert  into `u_role`(`id`,`name`,`type`) values (1,'系统管理员','888888'),(3,'权限角色','100003'),(4,'用户中心','100002');
/*Data for the table `u_role_permission` */
insert  into `u_role_permission`(`rid`,`pid`) values (4,8),(4,9),(4,10),(4,11),(4,12),(3,4),(3,6),(3,7),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(1,4),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);
/*Data for the table `u_user` */
insert  into `u_user`(`id`,`nickname`,`email`,`pswd`,`create_time`,`last_login_time`,`status`) values (1,'管理员','admin','9c3250081c7b1f5c6cbb8096e3e1cd04','2016-06-16 11:15:33','2016-06-16 11:24:10',1),(11,'soso','8446666@qq.com','d57ffbe486910dd5b26d0167d034f9ad','2016-05-26 20:50:54','2016-06-16 11:24:35',1),(12,'8446666','8446666','4afdc875a67a55528c224ce088be2ab8','2016-05-27 22:34:19','2016-06-15 17:03:16',1);
/*Data for the table `u_user_role` */
insert  into `u_user_role`(`uid`,`rid`) values (12,4),(11,3),(11,4),(1,1);
   
    END
;;
DELIMITER ;
