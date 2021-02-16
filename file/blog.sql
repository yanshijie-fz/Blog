/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/02/2021 09:14:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `blog_id` bigint NULL DEFAULT NULL,
  `reply_user_Id` bigint NULL DEFAULT NULL,
  `pid` bigint NULL DEFAULT NULL,
  `comment_msg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reply_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 27, NULL, 0, '第一级评论', '2021-02-13 17:29:10', 'admin', '');
INSERT INTO `comment` VALUES (2, 2, 27, 1, 1, '第二级评论', '2021-02-13 17:29:43', 'user1', 'admin');
INSERT INTO `comment` VALUES (3, 1, 27, 2, 1, '第二级评论', '2021-02-13 17:30:05', 'admin', 'user1');
INSERT INTO `comment` VALUES (4, 3, 27, NULL, 0, '第一级评论', '2021-02-13 19:01:54', 'user2', '');
INSERT INTO `comment` VALUES (5, 1, 27, 3, 4, '第二级评论', '2021-02-13 19:02:22', 'admin', 'user2');
INSERT INTO `comment` VALUES (6, 1, 27, 1, 1, '第二级评论', '2021-02-14 18:34:22', 'admin', 'admin');
INSERT INTO `comment` VALUES (7, 1, 27, 1, 4, '第二级评论', '2021-02-14 19:17:00', 'admin', 'admin');
INSERT INTO `comment` VALUES (8, 1, 27, 0, 0, '这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论这是一个评论', '2021-02-14 20:14:21', 'admin', NULL);

-- ----------------------------
-- Table structure for editor
-- ----------------------------
DROP TABLE IF EXISTS `editor`;
CREATE TABLE `editor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `contentTitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contentDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `textContent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `modifyTime` datetime NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of editor
-- ----------------------------
INSERT INTO `editor` VALUES (27, '这是一篇测试博客', '用于测试博客访问是否正常', '<h1 id=\"h1-u6807u9898u4E00\"><a name=\"标题一\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题一</h1><p>这是测试内容</p>\r\n<h2 id=\"h2-u6807u9898u4E8C\"><a name=\"标题二\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题二</h2><ul>\r\n<li>这是标题二测试内容</li><li>这是标题二测试内容</li></ul>\r\n<h3 id=\"h3-u6807u9898u4E09\"><a name=\"标题三\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题三</h3><pre><code class=\"lang-html\">&lt;div id=&quot;layout&quot;&gt;\r\n                        &lt;header&gt;\r\n                            &lt;h1 th:text=&quot;${editor.getContentTitle()}&quot;&gt;标题&lt;/h1&gt;\r\n                            &lt;br&gt;\r\n                            &lt;span class=&quot;glyphicon glyphicon-user&quot; th:text=&quot;${&#39; &#39;+editor.getUser_name()}&quot;&gt;作者&lt;/span&gt;\r\n                            &amp;nbsp;\r\n                            &lt;span class=&quot;glyphicon glyphicon-calendar&quot; th:text=&quot;${&#39; &#39;+editor.getModifyTime()}&quot;&gt;2021-2-12 00:00:00&lt;/span&gt;\r\n                        &lt;/header&gt;\r\n                        &lt;div id=&quot;test-editormd-view&quot;&gt;\r\n                            &lt;textarea style=&quot;display:none;&quot; id=&quot;textContent&quot; th:text=&quot;${editor.getTextContent()}&quot;&gt;&lt;/textarea&gt;\r\n                        &lt;/div&gt;\r\n                    &lt;/div&gt;\r\n</code></pre>\r\n<p><img src=\"http://localhost:8080/img/upload/1/37c09197fe3648e7a34f643654603713\" alt=\"\"></p>\r\n', '# 标题一\r\n这是测试内容\r\n\r\n## 标题二\r\n- 这是标题二测试内容\r\n- 这是标题二测试内容\r\n\r\n### 标题三\r\n```html\r\n<div id=\"layout\">\r\n						<header>\r\n							<h1 th:text=\"${editor.getContentTitle()}\">标题</h1>\r\n							<br>\r\n							<span class=\"glyphicon glyphicon-user\" th:text=\"${\' \'+editor.getUser_name()}\">作者</span>\r\n							&nbsp;\r\n							<span class=\"glyphicon glyphicon-calendar\" th:text=\"${\' \'+editor.getModifyTime()}\">2021-2-12 00:00:00</span>\r\n						</header>\r\n						<div id=\"test-editormd-view\">\r\n							<textarea style=\"display:none;\" id=\"textContent\" th:text=\"${editor.getTextContent()}\"></textarea>\r\n						</div>\r\n					</div>\r\n```\r\n\r\n![](http://localhost:8080/img/upload/1/37c09197fe3648e7a34f643654603713)', '2021-02-12 16:12:47', '2021-02-12 16:12:47', 1, 'admin');
INSERT INTO `editor` VALUES (28, 'testtesttest', '用于测试博客访问是否正常', '<h1 id=\"h1-u6807u9898u4E00\"><a name=\"标题一\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题一</h1><p>这是测试内容</p>\r\n<h2 id=\"h2-u6807u9898u4E8C\"><a name=\"标题二\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题二</h2><ul>\r\n<li>这是标题二测试内容</li><li>这是标题二测试内容</li></ul>\r\n<h3 id=\"h3-u6807u9898u4E09\"><a name=\"标题三\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>标题三</h3><pre><code class=\"lang-html\">&lt;div id=&quot;layout&quot;&gt;\r\n                        &lt;header&gt;\r\n                            &lt;h1 th:text=&quot;${editor.getContentTitle()}&quot;&gt;标题&lt;/h1&gt;\r\n                            &lt;br&gt;\r\n                            &lt;span class=&quot;glyphicon glyphicon-user&quot; th:text=&quot;${&#39; &#39;+editor.getUser_name()}&quot;&gt;作者&lt;/span&gt;\r\n                            &amp;nbsp;\r\n                            &lt;span class=&quot;glyphicon glyphicon-calendar&quot; th:text=&quot;${&#39; &#39;+editor.getModifyTime()}&quot;&gt;2021-2-12 00:00:00&lt;/span&gt;\r\n                        &lt;/header&gt;\r\n                        &lt;div id=&quot;test-editormd-view&quot;&gt;\r\n                            &lt;textarea style=&quot;display:none;&quot; id=&quot;textContent&quot; th:text=&quot;${editor.getTextContent()}&quot;&gt;&lt;/textarea&gt;\r\n                        &lt;/div&gt;\r\n                    &lt;/div&gt;\r\n</code></pre>\r\n<p><img src=\"http://localhost:8080/img/upload/1/37c09197fe3648e7a34f643654603713\" alt=\"\"></p>\r\n', '# 标题一\r\n这是测试内容\r\n\r\n## 标题二\r\n- 这是标题二测试内容\r\n- 这是标题二测试内容\r\n\r\n### 标题三\r\n```html\r\n<div id=\"layout\">\r\n						<header>\r\n							<h1 th:text=\"${editor.getContentTitle()}\">标题</h1>\r\n							<br>\r\n							<span class=\"glyphicon glyphicon-user\" th:text=\"${\' \'+editor.getUser_name()}\">作者</span>\r\n							&nbsp;\r\n							<span class=\"glyphicon glyphicon-calendar\" th:text=\"${\' \'+editor.getModifyTime()}\">2021-2-12 00:00:00</span>\r\n						</header>\r\n						<div id=\"test-editormd-view\">\r\n							<textarea style=\"display:none;\" id=\"textContent\" th:text=\"${editor.getTextContent()}\"></textarea>\r\n						</div>\r\n					</div>\r\n```\r\n\r\n![](http://localhost:8080/img/upload/1/37c09197fe3648e7a34f643654603713)', '2021-02-12 16:12:47', '2021-02-12 16:12:47', 1, 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_grade` int NOT NULL,
  `user_email` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_gender` int NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, 'yanshijie.work@aliyun.com', 1);
INSERT INTO `user` VALUES (2, 'user1', 'aaaaaa', 2, 'yanshijie.work@aliyun.com', 1);
INSERT INTO `user` VALUES (3, 'user2', 'aaaaaa', 2, 'yanshijie.work@aliyun.com', 1);
INSERT INTO `user` VALUES (6, 'user3', 'aaaaaa', 2, '2096983987@qq.com', 0);

SET FOREIGN_KEY_CHECKS = 1;
