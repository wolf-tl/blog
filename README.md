# 项目名称：
个人网站：https://www.wolf-tl.top/index
# 项目描述：
本人在学习过程中喜欢记录分享，在使用CSDN博客后感觉该博客的分类标签做的不直观，难以快捷直观地查找相关文章，因此结合SpringBoot框架自主开发了个人博客，结合自己的写作习惯、分类习惯，设计了后台管理、前台展示的业务逻辑，经过多个月的使用，逐步修复Bug、完善功能。
# 设计技术：
SpringMVC、SpringBoot、MyBatis、MySQL、Redis、Html、Css、JavaScript、Jquery、
Thymeleaf、面向对象编程、面向接口编程等。
# 具体技术： 
1.项目的持久层使用了中间件MyBatis，项目使用了分页插件PageHelper，由于该插件根据pagesize在SQL语句后拼接limit限制，导致一对多联表查询会出现“乱分页”的现象，将一对多SQL语句优化成嵌套子查询的方式，这样分页插件查询的是外面一层blog数据，然后再嵌套地查询该blog对应的多个标签数据，从而实现一对多数据的分页查询；
2.前台登录模块使用redisTemplate完成验证码有效时间的设置以及避免重复发送验证码。
# 责任描述：
个人设计并开发项目的全部，包括后端与前端。