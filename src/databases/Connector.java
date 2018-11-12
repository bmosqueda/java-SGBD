package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector 
{
    protected String host;
    protected String database;
    protected String user;
    protected String password;
    protected String port;
    
    protected String className;
    protected Connection connection;
    protected boolean isConnected;
    private boolean isMaria;
    
    public Connector(String host, String database, String user, String password, String port, String className) 
    {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
        
        this.isConnected = false;
        this.className = className;
        this.isMaria = className == "mysql";
    }
    
    public void openConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(this.isMaria ? "com.mysql.jdbc.Driver" : "org.postgresql.Driver");

        connection = DriverManager.getConnection(
                "jdbc:"+this.className+"://" + this.host + ":" + this.port + "/" + this.database,
                this.user,
                this.password
        );

        this.isConnected = true;
    }
    
    public void close() throws SQLException
    {
        connection.close();
        this.isConnected = false;
    }
    
    public ArrayList<String> getDatabases() throws SQLException, ClassNotFoundException
    {
        openConnection();
        String sql = this.isMaria ? "SHOW DATABASES" : "SELECT datname AS \"Database\" FROM pg_database WHERE datname != 'template0' AND datname != 'template1'";
        
        System.out.println("Databases: " + sql);
        
        PreparedStatement stament = connection.prepareStatement(sql);
        ResultSet result = stament.executeQuery();
        ArrayList<String> databases = new ArrayList();

        while (result.next()) 
        {
            databases.add(result.getString("Database"));
        }

        result.close();
        close();
        
        return databases;
    }
    
    public ArrayList<String> getTables(String table) throws SQLException, ClassNotFoundException
    {
        this.database = table;
        openConnection();
        String sql = this.isMaria ? "SELECT TABLE_NAME AS 'Table' FROM information_schema.TABLES WHERE TABLE_SCHEMA = '"+table+"'"
                    : "SELECT tablename AS \"Table\" FROM pg_tables WHERE schemaname = 'public'";
        
        System.out.println("Tables: " + sql);
        
        PreparedStatement stament = connection.prepareStatement(sql);
        ResultSet result = stament.executeQuery();
        ArrayList<String> databases = new ArrayList();

        while (result.next()) 
        {
            databases.add(result.getString("Table"));
        }

        result.close();
        close();
        
        return databases;
    }
    
    public ArrayList<String> getColumns(String table, String database) throws SQLException, ClassNotFoundException
    {
        openConnection();
        
        String sql = this.isMaria ? "SELECT COLUMN_NAME AS 'Column' FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '"+database+"' AND TABLE_NAME = '"+table+"'"
            : "select column_name AS \"Column\" from INFORMATION_SCHEMA.COLUMNS where table_name = '"+table+"'";
        
        System.out.println("Columns: " + sql);
        
        PreparedStatement stament = connection.prepareStatement(sql);
        ResultSet result = stament.executeQuery();
        ArrayList<String> databases = new ArrayList();

        while (result.next()) 
        {
            databases.add(result.getString("Column"));
        }

        result.close();
        close();
        
        return databases;
    }
    
    public ArrayList<ArrayList<String>> getBySQL(String sql) throws SQLException, ClassNotFoundException
    {
        openConnection();
        
        PreparedStatement stament = connection.prepareStatement(sql);
        ResultSet result = stament.executeQuery();
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
        
        ResultSetMetaData metaData = result.getMetaData();
        int count = metaData.getColumnCount(); //number of column
            
        ArrayList<String> columns = new ArrayList<String>();
        for (int i = 1; i <= count; i++) {
            columns.add(metaData.getColumnLabel(i));
        }
        
        rows.add(columns);

        while (result.next()) 
        {
            ArrayList<String> temp = new ArrayList<String>();
            for (int i = 1; i <= count; i++) {
                temp.add(result.getString(metaData.getColumnLabel(i)));
            }
            
            rows.add(temp);
        }

        result.close();
        close();
        
        return rows;
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}