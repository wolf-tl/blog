/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.19 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `blog`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` longtext NOT NULL,
  `firstPicture` varchar(200) NOT NULL,
  `flag` varchar(200) NOT NULL,
  `views` int(100) NOT NULL,
  `appreciation` tinyint(1) NOT NULL,
  `shareStatement` tinyint(1) NOT NULL,
  `commentabled` tinyint(1) NOT NULL,
  `published` tinyint(1) NOT NULL,
  `recommend` tinyint(1) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `typeId` int(100) NOT NULL,
  `userId` int(100) NOT NULL,
  `tagIds` varchar(200) NOT NULL,
  `description` longtext NOT NULL,
  `commentCount` int(200) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`title`,`content`,`firstPicture`,`flag`,`views`,`appreciation`,`shareStatement`,`commentabled`,`published`,`recommend`,`createTime`,`updateTime`,`typeId`,`userId`,`tagIds`,`description`,`commentCount`) values (8,'测试3','进行分类与标签功能测试','www.baidu.com','原创',1,0,0,0,1,1,'2022-03-10 00:00:00','2022-03-12 00:00:00',18,1,'12','1',0),(9,'测试4','blog.type.name=null的调试','www.baidu.com','转载',1,0,0,0,1,1,'2022-03-10 00:00:00','2022-03-12 00:00:00',19,1,'11','2',0),(10,'测试4','测试blog.type.name为空的问题','www.baidu.com','转载',0,0,0,0,1,1,'2022-03-10 00:00:00','2022-03-12 00:00:00',20,1,'13','3',NULL),(11,'测试5','进一步测试type.name','www.baidu.com','翻译',0,0,0,0,1,1,'2022-03-10 00:00:00','2022-03-12 00:00:00',19,1,'12','4',NULL),(12,'测试6','**测试查询功能**','www.baidu.com','转载',0,0,1,0,1,0,'2022-03-10 00:00:00','2022-03-12 00:00:00',18,1,'12','5',NULL),(13,'进行下一步开发','进行下一步开发','www.baidu.com','转载',2,1,1,0,1,1,'2022-03-10 00:00:00','2022-03-12 00:00:00',18,1,'11,12,13','测试标签显示在前端的功能',0),(14,'是否可以评论','@[TOC](初次接触Git ,记录一些坑)\r\n# git命令行和Linux差不多\r\n# 配置GIT，我们需要创建一个当前登录用户的全局配置文件.gitconfig\r\n这个文件的路径默认创建在用户环境变量HOME下\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2b001f75283c4a8db88d1385a83fa713.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5Lq65bel5pm66J-R,size_20,color_FFFFFF,t_70,g_se,x_16)\r\n首次配置需要通过输入git指令创建：\r\n\r\n```bash\r\ngit config --global user.name \"tl\"  #名称\r\ngit config --global user.email \"xxx\"   #邮箱\r\n```\r\n# 生成ssh公钥\r\n在用户目录下运行Git Bash , 输入\r\n\r\n```bash\r\nssh-keygen -t -rsa\r\n```\r\n\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/bd7268caf4ab4f1a83b7ed384d2e1311.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5Lq65bel5pm66J-R,size_20,color_FFFFFF,t_70,g_se,x_16)\r\n上面的是私钥，不能告诉别人；下面的是公钥，把公钥复制到gitee/github上，每个电脑有一个公钥，将之绑定在远程仓库，就可以免密连接远程仓库；\r\n\r\n# 版本库连接远程仓库\r\n注意：远程库中仓库的名称尽量和版本库所在的文件（工作区）名称一致；\r\n\r\n有两种方式：\r\n$1.$ 先创建版本库，再连接远程仓库；\r\n$2.$ 直接克隆一个远程仓库，然后在这个克隆的仓库（工作区）上进行开发。\r\n## 先创建版本库，再连接远程仓库\r\n### 创建版本库\r\n创建一个空目录，cd进去，显示当前路径；\r\n这个空目录就是工作区；\r\n```bash\r\n$ mkdir learngit\r\n$ cd learngit\r\n$ pwd\r\n/Users/michael/learngit\r\n```\r\n初始化版本库，版本库就创建好了；\r\n\r\n```bash\r\n$ git init\r\n```\r\n### 连接远程仓库\r\n\r\n```bash\r\n$ git remote add origin git@gitee.com:tl/learngit.git\r\n```\r\n第一次连接远程仓库时需要把版本库的master分支和远程的master分支关联起来：加上-u\r\norigin 是远程库的默认别称；\r\n```bash\r\n$ git push -u origin master\r\n```\r\n### 查看连接状态\r\n\r\n```bash\r\n$ git remote -v\r\n```\r\n### 删除远程库\r\n\r\n```bash\r\n$ git remote rm <远程库平台名字（自定义的）>\r\n```\r\n## 直接克隆一个远程仓库\r\n\r\n```bash\r\n$ git clone git@gitee.com:tl/learngit.git\r\n```\r\n执行后，本地就有learngit文件夹了，也即工作区；\r\n# 工作区与版本库\r\n版本库可以有多个分支；默认的是一个主分支；\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/08d4c2c15f59488ebbc958d795174d7c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5Lq65bel5pm66J-R,size_20,color_FFFFFF,t_70,g_se,x_16)\r\n# 本地和远程仓库的礼尚往来\r\n## 本地通过push更新远程仓库\r\n**通过该指令将某个分支的所有文件或者所有修改的文件更新到远程仓库；**\r\n```bash\r\n$ git push\r\n```\r\n\r\n## 本地通过pull将远程仓库的更新合并到本地\r\n**有时候远程仓库文件比你的本地更新，你需要将其合并来更新本地的，从而达成一致；**\r\n```bash\r\n$ git pull\r\n```\r\n\r\n## 更新远程仓库\r\n本地工作区创建了一个文件或者修改了某个文件，需要添加到缓存，再到分支，再push到远程仓库；\r\n\r\n```bash\r\n$ git add <file>\r\n$ git commit -m \"注释\" \r\n$ git push\r\n```\r\n## 删除文件\r\n删除单个文件：`$ git rm <file>`\r\n删除某个路径：`$ git rm -r <路径>`\r\n### 仅删除远程仓库与版本库的文件\r\n**加上--cached就不影响工作区的文件；**\r\n```bash\r\n$ git rm --cached <file>\r\n$ git commit -m \"注释\"\r\n$ git push\r\n```\r\n### 仅删除版本库的文件\r\n```bash\r\n$ git rm --cached <file>\r\n$ git commit -m \"注释\"\r\n```\r\n### 远程仓库连同工作区的文件一起删\r\n**注意：这里的删除把本地工作区的也删除了；**\r\n\r\n```bash\r\n$ git rm <file>\r\n$ git commit -m \"注释\"\r\n$ git push\r\n```\r\n## 查看版本库状态\r\n**用于提示是否有更新文件没有加到缓存区，或者没有提交到分支上；以及本地与远程之间的差异；**\r\n```bash\r\n$ git status\r\n```\r\n### 更新版本库\r\n$1.$ 添加修改到缓存区；\r\n$2.$ 提交修改到版本库的分支上；\r\n```bash\r\n$ git add <file>\r\n$ git commit -m \"注释\"\r\n```\r\n\r\n## 查看版本库与工作区的差异\r\n```bash\r\n$ git diff HEAD -- <file>\r\n```\r\n\r\n## 查看已提交到分支上的文件\r\n\r\n```bash\r\n$ git ls-files\r\n```\r\n## 撤回restore\r\n顾名思义，就是撤回的意思；\r\n```bash\r\n$ git restore <file>\r\n```\r\n\r\n## 版本回退reset\r\n\r\n```bash\r\n$ git reset --hard HEAD^\r\n```\r\n或\r\n\r\n```bash\r\n$ git reset --hard HEAD~100\r\n```\r\n或\r\n\r\n```bash\r\n$ git reset --hard commit id(模糊查询，不用补全)\r\n```\r\n## 查看文件修改记录\r\n**注意：有时运行`$ git log `会卡住光标，解决办法就是扩大命令行窗口；**\r\n```bash\r\n$ git log\r\n```\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/65e82b1d891e43d6818a8474b235d1ab.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5Lq65bel5pm66J-R,size_20,color_FFFFFF,t_70,g_se,x_16)\r\n\r\n\r\n## 查看你的每一次命令记录\r\n\r\n```bash\r\n$ git reflog\r\n```\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/885bb06273f04ad2ab68b361a1351cfa.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5Lq65bel5pm66J-R,size_20,color_FFFFFF,t_70,g_se,x_16)\r\n\r\n\r\n# 带git的指令是处理版本库的，不带的则处理工作区的\r\n**默认情况下，版本库会更新本地工作区的文件**\r\n# 只要把文件提交到分支上，就不用担心误删了\r\n**因为可以通过回退找到之前的版本；**\r\n**Git是基于存储修改的，他会记录你每一次修改，并通过指针来指向某一次修改版本，操作快，占存小。**','https://picsum.photos/200/300','原创',18,1,1,1,1,1,'2022-03-11 00:00:00','2022-03-14 00:00:00',18,1,'12','7',9),(15,'草稿是否能展示在首页','测试草稿的属性','https://picsum.photos/200/300?grayscale','转载',0,1,1,1,0,1,'2022-03-11 00:00:00','2022-03-12 00:00:00',18,1,'11','8',NULL);

