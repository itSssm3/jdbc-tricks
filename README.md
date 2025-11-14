# jdbc-tricks

## 深入JDBC安全：特殊URL构造与不出网反序列化利用技术揭秘

![JDBC](https://img.shields.io/badge/JDBC-Security-red)
![MySQL](https://img.shields.io/badge/MySQL-Driver-blue)
![Research](https://img.shields.io/badge/Security-Research-green)

本项目汇总了JDBC安全研究成果，专注于特殊URL构造技术与不出网反序列化利用方法的探索与实践。

[English Version](README_EN.md)

议题PPT附件：
[深入JDBC安全：特殊URL构造与不出网反序列化利用技术揭秘.pptx](深入JDBC安全：特殊URL构造与不出网反序列化利用技术揭秘.pptx)

## 项目结构

```-
jdbc-tricks/
├── LICENSE
├── README.md
├── dump-mysql-properties/        # MySQL 驱动默认安全属性分析
├── jdbc-test-case/               # JDBC 测试用例集合
│   ├── mysql-driver/             # MySQL Driver 各版本默认属性与绕过技巧
│   ├── h2-driver/                # H2 JDBC 特殊 URL 构造与不出网利用
│   ├── postgreSQL-driver/        # PostgreSQL JDBC PoC 与利用测试
│   ├── jaas4jdbc/                # 利用 JAAS 机制的 JDBC 攻击面
│   └── utils/                    # 公共工具类（如 MysqlConnectionUtils）
├── jdbc-url-fuzzer/              # JDBC URL Fuzzing 测试框架
├── real-world-case/              # 真实世界漏洞案例集合
```

## 🔍 Tricks 列表

### MYSQL Driver Tricks

已知tricks：

- default properties ：默认属性绕过
    - [DefaultProperties.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/default_properties/DefaultProperties.java)
- multi host ：多host写法绕过
    - [Connection URL Syntax](https://dev.mysql.com/doc/connector-j/en/connector-j-reference-jdbc-url-format.html#connector-j-url-user-credentials)
    - [AllowLoadLocal_MultiHostInjectionBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/AllowLoadLocal_MultiHostInjectionBypass.java)
- space between ：键值插入空格绕过
    - [AllowLoadLocal_SpaceBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_SpaceBetweenKeyValueBypass.java)
- tab between ：键值插入 \t 等制表符绕过
    - [AllowLoadLocal_TabBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_TabBetweenKeyValueBypass.java)
- upper case ：键值大写绕过
    - [AllowLoadLocal_TrueUpperCaseBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/upper_case/AllowLoadLocal_TrueUpperCaseBypass.java)

会议公开内容：

- no-outbound：jdbc不出网利用
    - [no-outbound/README.md](jdbc-test-case/mysql-driver/no-outbound/README.md)
- no-outbound-spring：jdbc不出网利用spring环境
    - [not-outbound-spring/README.md](jdbc-test-case/mysql-driver/not-outbound-spring/README.md)
- multi-host and equalsIgnoreCase bypass
    - [AllowLoadLocal_MultiHost_equalsIgnoreCase_bypass](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/AllowLoadLocal_MultiHost_equalsIgnoreCase_bypass.java)
    - [equalsIgnoreCase bypass key fuzz case](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/fuzzCase1.java)
- other-between
    - [AllowLoadLocal_OtherBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_OtherBetweenKeyValueBypass.java)
    - [ 空白字符fuzz case](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/fuzzCase2.java)
- QuoteBypass
    - [QuoteBypass.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/quote_bypass/QuoteBypass.java)
- ReplicationBypass
    - [ReplicationBypass.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/replication/ReplicationBypass.java) 

非会议公开内容：

- bypass_max_allowed_packet 5.1.16版本示例
    - [绕过max_allowed_packet参数](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/README.md)
    - [DefaultProperties.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/DefaultProperties.java)
    - [bypass_max_allowed_packet.py](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/bypass_max_allowed_packet.py)

### Other Driver Tricks

#### H2 JDBC Tricks

- 关键字绕过
  - 反斜杠绕过
    - [BackslashBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/BackslashBypass.java)
  - 大小写绕过
    - [CaseConversionBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/CaseConversionBypass.java)
  - unicode绕过
    - [UnicodeBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/UnicodeBypass.java)
- 特殊fearure
  - 利用不可见字符代替空格
    - [NoSpacePoc](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/NoSpacePoc.java)
  - RUNSCRIPT FROM不出现.sql直接连接ip
    - [NoSqlSuffix](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/NoSqlSuffix.java)
  - RUNSCRIPT FROM连接本地文件
    - [ConnectToLocalFiles](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/ConnectToLocalFiles.java)
  - 不出网poc自动生成脚本
    - [NoNetworkConversion](jdbc-test-case/h2-driver/NoNetworkConversion/)

#### PostgreSQL JDBC Tricks

- Jaas PoC 示例
  - [App.java](jdbc-test-case/postgreSQL-driver/src/main/java/com/jdbc/tricks/App.java)

#### JAAS for JDBC Tricks

- Databricks JDBC JAAS 利用示例
  - [Databricks.java](jdbc-test-case/jaas4jdbc/src/main/java/databricks/Databricks.java)
- Impala JDBC JAAS 利用示例
  - [Impala.java](jdbc-test-case/jaas4jdbc/src/main/java/impala/Impala.java)

#### JDBC Utils

- MySQL 连接工具类（辅助构造与复用 PoC）
  - [MysqlConnectionUtils.java](jdbc-test-case/utils/src/main/java/com/jdbc/tricks/utils/MysqlConnectionUtils.java)

## 🔥 真实世界漏洞案例

案例大纲：
[real-world-case/README.md](real-world-case/README.md)

- 2025-04-20 L0ne1y 贡献案例集
  [real-world-case/2025-04-20-L0ne1y](real-world-case/2025-04-20-L0ne1y)
- 2025-06-23 fushuling 贡献案例集 [real-world-case/2025-06-23-fushuling](real-world-case/2025-06-23-fushuling)

## 其他优秀的开源JDBC 项目

tricks:
- cwkiller [JDBC-PROXY-Bypass](https://github.com/cwkiller/JDBC-PROXY-Bypass) : 利用代理驱动绕过JDBC Attack检测

JDBC tools:
- rmb122 [rogue_mysql_server](https://github.com/rmb122/rogue_mysql_server) : A rouge mysql server supports reading files from most mysql libraries of multiple programming languages.

## 🤝 贡献指南

欢迎贡献新的JDBC安全研究成果！请遵循以下步骤：

1. Fork本项目
2. 创建您的特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交您的更改 (`git commit -m '添加一些功能'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 打开Pull Request

### 贡献者
<a href="https://github.com/yulate/jdbc-tricks/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=yulate/jdbc-tricks" />
</a>

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=yulate/jdbc-tricks&type=Date)](https://www.star-history.com/#yulate/jdbc-tricks&Date)

## 📄 License

本项目遵循项目根目录中 [LICENSE](LICENSE) 文件的规定。

---

⚠️ **免责声明**: 本项目仅用于安全研究和教育目的，请在合法授权的环境中进行测试。
