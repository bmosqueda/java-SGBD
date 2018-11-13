package databases.models;

public class Table 
{
    private Column[] columns;
    private String name;
    
    public Table(String name)
    {
        this.name = name;
    }
    
    public void setColumns(Column[] columns)
    {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