/*Table structure for table `t_blog_tags` */

DROP TABLE IF EXISTS `t_blog_tags`;

CREATE TABLE `t_blog_tags` (
  `tagId` int(100) NOT NULL,
  `blogId` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_blog_tags` */

insert  into `t_blog_tags`(`tagId`,`blogId`) values (11,13),(12,13),(13,13),(12,8),(11,9),(13,10),(12,11),(12,12),(11,15),(12,14);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `blogId` bigint(20) DEFAULT NULL,
  `parentCommentId` bigint(20) DEFAULT NULL,
  `adminComment` bit(1) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`nickname`,`email`,`content`,`avatar`,`createTime`,`blogId`,`parentCommentId`,`adminComment`) values (1,'人工智障','3136695130@qq.com','你好','/images/tl.jpg','2022-03-13 03:45:44',14,-1,'\0'),(2,'人工智障','3136695130@qq.com','你好','/images/tl.jpg','2022-03-13 03:57:32',14,-1,'\0'),(3,'人工智障','3136695130@qq.com','你好','/images/tl.jpg','2022-03-13 04:02:18',14,-1,'\0'),(4,'人工智障','3136695130@qq.com','你好啊','/images/tl.jpg','2022-03-13 04:06:12',14,-1,'\0'),(6,'人工智障','3136695130@qq.com','不想吃了','/images/tl.jpg','2022-03-13 04:27:45',14,-1,'\0'),(7,'人工智障','3136695130@qq.com','拆好啦','/images/tl.jpg','2022-03-13 06:46:45',14,-1,'\0'),(8,'陌生人','3136695130@qq.com','hello!','/images/tl.jpg','2022-03-13 08:01:32',14,1,'\0'),(9,'wolf-tl','3136695130@qq.com','niyehaoa ','/images/tl.jpg','2022-03-13 08:02:28',14,8,''),(10,'lll','3136695130@qq.com','测试子级评论','/images/tl.jpg','2022-03-13 08:25:15',14,9,'\0');

/*Table structure for table `t_friend` */

DROP TABLE IF EXISTS `t_friend`;

CREATE TABLE `t_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blogaddress` varchar(255) NOT NULL,
  `blogname` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `pictureaddress` varchar(255) NOT NULL,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_friend` */

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `parent_message_id` bigint(20) DEFAULT NULL,
  `admin_message` bit(1) NOT NULL,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_message` */

insert  into `t_message`(`id`,`nickname`,`email`,`content`,`avatar`,`create_time`,`parent_message_id`,`admin_message`) values (1,'人工智障','3136695130@qq.com','nihaoa ','/images/tl.jpg','2022-03-14 06:42:23',-1,'\0'),(3,'wolf-tl','3136695130@qq.com','你好啊','/images/tl.jpg','2022-03-14 07:35:28',1,''),(4,'人工智障','3136695130@qq.com','akjfljfl','/images/tl.jpg','2022-03-14 08:59:12',-1,'\0');

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_tag` */

insert  into `t_tag`(`id`,`name`) values (11,'spring'),(12,'springboot'),(13,'stp');

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_type` */

insert  into `t_type`(`id`,`name`) values (18,'java'),(19,'c'),(20,'c++');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `avatar` varchar(200) NOT NULL,
  `type` int(100) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`nickname`,`username`,`password`,`email`,`avatar`,`type`,`createTime`,`updateTime`) values (1,'wolf-tl','tl','457fed2d5b922f79e1dd4b92f3262cc5','3136695130@qq.com','/images/tl.jpg',1,'2022-03-08 00:00:00','2022-03-08 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
