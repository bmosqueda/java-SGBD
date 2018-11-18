package databases;

import databases.GUI.DatabaseNode;
import databases.models.Column;
import databases.models.Database;
import databases.models.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Connector 
{
    protected String host;
    protected String database;
    protected String user;
    protected String password;
    protected String port;
    
    protected Connection connection;
    protected boolean isConnected;
    private SERVER server;
    
    public static enum SERVER 
    {
        MARIADB ("mysql", "com.mysql.jdbc.Driver"), POSTGRESQL ("postgresql", "org.postgresql.Driver");
        
        private final String className;
        private final String driver;
        
        SERVER(String className, String driver)
        {
            this.className = className;
            this.driver = driver;
        }
        
        String className() {
            return this.className;
        }
        
        String driver() {
            return this.driver;
        }
    }
    
    public Connector(String host, String database, String user, String password, String port, SERVER server) 
    {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
        
        this.isConnected = false;
        this.server = server;
    }
    
    public void openConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(this.server.driver);

        connection = DriverManager.getConnection(
                "jdbc:"+this.server.className+"://" + this.host + ":" + this.port + "/" + this.database,
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
    
    public ArrayList<String[]> getBySQL(String sql) throws SQLException, ClassNotFoundException
    {
        System.out.println(sql);
        openConnection();
       
        PreparedStatement stament = connection.prepareStatement(sql);
        ResultSet resultSet = stament.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        
        int columnsCount = metaData.getColumnCount();
        String columns[] = new String[columnsCount];
        ArrayList<String[]> rows = new ArrayList<>();
        
        for (int i = 1; i <= columnsCount; i++) 
            columns[i - 1] = metaData.getColumnLabel(i);
        
        rows.add(columns);
        
        while (resultSet.next()) 
        {
            String temp[] = new String[columnsCount];
            for (int i = 1; i <= columnsCount; i++) 
                temp[i -1] = resultSet.getString(metaData.getColumnLabel(i));
            
            rows.add(temp);
        }

        resultSet.close();
        close();
        
        return rows;
    }
    
    public Database[] getDatabasesBySQL(String sql) throws SQLException, ClassNotFoundException
    {
        ArrayList<String[]> arrayDatabases = this.getBySQL(sql);
        int length = arrayDatabases.size();
        Database databases[] = new Database[length - 1];
        
        //Empieza en 1 porque la primera file es la metadata de las columnas
        for (int i = 1; i < length; i++)
            databases[i - 1] = new Database(arrayDatabases.get(i)[0]);
        
        return databases;
    }
    
    public Table[] getTablesBySQL(String sql) throws SQLException, ClassNotFoundException {
        ArrayList<String[]> arrayTables = this.getBySQL(sql);
        int length = arrayTables.size();
        Table tables[] = new Table[length - 1];

        //Empieza en 1 porque la primera file es la metadata de las columnas
        for (int i = 1; i < length; i++) 
            tables[i - 1] = new Table(arrayTables.get(i)[0]);
        

        return tables;
    }
    
    public Column[] getColumnsBySQL(String sql) throws SQLException, ClassNotFoundException {
        ArrayList<String[]> arrayColumns = this.getBySQL(sql);
        int length = arrayColumns.size();
        Column columns[] = new Column[length - 1];
        
        //Empieza en 1 porque la primera file es la metadata de las columnas
        for (int i = 1; i < length; i++) 
            columns[i - 1] = new Column(arrayColumns.get(i)[0], arrayColumns.get(i)[1], arrayColumns.get(i)[2], arrayColumns.get(i)[3]);
        

        return columns;
    }
    
    public abstract Database[] getDatabases() throws SQLException, ClassNotFoundException ;
    
    public abstract Table[] getTables(String database) throws SQLException, ClassNotFoundException;
    
    public abstract Column[] getColumns(String table) throws SQLException, ClassNotFoundException;
    
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