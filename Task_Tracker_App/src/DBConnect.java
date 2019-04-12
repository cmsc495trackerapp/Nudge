
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
                                                    + "TaskAppDatabase",
                                            "userAdmin", 
                                            "userAdmin");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
        
    }
    
}
