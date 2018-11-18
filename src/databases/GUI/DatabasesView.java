package databases.GUI;

import databases.Connector;
import databases.MariaDB;
import databases.PostgreSQL;
import databases.models.Column;
import databases.models.Database;
import databases.models.Table;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class DatabasesView extends javax.swing.JFrame {
    
    private Connector connector;
    //private Database databases[];
    private ArrayList<String> columns[];
    private JTree tree;
    
    public DatabasesView(Connector connector) 
    {
        this.connector = connector;
        JScrollPane scroll = null;
        
        try {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Databases");        
            
            Database[] databases = connector.getDatabases();
            
            int databasesLength = databases.length;
            //this.databases = new Database[databasesLength - 1];
            
            DatabaseNode databasesNodes[] = new DatabaseNode[databasesLength];
            
            for (int i = 0; i < databasesLength; i++)
            {
                databasesNodes[i] = new DatabaseNode(databases[i]);
                
                Table tables[] = connector.getTables(databases[i].getName());
                int tableLength = tables.length;
                
                for (int j = 0; j < tableLength; j++) 
                {
                    Column columns[] = connector.getColumns(tables[j].getName());
                    int columnLength = columns.length;
                    //Nodo de tabla
                    DefaultMutableTreeNode tempNodeTable = new DefaultMutableTreeNode(tables[j].getName());
                    
                    for (int k = 1; k < columnLength; k++) 
                    {
                        tempNodeTable.add(new DefaultMutableTreeNode(columns[k].getName()));
                    }
                    
                    tables[j].setColumns(columns);
                    databasesNodes[i].add(tempNodeTable);
                }
                
                root.add(databasesNodes[i]);
                databasesNodes[i].database.setTables(tables);
            }
            
            this.tree = new JTree(root);
            scroll = new JScrollPane(tree);
            
            add(scroll);
            
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            
            //Events
            tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                            
                    if (isDatabaseNode(selectedNode))
                    {
                        connector.setDatabase(selectedNode.toString());
                        DatabaseNode node = (DatabaseNode) selectedNode;
                        
                        System.out.println("DATABASENAME: " + node.database.getName());
                        System.out.println("First table: " + node.database.getTables()[0].getName());
                    }
                    else if (isTableNode(selectedNode))
                    {
                        connector.setDatabase(selectedNode.getParent().toString());
                        showForm(selectedNode.toString(), (DatabaseNode) selectedNode.getParent());
                    }
                    else if(isColumnNode(selectedNode))
                        connector.setDatabase(selectedNode.getParent().getParent().toString());

                    System.out.println(selectedNode.toString());
                }
            });
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabasesView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        scroll.setSize(250, 715);
    }
    
    public void showForm(String tableName, DatabaseNode node)
    {
        System.out.println("SHOWFORMS");
        Database db = node.database;
        Table table = findTable(tableName, db.getTables());
        if(table == null)
        {
            System.out.println("TABLA NO ENCONTRADA");
            return;
        }
        
        Column cols[] = table.getColumns();
        int colsCount = cols.length;
        //Component components[] = new Component[colsCount];
        
        for (int i = 0; i < colsCount; i++) 
        {
            System.out.println(cols[i]);
            scrollForm.add(createComponent(cols[i]));
        }
        
        scrollForm.revalidate();
        scrollForm.repaint();
    }
    
    private Component createComponent(Column col) {
        Component component = null;
        
        if(col.getType() == Column.TYPE.INT)
        {
            component = new JSpinner();
        }        
        else 
        {
            component = new JTextField(10);
        }
        
        component.setName(col.getName());
        
        return component;
    }
    
    public Table findTable(String table, Table[] tables)
    {
        for (int i = 0; i < tables.length; i++) 
            if(table.equals(tables[i].getName()))
                return tables[i];
        
        return null;
    }
    
    public static boolean isDatabaseNode(DefaultMutableTreeNode node)
    {
        return node.getLevel() == 1;
        /*if(node.isRoot())
            return false;
        
        return ((DefaultMutableTreeNode)node.getParent()).isRoot();*/
    }
    
    public static boolean isTableNode(DefaultMutableTreeNode node)
    {
        return node.getLevel() == 2;
        //return isDatabaseNode((DefaultMutableTreeNode) node.getParent());
    }
    
    public static boolean isColumnNode(DefaultMutableTreeNode node)
    {
        return node.getLevel() == 3;
        //return isTableNode((DefaultMutableTreeNode) node.getParent());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtQuery = new java.awt.TextField();
        label1 = new java.awt.Label();
        btnExcecute = new java.awt.Button();
        txtErrors = new javax.swing.JScrollPane();
        txtErrores = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        panelTable = new javax.swing.JPanel();
        tableResult = new javax.swing.JScrollPane();
        tableRes = new javax.swing.JTable();
        lblResulSet = new javax.swing.JLabel();
        scrollForm = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtQuery.setText("SELECT * FROM test.users");

        label1.setText("SQL:");

        btnExcecute.setLabel("Ejecutar");
        btnExcecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcecuteActionPerformed(evt);
            }
        });

        txtErrores.setColumns(20);
        txtErrores.setRows(5);
        txtErrors.setViewportView(txtErrores);

        jLabel1.setText("Errores:");

        panelTable.setBackground(new java.awt.Color(80, 115, 149));

        tableRes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResult.setViewportView(tableRes);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableResult)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableResult)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnExcecute, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtErrors)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(385, 385, 385))))
                        .addGap(37, 37, 37)
                        .addComponent(scrollForm, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblResulSet)
                        .addGap(252, 252, 252))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollForm, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResulSet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtErrors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcecuteActionPerformed
        String sql = txtQuery.getText();
        try {
            ArrayList<String[]> result = connector.getBySQL(sql);
            
            int rowsLength = result.size();
            int columnsLength = result.get(0).length;
            
            String rowData[][] = new String[rowsLength - 1][columnsLength];
            
            //La primera fila son metadatos de las columnas
            for (int i = 1; i < rowsLength; i++) 
            {
                for (int j = 0; j < columnsLength; j++) 
                {
                    rowData[i - 1][j] = result.get(i)[j];
                }
            }
            
            lblResulSet.setText((rowsLength - 1) + " filas devueltas");
            
            DefaultTableModel model = new DefaultTableModel(rowData, result.get(0));
            tableRes.setModel(model);
            
        } catch (SQLException ex) {
            txtErrores.setText("Hubo un problema al ejecutar la consulta:\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            txtErrores.setText(ex.getMessage());
        }
    }//GEN-LAST:event_btnExcecuteActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new DatabasesView(new PostgreSQL("localhost", "postgres", "postgres", "postPass", "5432")).setVisible(true);
                new DatabasesView(new MariaDB("localhost", "constructora", "root", "mariaPass", "3306")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnExcecute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private javax.swing.JLabel lblResulSet;
    private javax.swing.JPanel panelTable;
    private javax.swing.JScrollPane scrollForm;
    private javax.swing.JTable tableRes;
    private javax.swing.JScrollPane tableResult;
    private javax.swing.JTextArea txtErrores;
    private javax.swing.JScrollPane txtErrors;
    private java.awt.TextField txtQuery;
    // End of variables declaration//GEN-END:variables
}
