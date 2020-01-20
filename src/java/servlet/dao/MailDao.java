
package servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import servlet.model.Mail;

public class MailDao {
    public List<Mail> find () throws SQLException{
    
        List<Mail> mail = new ArrayList<Mail>();
        connectorDB connection = new connectorDB();
        Connection con = connection.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from mail");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
        int id = rs.getInt(1);
        String sender = rs.getString(2);
        String receiver =rs.getString(3);
        String object=rs.getString(4);
        String subject=rs.getString(5);
        Mail mailO = new Mail(id,sender,receiver,object,subject);
        mail.add(mailO);
        
        }
    return mail;
    } 
    
    public void add(String sql)
    {
        connectorDB connection = new connectorDB();
        Connection con = connection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(String sql) {
       connectorDB connection = new connectorDB();
       Connection con = connection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
