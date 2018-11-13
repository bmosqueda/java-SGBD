package databases.models;

public class Database 
{
    private Table[] tables;
    private String name;

    public Database(String name) 
    {
        this.name = name;
    }
    
    public void setTables(Table[] tables)
    {
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
