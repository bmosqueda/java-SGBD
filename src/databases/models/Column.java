package databases.models;

public class Column 
{
    private String name;
    private TYPE type;
    private boolean isNullable;
    private CONSTRAINT constraint;
    
    public static enum TYPE 
    {
        TEXT, INT, DATE
    }
    
    public static enum CONSTRAINT 
    {
        PK, FK
    }

    public Column(String name, String type, String isNullable, String constraint) 
    {
        this.name = name;
        
        switch(type)
        {
            case "int": case "tinyint": case "smallint": case "integer":
                this.type = TYPE.INT;
                break;
            case "varchar": case "text":
                this.type = TYPE.TEXT;
                break;
            case "date": case "time": case "timestamp":
                this.type = TYPE.DATE;
                break;
            default:
                this.type = null;
        }
        
        this.isNullable = !"NO".equals(isNullable);
        
        switch(constraint)
        {
            case "PRI":
                this.constraint = CONSTRAINT.PK;
                break;
            case "MUL":
                this.constraint = CONSTRAINT.FK;
                break;
            default:
                this.constraint = null;
        }
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

    public TYPE getType() {
        return type;
    }

    public boolean isIsNullable() {
        return isNullable;
    }

    public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
