
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zack__000
 */
public class DBConnect {
    DBConnect(){
        try {
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection connectDB(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(  
                                            "jdbc:derby://localhost:1527/"
                                                    + "TaskAppDatabase;create=true",
                                            "userAdmin", 
                                            "userAdmin");
            ResultSet res = con.getMetaData().getTables(null, "USERADMIN", "LOGINTABLE", null);
            if(res.next()){
                
            }else{
                System.out.println("Table doesn't exist");
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE TABLE LOGINTABLE (USERNAME VARCHAR(30) PRIMARY KEY, PASSWORD VARCHAR(30), TASKS VARCHAR(500))");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
        
    }
    
}
