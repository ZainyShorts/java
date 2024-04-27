package carrental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    
    Connection c;
    Statement s;

    public database () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/conenction", "root", "");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
