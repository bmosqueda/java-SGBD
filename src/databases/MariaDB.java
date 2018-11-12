package databases;

public class MariaDB extends Connector
{
    public MariaDB(String host, String database, String user, String password, String port) 
    {
        super(host, database, user, password, port, "mysql");
    }
}
