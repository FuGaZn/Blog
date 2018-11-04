# 开发日志

2018/11/4

初步架构

Controller:处理前端http请求

Repository：数据库操作接口

Repositorympl:数据库操作接口的实现

Model:模板类

Service：Controller对后端相关操作的调用接口

ServiceImpl

技术栈：

Vue

Spring Boot

Maven

Hibernate



本日工作：

- 搭建后端框架：

  spring boot + hibernate

  解决mysql驱动找不到的问题：maven引入符合本机mysql版本的版本

  hibernate配置

  - 配置hibernate.cfg.xml文件
  - 配置dataHelper，实现Hibernate下的数据的增删改查。

  ​