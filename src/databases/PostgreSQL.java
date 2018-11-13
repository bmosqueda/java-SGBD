package databases;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostgreSQL extends Connector
{
    
    public PostgreSQL(String host, String database, String user, String password, String port) 
    {
        super(host, database, user, password, port, Connector.SERVER.POSTGRESQL);
    }
    
    @Override
    public ArrayList<String[]> getDatabases() throws SQLException, ClassNotFoundException 
    {
        String sql = "SELECT datname AS \"Database\" FROM pg_database WHERE datname != 'template0' AND datname != 'template1'";

        return this.getBySQL(sql);
    }

    @Override
    public ArrayList<String[]> getTables(String database) throws SQLException, ClassNotFoundException 
    {
        this.database = database;
        String sql = "SELECT tablename AS \"Table\" FROM pg_tables WHERE schemaname = 'public'";

        return this.getBySQL(sql);
    }

    @Override
    public ArrayList<String[]> getColumns(String table) throws SQLException, ClassNotFoundException 
    {
        String sql = "select column_name AS \"Column\" from INFORMATION_SCHEMA.COLUMNS where table_name = '"+table+"'";

        return this.getBySQL(sql);
    }
}