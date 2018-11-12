package databases;

public class PostgreSQL extends Connector{
    
    public PostgreSQL(String host, String database, String user, String password, String port) 
    {
        super(host, database, user, password, port, "postgresql");
    }
}
