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

### **开发，导入工程的流程**
#### 1. git clone本项目：***git@github.com:690677532/Litchi.git***
```
clone后请将本项目的相关git文件删除。意思就是你们新建一个git仓库来存储这个项目，不要使用我原来的仓库地址，用你们自己的。
具体不会可以百度一下，或者邮件问我。邮件我会在最后给出。
```
#### 2 .git clone一个fastdfs的客户端。***git@github.com:690677532/fastdfs_client.git***
```
这个是自己修改过的，主要是fastdfs的客户端，用来上传文件使用的。也可以使用官方的，只需要在pom.xml文件导入相关官方的依赖。但我不确定具体对接代码是否需要更改。
(litchi-source-server模块)。将 fastdfs_client mvn install 到本地仓库。
```
#### 3. 在你的IDE选择 Litchi/pom.xml文件打开即可。

### **项目的各模块功能介绍**
```
Litchi
├── litchi-model # 一些通用的模型，例如POJO，mapper，一些请求和返回的参数模型以及一些工具类
├── litchi—rpc   # RPC调用的接口模型
├── litchi-data-server # 节点，节点数据相关的服务层逻辑
├── litchi—customer—server # 用户相关的服务层逻辑
├── litchi-source-server # 文件上传相关的服务层逻辑
├── litchi-upload-server # 节点数据上传的逻辑
├── litchi-core # 聚合了litci-model，litchi-rpc作为一个公共库
├── litchi-api-server # 控制层，最顶层，为前端提供接口。
├── litchi-monitor-server # 监控规则以及定期监控的逻辑
├── litchi-order-server # 订单逻辑
├── litchi-product-server # 商品逻辑
# 其他没有提到的server属于规划中，也可以删除。
# 后缀为 -server的模块都是可以也需要单独部署的，即是一个独立可运行的jar。
```
### **一些提示和建议** ###
```
1. 先通过各个pom.xml文件了解各个模块的依赖关系。
2. 理解RPC调用的过程。
    即在这个项目中，如果要新增一个RPC接口，应该怎样写。
    其实只需要在litchi-rpc中新建接口或者在已有接口写一个方法，然后在对象的 *-server模块实现接口，即可在litchi-api-server中调用了。这个过程需要consul，用以发现服务。
3. 理解application.yml文件中各个参数的意义。consul，redis，mysql这些东西也是在这里面配置的。
4. 当你们本地可以运行其项目后，就可以去服务器上部署一遍了。因为现在服务器上运行的都是老的项目和接口，所以需要你们重新部署一下这个新的项目。
5. 我的旧代码可以 review 一下，里面有很多不优雅的实现以及一些临时的实现。特别是节点数据获取的那一部分。
6. litchi-api-server 多注意一下拦截器这些东西。
7. RPC接口声明以及实现的时候，要注意一下参数注解的使用。建议是根据我写过的，对get，post方法这些以及变量参数注解依葫芦画瓢，不然可能会有一些坑。
8. 把Litchi/attachments/doc/里面的论文过一遍，相关思路都有叙述。
```

### **部署流程——可执行Jar包配置文件**
具体配置见 **后端\可执行文件jar\.\application-example.yaml** 示例配置文件。

 -  将各 application-example.yaml 复制一份为 application.yaml 并修改内容为本地环境配置(主要需要修改的是MySQL配置，Redis配置，Consul配置)。
 -  指定 application.yaml 为配置文件运行Jar包即可。（请确保consul,redis,mysql已启动）

        java -jar demo.jar --spring.config.location=/path/to/application.yaml
### **注意**

 - litchi-api-server最后启动，其他jar包启动顺序不做要求。

 - 可在consul管理平台（默认端口8500），如localhost:8500，查看
   litchi-data-server,litchi-customer-server,litchi-monitor-server，litchi-upload-server是否注册服务成功

 - 系统初始账号 root 初始密码 123
 
 ### **邮箱**
 jieliangcao@qq.com
 项目有不理解的地方可以邮件问我