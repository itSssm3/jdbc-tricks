package impala;

import java.sql.DriverManager;

public class Impala {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:impala://127.0.0.1:8000;authmech=1;krbhostfqdn=host;KrbServiceName=impala;krbJAASFile=http://127.0.0.1:8000/JAAS.conf";
        Class.forName("com.cloudera.impala.jdbc41.Driver");
        DriverManager.getConnection(url);
    }
}
