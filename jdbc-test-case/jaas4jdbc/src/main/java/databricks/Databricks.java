package databricks;

import java.sql.DriverManager;

public class Databricks {
    public static void main(String[] args) throws Exception{
        Class.forName("com.databricks.client.jdbc.Driver");
        String url = "jdbc:databricks://localhost:10002;AuthMech=1;httpPath=test;KrbHostFQDN=test;KrbServiceName=test;krbJAASFile=http://localhost:8000/JAAS.conf";
        DriverManager.getConnection(url);
    }
}