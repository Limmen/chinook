/*
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar
 */
package limmen.chinook_rest.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kim Hammar
 */
public class DBhandler {
    private Connection con;
    
    public DBhandler(){
        connect();
    }
    
    private void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/chinook",
                            "guest", "guest");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
    public void disconnect(){
        try{
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return con;
    }
    public boolean isConnected(){
        try{
            if(con.isValid(5))
                return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
    
