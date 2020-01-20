
package servlet.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.model.Mail;
import servlet.services.MailService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class mailServlet extends HttpServlet {
    private MailService mailservice ;
    @Override
    public void init() throws ServletException{
        super.init();
        mailservice = new MailService() ;
    }
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        try {
            List<Mail> mails= mailservice.find();
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json");
                Gson gson = new Gson();
                if(mails.isEmpty()){ out.print("no Mails in database");}
                else if(mails.size()>0){
                    String jsondata = gson.toJson(mails);
                    out.print(jsondata);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(mailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONParser parser = new JSONParser();
        JSONObject jObj = null;
        try {
            jObj = (JSONObject) parser.parse(sb.toString());
        } catch (ParseException ex) {
            Logger.getLogger(mailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id = Integer.valueOf(jObj.get("mailid").toString());
        String sender = jObj.get("mailsender").toString();
        String receiver = jObj.get("mailreceiver").toString();
        String object = jObj.get("mailobject").toString();
        String subject = jObj.get("mailsubject").toString();
        
        mailservice.add("INSERT INTO `mail`(`id`, `sender`, `receiver`,`object`,`subject`) VALUES ('"+id+"','"+sender+"','"+receiver+"','"+object+"','"+subject+"')");
        
        }
    @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONParser parser = new JSONParser();
        JSONObject jObj = null;
        
        try {
            jObj = (JSONObject) parser.parse(sb.toString());
        } catch (ParseException ex) {
            Logger.getLogger(mailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        int id = Integer.valueOf(jObj.get("mailid").toString());
        mailservice.delete("DELETE FROM mail WHERE id = '"+id+"'");

        }
}
