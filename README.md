Menu Authority Management System(菜单权限管理系统) 
----------------------

# 简介

mams是一套菜单和权限管理的系统，用户管理用户可访问的目录，或者用户所拥有的权限。


# 数据依赖

mams需要在mysql 5.7+的版本上(需支持JSON存储)，导入三个表:

mams_auth:  保存权限信息。
mams_menu:  保存菜单信息。
mams_group: 保存组信息。

详情请见: 文件mams-create-tables.sql。

# 用法


