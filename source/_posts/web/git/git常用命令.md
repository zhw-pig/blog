---
title: git常用命令
date: 2022-03-14 21:15:06
tags: [git]
keywords: git命令
categories:
- 前端
image: https://img-blog.csdnimg.cn/6275c941a3f247e29b73b6fd589050b5.png
top: 4
---

这里记录一些项目开发中，git的常用命令

<!-- more -->

## Git是什么
Git是**分布式**版本控制工具。简单来说，git就是基于命令指示来管理项目的。
### 什么是版本控制
记录文件修改的历史记录，方便版本切换
## Git安装
[git安装](https://git-scm.com/)
安装成功测试：git --version
![在这里插入图片描述](https://img-blog.csdnimg.cn/6275c941a3f247e29b73b6fd589050b5.png)
## Git工作机制
	1、工作区：本地代码的存在目录  【可以删除】
	2、暂存区：通过git add . 将工作区代码添加到暂存区 【可以删除】
	3、本地库：通过git commit 将暂存区代码提交到本地库，【此时又历史记录，不可以删除了】
	4、远程库：通过git push将本地库代码推送到远程库
### 远程库【即是：代码托管中心】
	1、局域网：GitLab 【公司内部服务器】
	2、互联网
		2.1、GitHub【外网】
		2.2、Gitee码云【国内网站】
代码托管中心是基于**网络服务器**的远程库
## GIt常用命令
	1、设置用户签名，【git首次安装必须设置用户签名】，用来区别git操作者身份
	      $ git config --global user.name 用户名
	      $ git config --global user.email 邮箱
	     查看设置签名是否成功：
	     	在C盘 ASUS目录下查找 .config文件，看自己设置的信息是否存在
	2、git初始化本地库：目的是让git获取管理代码权
	      $ git init
	3、文件添加到暂存区
	     $ git add .    【.代表将所有未添加到暂存区的文件添加到暂存区】
	     $ git add hello.js 【将hello.js这个文件添加到暂存区】
	4、文件从暂存区删除【工作区还存在该文件】
	     $ git rm --cached hello.js
	5、暂存区文件提交到本地库
	    $ 本次提交的标识名'
	6、查看版本 【包含版本号】
		$ git reflog 
		或者  $ git log  【详细信息】
		当前版本指向新提交的
	7、切换版本
		$ git reset --hard 版本号
## Git分支
分支可以理解为项目的副本，该副本单独在内存开辟新的空间，拥有自己的指针地址，不会影响其他任何分支。每个分支下会有不同的版本
### 分支的命令
	1、创建分支
		$ git branch 分支名
	2、查看分支
		$ git branch -v
	3、切换分支
		$ git checkout 分支名
	4、把指定的分支合并到当前分支上
		$ git merge 分支名
		注意：要把A分支合并到B分支上，需要先切换分支到B分支上，此时再用git merge A分支
### 代码分支合并冲突解决
分支冲突产生原因：**两个分支在同一文件的同一位置，有两套完全不同的修改**，此时需要人为决定新代码内容，步骤如下：
 * 手动修改冲突文件
 * git add . 添加到暂存区
 * git commit -m '提交的版本名'  提交到本地库  【git commit -m '提交的版本名' 不能再加文件名】