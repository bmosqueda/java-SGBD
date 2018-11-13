package databases.models;

public class Column 
{
    private String name;
    private Type type;
    
    public static enum Type 
    {
        TEXT, INT, DATE
    }

    public Column(String name, Type type) 
    {
        this.name = name;
        this.type = type;
    }

    public Column(String name) 
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
