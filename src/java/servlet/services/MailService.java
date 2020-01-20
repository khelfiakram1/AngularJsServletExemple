
package servlet.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import servlet.dao.MailDao;
import servlet.model.Mail;


public class MailService {
    private MailDao maildao = new MailDao();

   
  public List<Mail>find() throws SQLException{
    List<Mail> list = new ArrayList<Mail>();
    try{
        list = maildao.find();
        
    }catch(SQLException e){

    }
      
      
    return list;
  
  }
  public void add(String sql){
  maildao.add(sql);
  }
  
  public void delete(String sql){
  maildao.delete(sql);
  }
}
