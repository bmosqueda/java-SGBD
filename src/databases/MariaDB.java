package databases;

import databases.models.Column;
import databases.models.Database;
import databases.models.Table;
import java.sql.SQLException;
import java.util.ArrayList;

public class MariaDB extends Connector
{
    public MariaDB(String host, String database, String user, String password, String port) 
    {
        super(host, database, user, password, port, Connector.SERVER.MARIADB);
    }
    
    @Override
    public Database[] getDatabases() throws SQLException, ClassNotFoundException 
    {
        String sql = "SHOW DATABASES";

        return this.getDatabasesBySQL(sql);
    }
    
    @Override
    public Table[] getTables(String database) throws SQLException, ClassNotFoundException 
    {
        String sql = "SELECT TABLE_NAME AS 'Table' FROM information_schema.TABLES WHERE TABLE_SCHEMA = '" + database + "'";
        
        return this.getTablesBySQL(sql);
    }
    
    @Override
    public Column[] getColumns(String table) throws SQLException, ClassNotFoundException 
    {
        //String sql = "SELECT COLUMN_NAME AS 'Column' FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '"+database+"' AND TABLE_NAME = '"+table+"'";
        String sql = "  SELECT \n" +
                    "    COLUMN_NAME AS 'Field'," +
                    "    DATA_TYPE AS 'Type'," +
                    "    IS_NULLABLE AS 'Null'," +
                    "    COLUMN_KEY AS 'Key' " +
                    "  FROM information_schema.COLUMNS " +
                    "  WHERE TABLE_SCHEMA = '"+this.database+"' AND TABLE_NAME = '"+table+"'";
        
        return this.getColumnsBySQL(sql);
    }
}
