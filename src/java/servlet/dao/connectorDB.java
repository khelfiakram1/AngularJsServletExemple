
package servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectorDB {
    private Connection conn;
    private Statement st;
    public Connection getConnection(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/MailManager";
            String user = "akram";
            String pass = "12082000";
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }
        catch(ClassNotFoundException ex){
            System.out.println("Error"+ex.getMessage());
        }
        finally{
            return conn;
        }
    } 
}
