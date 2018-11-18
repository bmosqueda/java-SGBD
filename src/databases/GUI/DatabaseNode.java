package databases.GUI;

import databases.models.Database;
import javax.swing.tree.DefaultMutableTreeNode;

public class DatabaseNode extends DefaultMutableTreeNode
{
    public Database database;

    public DatabaseNode(Database database) 
    {
        super(database.getName());
        this.database = database;
    }
}