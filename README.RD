# **litchi**
```
    基于SpringBoot + SpringCloud + Feign + Redis + Consul + maven + MyBatis-plus + MySQL
    在接手这个项目后，最好熟悉一下以上的东西，特别是 maven，SpringCloud，MyBatis-plus以及一些java8 Stream，lambda的写法
```
## **Litchi 后端配置**
### **环境要求（请自行安装）**
	MySQL 5.6 +
	Redis 3.0.503 +
	Consul v1.6.2
	JDK 1.8 
	fast_dfs (用以分布式文件存储)

## **window系统下启动流程**
### **启动Redis**

    ./redis-server.exe redis.windows.conf

### **启动Consul**

    ./consul.exe agent -dev

### **启动MySQL**

    net start mysql

### **创建数据库**

    使用 Litchi\attachments\sql 的litchi.sql文件建立数据库。
    
### **fast_dfs**
    fast_dfs部署难度较大，如果不会自己部署，可以去网上找一个别人封装好的docker容器，直接使用。
