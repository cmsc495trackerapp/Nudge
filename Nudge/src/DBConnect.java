/*File: DBConnect.java
 *Author: Zackary Scott
 *Date: 4/18/2019
 *Purpose: Creates a server connection and connects to a database to set and
 *get data.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.drda.NetworkServerControl;

public class DBConnect {
    private static NetworkServerControl server = null;
    DBConnect(){
        try {
            //creates apache derby server for db connection
            server = new NetworkServerControl();
            server.start(null);
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, 
                                                            null, 
                                                            ex);
        }
    }
    //Creates a connection to the database
    //Also creates tables and database if they don't exist.
    public static Connection connectDB(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(  
                                            "jdbc:derby://localhost:1527/"
                                                + "TaskAppDatabase;create=true",
                                            "userAdmin", 
                                            "userAdmin");
            ResultSet res = con.getMetaData().getTables(null, 
                                                        "USERADMIN", 
                                                        "LOGINTABLE",
                                                        null);
            if(!res.next()){
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE TABLE LOGINTABLE "
                        +"(USERNAME VARCHAR(30) PRIMARY KEY, "
                        +"PASSWORD VARCHAR(30))");
            }
            ResultSet resTwo = con.getMetaData().getTables(null, 
                                                        "USERADMIN", 
                                                        "TASKTABLE",
                                                        null);
            if(!resTwo.next()){
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE TABLE TASKTABLE "
                        +"(ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                        +"USERNAME VARCHAR(30) NOT NULL, "
                        +"CATEGORY VARCHAR(30) NOT NULL, "
                        +"DATE VARCHAR(20)  NOT NULL, "
                        +"TIME VARCHAR(20)  NOT NULL, "
                        +"TASK  VARCHAR(500) NOT NULL)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, 
                                                            null,
                                                            ex);
        }
        return con;
    }
    //Shutsdown the server
    public static void serverStop(){
        try {
            server.shutdown();
        } catch (Exception ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, 
                                                            null, 
                                                            ex);
        }
    }
    public static NetworkServerControl getServer(){
        return server;
    }
}
