package databases;

import java.sql.SQLException;
import java.util.ArrayList;

public class MariaDB extends Connector
{
    public MariaDB(String host, String database, String user, String password, String port) 
    {
        super(host, database, user, password, port, Connector.SERVER.MARIADB);
    }
    
    @Override
    public ArrayList<String[]> getDatabases() throws SQLException, ClassNotFoundException 
    {
        String sql = "SHOW DATABASES";

        return this.getBySQL(sql);
    }
    
    @Override
    public ArrayList<String[]> getTables(String database) throws SQLException, ClassNotFoundException 
    {
        String sql = "SELECT TABLE_NAME AS 'Table' FROM information_schema.TABLES WHERE TABLE_SCHEMA = '" + database + "'";
        
        return this.getBySQL(sql);
    }
    
    @Override
    public ArrayList<String[]> getColumns(String table) throws SQLException, ClassNotFoundException 
    {
        String sql = "SELECT COLUMN_NAME AS 'Column' FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '"+database+"' AND TABLE_NAME = '"+table+"'";
        
        return this.getBySQL(sql);
    }
}
