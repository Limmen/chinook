/*
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar
 */
package limmen.chinook_rest.integration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;

/**
 *
 * @author Kim Hammar
 */
public class QueryManager {
    private DBhandler db;
    private PreparedStatement getAllArtists;

    public QueryManager(DBhandler db){
        this.db = db;
        prepareStatements();
    }
    private void prepareStatements(){
        try{
            getAllArtists = db.getConnection().prepareStatement("SELECT * FROM \"Artist\";");
            
        }
        catch(SQLException e){
            
        }
    }
    public JSONArray getAllArtists(){
        ResultSet rs = null;
        if(db.isConnected()){
            try{
                rs = getAllArtists.executeQuery();
                return ResultSetConverter.convert(rs);
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
            finally{
                try{
                    if(rs != null)
                        rs.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
   
    public void cleanUp(){
        try{
            getAllArtists.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
