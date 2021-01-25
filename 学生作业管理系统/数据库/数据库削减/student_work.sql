/*
 Navicat Premium Data Transfer

 Source Server         : Alian
 Source Server Type    : MySQL
 Source Server Version : 50022
 Source Host           : localhost:3306
 Source Schema         : student_work

 Target Server Type    : MySQL
 Target Server Version : 50022
 File Encoding         : 65001

 Date: 21/08/2019 10:41:25
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `semester_id` int(10) NULL DEFAULT NULL,
  `course_id` int(10) NULL DEFAULT NULL,
  `teacher_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`),
  INDEX `semester_id` USING BTREE(`semester_id`),
  INDEX `course_id` USING BTREE(`course_id`),
  INDEX `teacher_id` USING BTREE(`teacher_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `class_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'InnoDB free: 11264 kB; (`course_id`) REFER `student_work/course`(`id`) ON UPDATE' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, 1, 1, 1);
INSERT INTO `class` VALUES (2, 1, 2, 1);
INSERT INTO `class` VALUES (3, 1, 3, 2);
INSERT INTO `class` VALUES (4, 1, 4, 2);

-- ----------------------------
-- Table structure for classstudent
-- ----------------------------
DROP TABLE IF EXISTS `classstudent`;
CREATE TABLE `classstudent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NULL DEFAULT NULL,
  `Student_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`),
  INDEX `class_id` USING BTREE(`class_id`),
  INDEX `Student_id` USING BTREE(`Student_id`),
  CONSTRAINT `classstudent_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `classstudent_ibfk_2` FOREIGN KEY (`Student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'InnoDB free: 11264 kB; (`class_id`) REFER `student_work/class`(`id`) ON UPDATE C' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classstudent
-- ----------------------------
INSERT INTO `classstudent` VALUES (1, 1, 1);
INSERT INTO `classstudent` VALUES (2, 1, 2);
INSERT INTO `classstudent` VALUES (3, 1, 3);
INSERT INTO `classstudent` VALUES (4, 1, 4);
INSERT INTO `classstudent` VALUES (5, 2, 1);
INSERT INTO `classstudent` VALUES (6, 2, 2);
INSERT INTO `classstudent` VALUES (7, 2, 3);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `courseid` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `score` int(10) NOT NULL ,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '001', '大学英语', 3);
INSERT INTO `course` VALUES (2, '002', '高等数学', 3);
INSERT INTO `course` VALUES (3, '003', 'c语言程序设计', 6);
INSERT INTO `course` VALUES (4, '004', '数据结构与算法', 5);
INSERT INTO `course` VALUES (5, '005', 'java程序设计', 6);

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NULL DEFAULT NULL,
  `stime` datetime NULL DEFAULT NULL,
  `etime` datetime NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL,
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `state` int(255) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`),
  INDEX `class_id` USING BTREE(`class_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'InnoDB free: 11264 kB; (`class_id`) REFER `student_work/class`(`id`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (2, 1, '2018-12-30 00:00:00', NULL, 2, '21566292314269.doc', '作业修噶', 1);
INSERT INTO `homework` VALUES (79, 1, '2018-12-05 00:00:00', '2018-12-17 00:00:00', 2, '11566186848699.doc', '什么是数值？\r\n\r\n这部分对于程序员来说可以直接跳过的。\r\n\r\n正如你知道的，来自于每天所使用的数字，比如16，0.5等这些用语就是 数值 的示例，也就是数字。在计算机语言中， 这些用语有着更广泛的含义，比如数值并不一定是数字类型值，比如面这个数据模型：', 1);
INSERT INTO `homework` VALUES (80, 1, '2018-12-14 00:00:00', '2018-12-26 00:00:00', 2, '11566272614518.txt', '在前端开发的过程中，总是遇到这种情况，有时候写了一些样式，在用浏览器做测试的时候，样式就是死活不出来。在我手速8000的条件下时断时续的出来样式，百度了好久也没有什么具体的答复。\r\n\r\n个人猜想：应该适合浏览器的缓存有关，或者和js代码引入得到位置有关，我一般是在<head>标签内引入的，还有一些则写在<body>底部，然而就是这样，我写的样式时断时续的出现。（苦恼。。。）\r\n\r\n就在前几天逛贴吧的时候看到一条评论，说在公司上班的时候，写外部js调入的代码写在</body>前面。开始没怎么注意，现在遇到这样的情况，死活不出来的那种，试了一下果然有用。\r\n\r\n自定义js文件调入位置：\r\n ———————————————— \r\n版权声明：本文为CSDN博主「自传难写啊」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。\r\n原文链接：https://blog.csdn.net/qq_41593124/article/details/99707211', 1);
INSERT INTO `homework` VALUES (81, 1, '2018-12-30 00:00:00', '2018-12-10 00:00:00', 2, '11566291071651.txt', '作业', 1);
INSERT INTO `homework` VALUES (82, 1, '2018-12-20 00:00:00', '2018-12-19 00:00:00', 2, '11566291413108.txt', '作业', 1);
INSERT INTO `homework` VALUES (83, 1, '2018-12-12 00:00:00', '2018-12-18 00:00:00', 2, '11566291674801.doc', '作业', 1);
INSERT INTO `homework` VALUES (84, 1, '2018-12-12 00:00:00', '2018-12-19 00:00:00', 2, '11566291851003.doc', '作业', 1);
INSERT INTO `homework` VALUES (85, 1, '2018-12-05 00:00:00', '2018-12-12 00:00:00', 2, '11566291976328.doc', '作业', 1);
INSERT INTO `homework` VALUES (86, 1, '2018-12-12 00:00:00', '2018-12-17 00:00:00', 2, '11566292092055.doc', '作业', 1);
INSERT INTO `homework` VALUES (87, 1, '2018-12-13 00:00:00', '2018-12-26 00:00:00', 0, NULL, '作业', 1);

-- ----------------------------
-- Table structure for homeworkinfo
-- ----------------------------
DROP TABLE IF EXISTS `homeworkinfo`;
CREATE TABLE `homeworkinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homework_id` int(11) NULL DEFAULT NULL,
  `student_id` int(11) NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL,
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学生附加信息',
  `piyu` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `filescore` int(255) NULL DEFAULT NULL,
  `textscore` int(255) NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '作业分数',
  PRIMARY KEY USING BTREE (`id`),
  INDEX `homework_id` USING BTREE(`homework_id`),
  INDEX `student_id` USING BTREE(`student_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of homeworkinfo
-- ----------------------------
INSERT INTO `homeworkinfo` VALUES (1, 2, 1, 1, '1212', '1', '题目有点难，选择题太多', '努力', 22, 60, '82');
INSERT INTO `homeworkinfo` VALUES (2, 2, 2, 1, '12123', '1213', '建议还是填空题号', '加油', 10, 12, '22');
INSERT INTO `homeworkinfo` VALUES (3, 1, 1, 1, '12', '12', '还是比较喜欢选择题', '1', NULL, NULL, '99');
INSERT INTO `homeworkinfo` VALUES (4, 79, 1, 2, '1566273034651.png', '啦啦啦', '好难哦', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (5, 80, 1, 2, '1566277769234.PNG', '', '', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (6, 80, 1, 2, '1566277771364.PNG', '', '', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (7, 80, 1, 2, '1566277773663.PNG', '', '', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (10, 2, 1, 2, '1566292950668.PNG', '', '作业有点难度', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (11, 2, 1, 2, '1566292953364.PNG', '', '作业有点难度', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (12, 2, 1, 2, '1566292955421.PNG', '', '作业有点难度', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (13, 2, 1, 2, '1566292957594.PNG', '', '作业有点难度', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (14, 2, 1, 2, '1566292959495.PNG', '', '作业有点难度', NULL, NULL, NULL, NULL);
INSERT INTO `homeworkinfo` VALUES (15, 80, 1, 2, '1566293252796.doc', '', '不错', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for meaasge
-- ----------------------------
DROP TABLE IF EXISTS `meaasge`;
CREATE TABLE `meaasge`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendid` int(11) NULL DEFAULT NULL,
  `receid` int(11) NULL DEFAULT NULL,
  `sendstate` int(255) NULL DEFAULT NULL,
  `recestate` int(255) NULL DEFAULT NULL,
  `state` int(255) NULL DEFAULT NULL,
  `info` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 371 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of meaasge
-- ----------------------------
INSERT INTO `meaasge` VALUES (15, 1, 3, 1, 2, 1, '2班:王渊老师:发布:4作业');
INSERT INTO `meaasge` VALUES (17, 1, 2, 1, 2, 1, '2班:王渊老师:发布:5作业');
INSERT INTO `meaasge` VALUES (18, 1, 3, 1, 2, 1, '2班:王渊老师:发布:5作业');
INSERT INTO `meaasge` VALUES (20, 1, 2, 1, 2, 1, '1班:王渊老师:发布:6作业');
INSERT INTO `meaasge` VALUES (21, 1, 3, 1, 2, 1, '1班:王渊老师:发布:6作业');
INSERT INTO `meaasge` VALUES (22, 1, 4, 1, 2, 1, '1班:王渊老师:发布:6作业');
INSERT INTO `meaasge` VALUES (24, 1, 2, 1, 2, 1, '1班:王渊老师:发布:7作业');
INSERT INTO `meaasge` VALUES (25, 1, 3, 1, 2, 1, '1班:王渊老师:发布:7作业');
INSERT INTO `meaasge` VALUES (26, 1, 4, 1, 2, 1, '1班:王渊老师:发布:7作业');
INSERT INTO `meaasge` VALUES (28, 1, 2, 1, 2, 1, '1班:王渊老师:发布:8作业');
INSERT INTO `meaasge` VALUES (29, 1, 3, 1, 2, 1, '1班:王渊老师:发布:8作业');
INSERT INTO `meaasge` VALUES (30, 1, 4, 1, 2, 1, '1班:王渊老师:发布:8作业');
INSERT INTO `meaasge` VALUES (32, 1, 2, 1, 2, 1, '1班:王渊老师:发布:9作业');
INSERT INTO `meaasge` VALUES (33, 1, 3, 1, 2, 1, '1班:王渊老师:发布:9作业');
INSERT INTO `meaasge` VALUES (34, 1, 4, 1, 2, 1, '1班:王渊老师:发布:9作业');
INSERT INTO `meaasge` VALUES (36, 1, 2, 1, 2, 1, '1班:王渊老师:发布:11作业');
INSERT INTO `meaasge` VALUES (37, 1, 3, 1, 2, 1, '1班:王渊老师:发布:11作业');
INSERT INTO `meaasge` VALUES (39, 1, 4, 1, 2, 1, '1班:王渊老师:发布:11作业');
INSERT INTO `meaasge` VALUES (40, 1, 2, 1, 2, 1, '1班:王渊老师:发布:10作业');
INSERT INTO `meaasge` VALUES (41, 1, 3, 1, 2, 1, '1班:王渊老师:发布:10作业');
INSERT INTO `meaasge` VALUES (42, 1, 4, 1, 2, 1, '1班:王渊老师:发布:10作业');
INSERT INTO `meaasge` VALUES (44, 1, 2, 1, 2, 1, '1班:王渊老师:发布:12作业');
INSERT INTO `meaasge` VALUES (45, 1, 3, 1, 2, 1, '1班:王渊老师:发布:12作业');
INSERT INTO `meaasge` VALUES (46, 1, 4, 1, 2, 1, '1班:王渊老师:发布:12作业');
INSERT INTO `meaasge` VALUES (48, 1, 2, 1, 2, 1, '1班:王渊老师:发布:13作业');
INSERT INTO `meaasge` VALUES (49, 1, 3, 1, 2, 1, '1班:王渊老师:发布:13作业');
INSERT INTO `meaasge` VALUES (50, 1, 4, 1, 2, 1, '1班:王渊老师:发布:13作业');
INSERT INTO `meaasge` VALUES (52, 1, 2, 1, 2, 1, '1班:王渊老师:发布:14作业');
INSERT INTO `meaasge` VALUES (53, 1, 3, 1, 2, 1, '1班:王渊老师:发布:14作业');
INSERT INTO `meaasge` VALUES (54, 1, 4, 1, 2, 1, '1班:王渊老师:发布:14作业');
INSERT INTO `meaasge` VALUES (56, 1, 2, 1, 2, 1, '1班:王渊老师:发布:15作业');
INSERT INTO `meaasge` VALUES (57, 1, 3, 1, 2, 1, '1班:王渊老师:发布:15作业');
INSERT INTO `meaasge` VALUES (58, 1, 4, 1, 2, 1, '1班:王渊老师:发布:15作业');
INSERT INTO `meaasge` VALUES (60, 1, 2, 1, 2, 1, '1班:王渊老师:发布:16作业');
INSERT INTO `meaasge` VALUES (61, 1, 3, 1, 2, 1, '1班:王渊老师:发布:16作业');
INSERT INTO `meaasge` VALUES (62, 1, 4, 1, 2, 1, '1班:王渊老师:发布:16作业');
INSERT INTO `meaasge` VALUES (64, 1, 2, 1, 2, 1, '1班:王渊老师:发布:17作业');
INSERT INTO `meaasge` VALUES (65, 1, 3, 1, 2, 1, '1班:王渊老师:发布:17作业');
INSERT INTO `meaasge` VALUES (66, 1, 4, 1, 2, 1, '1班:王渊老师:发布:17作业');
INSERT INTO `meaasge` VALUES (68, 1, 2, 1, 2, 1, '1班:王渊老师:发布:18作业');
INSERT INTO `meaasge` VALUES (69, 1, 3, 1, 2, 1, '1班:王渊老师:发布:18作业');
INSERT INTO `meaasge` VALUES (70, 1, 4, 1, 2, 1, '1班:王渊老师:发布:18作业');
INSERT INTO `meaasge` VALUES (72, 1, 2, 1, 2, 1, '1班:王渊老师:发布:19作业');
INSERT INTO `meaasge` VALUES (73, 1, 3, 1, 2, 1, '1班:王渊老师:发布:19作业');
INSERT INTO `meaasge` VALUES (74, 1, 4, 1, 2, 1, '1班:王渊老师:发布:19作业');
INSERT INTO `meaasge` VALUES (76, 1, 2, 1, 2, 1, '1班:王渊老师:发布:20作业');
INSERT INTO `meaasge` VALUES (77, 1, 3, 1, 2, 1, '1班:王渊老师:发布:20作业');
INSERT INTO `meaasge` VALUES (78, 1, 4, 1, 2, 1, '1班:王渊老师:发布:20作业');
INSERT INTO `meaasge` VALUES (82, 1, 4, 1, 2, 1, '1班:王渊老师:发布:21作业');
INSERT INTO `meaasge` VALUES (84, 1, 2, 1, 2, 1, '1班:王渊老师:发布:22作业');
INSERT INTO `meaasge` VALUES (85, 1, 3, 1, 2, 1, '1班:王渊老师:发布:22作业');
INSERT INTO `meaasge` VALUES (86, 1, 4, 1, 2, 1, '1班:王渊老师:发布:22作业');
INSERT INTO `meaasge` VALUES (88, 1, 2, 1, 2, 1, '1班:王渊老师:发布:23作业');
INSERT INTO `meaasge` VALUES (89, 1, 3, 1, 2, 1, '1班:王渊老师:发布:23作业');
INSERT INTO `meaasge` VALUES (90, 1, 4, 1, 2, 1, '1班:王渊老师:发布:23作业');
INSERT INTO `meaasge` VALUES (92, 1, 2, 1, 2, 1, '1班:王渊老师:发布:24作业');
INSERT INTO `meaasge` VALUES (93, 1, 3, 1, 2, 1, '1班:王渊老师:发布:24作业');
INSERT INTO `meaasge` VALUES (94, 1, 4, 1, 2, 1, '1班:王渊老师:发布:24作业');
INSERT INTO `meaasge` VALUES (96, 1, 2, 1, 2, 1, '1班:王渊老师:发布:25作业');
INSERT INTO `meaasge` VALUES (97, 1, 3, 1, 2, 1, '1班:王渊老师:发布:25作业');
INSERT INTO `meaasge` VALUES (98, 1, 4, 1, 2, 1, '1班:王渊老师:发布:25作业');
INSERT INTO `meaasge` VALUES (100, 1, 2, 1, 2, 1, '1班:王渊老师:发布:26作业');
INSERT INTO `meaasge` VALUES (101, 1, 3, 1, 2, 1, '1班:王渊老师:发布:26作业');
INSERT INTO `meaasge` VALUES (102, 1, 4, 1, 2, 1, '1班:王渊老师:发布:26作业');
INSERT INTO `meaasge` VALUES (104, 1, 2, 1, 2, 1, '1班:王渊老师:发布:27作业');
INSERT INTO `meaasge` VALUES (105, 1, 3, 1, 2, 1, '1班:王渊老师:发布:27作业');
INSERT INTO `meaasge` VALUES (106, 1, 4, 1, 2, 1, '1班:王渊老师:发布:27作业');
INSERT INTO `meaasge` VALUES (108, 1, 2, 1, 2, 1, '1班:王渊老师:发布:28作业');
INSERT INTO `meaasge` VALUES (109, 1, 3, 1, 2, 1, '1班:王渊老师:发布:28作业');
INSERT INTO `meaasge` VALUES (110, 1, 4, 1, 2, 1, '1班:王渊老师:发布:28作业');
INSERT INTO `meaasge` VALUES (112, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:29作业');
INSERT INTO `meaasge` VALUES (113, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:29作业');
INSERT INTO `meaasge` VALUES (114, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:29作业');
INSERT INTO `meaasge` VALUES (116, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:30作业');
INSERT INTO `meaasge` VALUES (117, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:30作业');
INSERT INTO `meaasge` VALUES (118, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:30作业');
INSERT INTO `meaasge` VALUES (120, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:31作业');
INSERT INTO `meaasge` VALUES (121, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:31作业');
INSERT INTO `meaasge` VALUES (122, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:31作业');
INSERT INTO `meaasge` VALUES (124, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:32作业');
INSERT INTO `meaasge` VALUES (125, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:32作业');
INSERT INTO `meaasge` VALUES (126, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:32作业');
INSERT INTO `meaasge` VALUES (128, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:33作业');
INSERT INTO `meaasge` VALUES (129, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:33作业');
INSERT INTO `meaasge` VALUES (130, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:33作业');
INSERT INTO `meaasge` VALUES (132, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:34作业');
INSERT INTO `meaasge` VALUES (133, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:34作业');
INSERT INTO `meaasge` VALUES (134, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:34作业');
INSERT INTO `meaasge` VALUES (136, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:35作业');
INSERT INTO `meaasge` VALUES (137, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:35作业');
INSERT INTO `meaasge` VALUES (138, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:35作业');
INSERT INTO `meaasge` VALUES (140, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:36作业');
INSERT INTO `meaasge` VALUES (141, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:36作业');
INSERT INTO `meaasge` VALUES (142, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:36作业');
INSERT INTO `meaasge` VALUES (144, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:37作业');
INSERT INTO `meaasge` VALUES (145, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:37作业');
INSERT INTO `meaasge` VALUES (146, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:37作业');
INSERT INTO `meaasge` VALUES (148, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:38作业');
INSERT INTO `meaasge` VALUES (149, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:38作业');
INSERT INTO `meaasge` VALUES (150, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:38作业');
INSERT INTO `meaasge` VALUES (152, 1, 2, 1, 2, 1, '2班:王渊老师:发布:39作业');
INSERT INTO `meaasge` VALUES (153, 1, 3, 1, 2, 1, '2班:王渊老师:发布:39作业');
INSERT INTO `meaasge` VALUES (155, 1, 2, 1, 2, 1, '2班:王渊老师:发布:40作业');
INSERT INTO `meaasge` VALUES (156, 1, 3, 1, 2, 1, '2班:王渊老师:发布:40作业');
INSERT INTO `meaasge` VALUES (158, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:41作业');
INSERT INTO `meaasge` VALUES (159, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:41作业');
INSERT INTO `meaasge` VALUES (160, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:41作业');
INSERT INTO `meaasge` VALUES (162, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:42作业');
INSERT INTO `meaasge` VALUES (163, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:42作业');
INSERT INTO `meaasge` VALUES (164, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:42作业');
INSERT INTO `meaasge` VALUES (166, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:43作业');
INSERT INTO `meaasge` VALUES (167, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:43作业');
INSERT INTO `meaasge` VALUES (168, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:43作业');
INSERT INTO `meaasge` VALUES (170, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:44作业');
INSERT INTO `meaasge` VALUES (171, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:44作业');
INSERT INTO `meaasge` VALUES (172, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:44作业');
INSERT INTO `meaasge` VALUES (174, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:45作业');
INSERT INTO `meaasge` VALUES (175, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:45作业');
INSERT INTO `meaasge` VALUES (176, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:45作业');
INSERT INTO `meaasge` VALUES (178, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:46作业');
INSERT INTO `meaasge` VALUES (179, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:46作业');
INSERT INTO `meaasge` VALUES (180, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:46作业');
INSERT INTO `meaasge` VALUES (182, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:47作业');
INSERT INTO `meaasge` VALUES (183, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:47作业');
INSERT INTO `meaasge` VALUES (184, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:47作业');
INSERT INTO `meaasge` VALUES (186, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:48作业');
INSERT INTO `meaasge` VALUES (187, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:48作业');
INSERT INTO `meaasge` VALUES (188, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:48作业');
INSERT INTO `meaasge` VALUES (190, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:49作业');
INSERT INTO `meaasge` VALUES (191, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:49作业');
INSERT INTO `meaasge` VALUES (192, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:49作业');
INSERT INTO `meaasge` VALUES (194, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:50作业');
INSERT INTO `meaasge` VALUES (195, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:50作业');
INSERT INTO `meaasge` VALUES (196, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:50作业');
INSERT INTO `meaasge` VALUES (198, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:51作业');
INSERT INTO `meaasge` VALUES (199, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:51作业');
INSERT INTO `meaasge` VALUES (200, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:51作业');
INSERT INTO `meaasge` VALUES (202, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:52作业');
INSERT INTO `meaasge` VALUES (203, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:52作业');
INSERT INTO `meaasge` VALUES (204, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:52作业');
INSERT INTO `meaasge` VALUES (206, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:53作业');
INSERT INTO `meaasge` VALUES (207, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:53作业');
INSERT INTO `meaasge` VALUES (208, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:53作业');
INSERT INTO `meaasge` VALUES (210, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:54作业');
INSERT INTO `meaasge` VALUES (211, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:54作业');
INSERT INTO `meaasge` VALUES (212, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:54作业');
INSERT INTO `meaasge` VALUES (214, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:55作业');
INSERT INTO `meaasge` VALUES (215, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:55作业');
INSERT INTO `meaasge` VALUES (216, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:55作业');
INSERT INTO `meaasge` VALUES (218, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:56作业');
INSERT INTO `meaasge` VALUES (219, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:56作业');
INSERT INTO `meaasge` VALUES (220, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:56作业');
INSERT INTO `meaasge` VALUES (222, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:57作业');
INSERT INTO `meaasge` VALUES (223, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:57作业');
INSERT INTO `meaasge` VALUES (224, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:57作业');
INSERT INTO `meaasge` VALUES (226, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:58作业');
INSERT INTO `meaasge` VALUES (227, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:58作业');
INSERT INTO `meaasge` VALUES (228, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:58作业');
INSERT INTO `meaasge` VALUES (230, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:59作业');
INSERT INTO `meaasge` VALUES (231, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:59作业');
INSERT INTO `meaasge` VALUES (232, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:59作业');
INSERT INTO `meaasge` VALUES (234, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:60作业');
INSERT INTO `meaasge` VALUES (235, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:60作业');
INSERT INTO `meaasge` VALUES (236, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:60作业');
INSERT INTO `meaasge` VALUES (238, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:61作业');
INSERT INTO `meaasge` VALUES (239, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:61作业');
INSERT INTO `meaasge` VALUES (240, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:61作业');
INSERT INTO `meaasge` VALUES (242, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:62作业');
INSERT INTO `meaasge` VALUES (243, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:62作业');
INSERT INTO `meaasge` VALUES (244, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:62作业');
INSERT INTO `meaasge` VALUES (246, 1, 2, 1, 2, 1, '2班:王渊老师:发布:63作业');
INSERT INTO `meaasge` VALUES (247, 1, 3, 1, 2, 1, '2班:王渊老师:发布:63作业');
INSERT INTO `meaasge` VALUES (249, 1, 2, 1, 2, 1, '0:1班:王渊老师:修改:true作业');
INSERT INTO `meaasge` VALUES (250, 1, 3, 1, 2, 1, '0:1班:王渊老师:修改:true作业');
INSERT INTO `meaasge` VALUES (251, 1, 4, 1, 2, 1, '0:1班:王渊老师:修改:true作业');
INSERT INTO `meaasge` VALUES (253, 1, 2, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (254, 1, 3, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (255, 1, 4, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (257, 1, 2, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (258, 1, 3, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (259, 1, 4, 1, 2, 1, '0:6班:王渊老师:修改:6作业');
INSERT INTO `meaasge` VALUES (261, 1, 2, 1, 2, 1, '2班:王渊老师:发布:64作业');
INSERT INTO `meaasge` VALUES (262, 1, 3, 1, 2, 1, '2班:王渊老师:发布:64作业');
INSERT INTO `meaasge` VALUES (264, 1, 2, 1, 2, 1, '2班:王渊老师:发布:65作业');
INSERT INTO `meaasge` VALUES (265, 1, 3, 1, 2, 1, '2班:王渊老师:发布:65作业');
INSERT INTO `meaasge` VALUES (267, 1, 2, 1, 2, 1, '2班:王渊老师:发布:66作业');
INSERT INTO `meaasge` VALUES (268, 1, 3, 1, 2, 1, '2班:王渊老师:发布:66作业');
INSERT INTO `meaasge` VALUES (270, 1, 2, 1, 2, 1, '2班:王渊老师:发布:67作业');
INSERT INTO `meaasge` VALUES (271, 1, 3, 1, 2, 1, '2班:王渊老师:发布:67作业');
INSERT INTO `meaasge` VALUES (273, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:68作业');
INSERT INTO `meaasge` VALUES (274, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:68作业');
INSERT INTO `meaasge` VALUES (275, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:68作业');
INSERT INTO `meaasge` VALUES (277, 1, 2, 1, 2, 1, '1班:王渊老师:发布:69作业');
INSERT INTO `meaasge` VALUES (278, 1, 3, 1, 2, 1, '1班:王渊老师:发布:69作业');
INSERT INTO `meaasge` VALUES (279, 1, 4, 1, 2, 1, '1班:王渊老师:发布:69作业');
INSERT INTO `meaasge` VALUES (281, 1, 2, 1, 2, 1, '0:69班:王渊老师:修改:69作业');
INSERT INTO `meaasge` VALUES (282, 1, 3, 1, 2, 1, '0:69班:王渊老师:修改:69作业');
INSERT INTO `meaasge` VALUES (283, 1, 4, 1, 2, 1, '0:69班:王渊老师:修改:69作业');
INSERT INTO `meaasge` VALUES (285, 1, 2, 1, 2, 1, '1班:王渊老师:发布:70作业');
INSERT INTO `meaasge` VALUES (286, 1, 3, 1, 2, 1, '1班:王渊老师:发布:70作业');
INSERT INTO `meaasge` VALUES (287, 1, 4, 1, 2, 1, '1班:王渊老师:发布:70作业');
INSERT INTO `meaasge` VALUES (289, 1, 2, 1, 2, 1, '1班:王渊老师:发布:74作业');
INSERT INTO `meaasge` VALUES (290, 1, 3, 1, 2, 1, '1班:王渊老师:发布:74作业');
INSERT INTO `meaasge` VALUES (291, 1, 4, 1, 2, 1, '1班:王渊老师:发布:74作业');
INSERT INTO `meaasge` VALUES (293, 1, 2, 1, 2, 1, '1班:王渊老师:发布:75作业');
INSERT INTO `meaasge` VALUES (294, 1, 3, 1, 2, 1, '1班:王渊老师:发布:75作业');
INSERT INTO `meaasge` VALUES (295, 1, 4, 1, 2, 1, '1班:王渊老师:发布:75作业');
INSERT INTO `meaasge` VALUES (297, 1, 2, 1, 2, 1, '1班:王渊老师:发布:76作业');
INSERT INTO `meaasge` VALUES (298, 1, 3, 1, 2, 1, '1班:王渊老师:发布:76作业');
INSERT INTO `meaasge` VALUES (299, 1, 4, 1, 2, 1, '1班:王渊老师:发布:76作业');
INSERT INTO `meaasge` VALUES (301, 1, 2, 1, 2, 1, '1班:王渊老师:发布:77作业');
INSERT INTO `meaasge` VALUES (302, 1, 3, 1, 2, 1, '1班:王渊老师:发布:77作业');
INSERT INTO `meaasge` VALUES (303, 1, 4, 1, 2, 1, '1班:王渊老师:发布:77作业');
INSERT INTO `meaasge` VALUES (305, 1, 2, 1, 2, 1, '1班:王渊老师:发布:78作业');
INSERT INTO `meaasge` VALUES (306, 1, 3, 1, 2, 1, '1班:王渊老师:发布:78作业');
INSERT INTO `meaasge` VALUES (307, 1, 4, 1, 2, 1, '1班:王渊老师:发布:78作业');
INSERT INTO `meaasge` VALUES (311, 1, 2, 1, 2, 1, '1班:王渊老师:发布:79作业');
INSERT INTO `meaasge` VALUES (312, 1, 3, 1, 2, 1, '1班:王渊老师:发布:79作业');
INSERT INTO `meaasge` VALUES (313, 1, 4, 1, 2, 1, '1班:王渊老师:发布:79作业');
INSERT INTO `meaasge` VALUES (317, 1, 1, 1, 2, 1, '1班:王渊老师:发布:80作业');
INSERT INTO `meaasge` VALUES (318, 1, 2, 1, 2, 1, '1班:王渊老师:发布:80作业');
INSERT INTO `meaasge` VALUES (319, 1, 3, 1, 2, 1, '1班:王渊老师:发布:80作业');
INSERT INTO `meaasge` VALUES (320, 1, 4, 1, 2, 1, '1班:王渊老师:发布:80作业');
INSERT INTO `meaasge` VALUES (328, 1, 2, 1, 2, 1, '1班:王渊老师:发布:81作业');
INSERT INTO `meaasge` VALUES (329, 1, 3, 1, 2, 1, '1班:王渊老师:发布:81作业');
INSERT INTO `meaasge` VALUES (330, 1, 4, 1, 2, 1, '1班:王渊老师:发布:81作业');
INSERT INTO `meaasge` VALUES (332, 1, 2, 1, 2, 1, '1班:王渊老师:发布:82作业');
INSERT INTO `meaasge` VALUES (333, 1, 3, 1, 2, 1, '1班:王渊老师:发布:82作业');
INSERT INTO `meaasge` VALUES (334, 1, 4, 1, 2, 1, '1班:王渊老师:发布:82作业');
INSERT INTO `meaasge` VALUES (336, 1, 2, 1, 2, 1, '1班:王渊老师:发布:83作业');
INSERT INTO `meaasge` VALUES (337, 1, 3, 1, 2, 1, '1班:王渊老师:发布:83作业');
INSERT INTO `meaasge` VALUES (338, 1, 4, 1, 2, 1, '1班:王渊老师:发布:83作业');
INSERT INTO `meaasge` VALUES (339, 1, 1, 1, 2, 1, '1班:王渊老师:发布:84作业');
INSERT INTO `meaasge` VALUES (340, 1, 2, 1, 2, 1, '1班:王渊老师:发布:84作业');
INSERT INTO `meaasge` VALUES (341, 1, 3, 1, 2, 1, '1班:王渊老师:发布:84作业');
INSERT INTO `meaasge` VALUES (342, 1, 4, 1, 2, 1, '1班:王渊老师:发布:84作业');
INSERT INTO `meaasge` VALUES (343, 1, 1, 1, 2, 1, '1班:王渊老师:发布:85作业');
INSERT INTO `meaasge` VALUES (344, 1, 2, 1, 2, 1, '1班:王渊老师:发布:85作业');
INSERT INTO `meaasge` VALUES (345, 1, 3, 1, 2, 1, '1班:王渊老师:发布:85作业');
INSERT INTO `meaasge` VALUES (346, 1, 4, 1, 2, 1, '1班:王渊老师:发布:85作业');
INSERT INTO `meaasge` VALUES (347, 1, 1, 1, 2, 1, '1班:王渊老师:发布:86作业');
INSERT INTO `meaasge` VALUES (348, 1, 2, 1, 2, 1, '1班:王渊老师:发布:86作业');
INSERT INTO `meaasge` VALUES (349, 1, 3, 1, 2, 1, '1班:王渊老师:发布:86作业');
INSERT INTO `meaasge` VALUES (350, 1, 4, 1, 2, 1, '1班:王渊老师:发布:86作业');
INSERT INTO `meaasge` VALUES (351, 1, 1, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (352, 1, 2, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (353, 1, 3, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (354, 1, 4, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (355, 1, 1, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (356, 1, 2, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (357, 1, 3, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (358, 1, 4, 1, 2, 1, '0:2班:王渊老师:修改:2作业');
INSERT INTO `meaasge` VALUES (359, 1, 1, 1, 2, 1, '1:王渊老师:打回:9作业');
INSERT INTO `meaasge` VALUES (360, 1, 1, 1, 2, 1, '1:王渊老师:打回:8作业');
INSERT INTO `meaasge` VALUES (361, 1, 1, 2, 1, 1, '0:陈联盛学生:提交:2作业');
INSERT INTO `meaasge` VALUES (362, 1, 1, 2, 1, 1, '0:陈联盛学生:提交:2作业');
INSERT INTO `meaasge` VALUES (366, 1, 1, 2, 1, 1, '0:陈联盛学生:提交:80作业');
INSERT INTO `meaasge` VALUES (367, 1, 1, 1, 2, 1, '1:1班:王渊老师:发布:87作业');
INSERT INTO `meaasge` VALUES (368, 1, 2, 1, 2, 1, '1:1班:王渊老师:发布:87作业');
INSERT INTO `meaasge` VALUES (369, 1, 3, 1, 2, 1, '1:1班:王渊老师:发布:87作业');
INSERT INTO `meaasge` VALUES (370, 1, 4, 1, 2, 1, '1:1班:王渊老师:发布:87作业');

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES (1, '2019-2020第一学期');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `scode` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `photo` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `unreadinfo` int(255) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '201701', '陈联盛', '男', '201701', -6);
INSERT INTO `student` VALUES (2, '201702', '钟武', '男', '201702', 88);
INSERT INTO `student` VALUES (3, '201703', '罗锐', '男', '201703', 90);
INSERT INTO `student` VALUES (4, '201704', '廖前祥', '男', '201704', 81);
INSERT INTO `student` VALUES (5, '201705', '唐伟真', '男', '201705', 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `degree` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `photo` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `unreadinfo` int(255) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '001', '王渊', '男', '博士', '讲师', ' 	主要从事与Web开发、智能化软件以及数字媒体领域等相关的教学科研工作。个人近几年主要讲授《JavaScript程序设计》、《Web程序设计》、《软件工程导论》、《PHP程序设计》、《JSP程序设计》、《Android程序设计与开发》等课程。', '001', 7);
INSERT INTO `teacher` VALUES (2, '002', '刘金', '男', '博士', '讲师', ' 	刘金，男，工学博士，讲师。2014年4月毕业于西南交通大学，获计算机应用技术专业博士学位，同年入职江西师范大学。承担了《数据库原理与技术》、《软件工程》的本科课堂教学、研究生课程《信息系统分析与设计》及软件学院“移动开发”工作室的教学工作。课堂教学质量多年评为优秀。主持江西省教育厅科学技术研究项目、江西省级教学改革研究课题（重点）等项目。以第一作者发表SCI期刊论文、EI检索论文等10余篇。指导学生获 “互联网+”创新创业大赛及中国计算机设计大赛等全国二等奖等多项，并多次荣获“优秀指导老师”称号。', '002', 0);

SET FOREIGN_KEY_CHECKS = 1;
