 <p align="center">
  <img src="https://img.shields.io/badge/Avue-1.5.0-green.svg" alt="Build Status">
   <img src="https://img.shields.io/badge/Spring%20Cloud-Greenwich.RELEASE-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Spring%20Boot-2.1.5.RELEASE-blue.svg" alt="Downloads">
 </p>   
 


   
![](https://images.gitee.com/uploads/images/2019/0330/065147_85756aea_410595.png)   

#### 核心依赖 


依赖 | 版本
---|---
Spring Boot |  2.1.5.RELEASE  
Spring Cloud | Greenwich.RELEASE   
Spring Security OAuth2 | 2.3.3
Mybatis Plus | 3.1.0
hutool | 4.5.10
Avue | 1.5.0
   


#### 模块说明
```lua
pig
├── pig-ui -- 前端工程[8080]
├── pig-auth -- 授权服务提供[3000]
└── pig-common -- 系统公共模块 
     ├── pig-common-core -- 公共工具类核心包
     ├── pig-common-log -- 日志服务
     └── pig-common-security -- 安全工具类
├── pig-config -- 配置中心[8888]
├── pig-eureka -- 服务注册与发现[8761]
├── pig-gateway -- Spring Cloud Gateway网关[9999]
└── pig-upms -- 通用用户权限管理模块
     └── pig-upms-api -- 通用用户权限管理系统公共api模块
     └── pig-upms-biz -- 通用用户权限管理系统业务处理模块[4000]
└── pig-visual  -- 图形化模块 
     ├── pig-monitor -- Spring Boot Admin监控 [5001]
     ├── pig-zipkin -- 链路调用监控 [5002]
     └── pig-codegen -- 图形化代码生成[5003]
	 
```
