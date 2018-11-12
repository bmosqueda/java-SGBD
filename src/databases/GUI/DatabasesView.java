package databases.GUI;

import databases.Connector;
import java.awt.BorderLayout;
import java.awt.Button;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class DatabasesView extends javax.swing.JFrame {
    
    private Connector connector;
    private ArrayList<String> databases;
    private ArrayList<String> tables[];
    private ArrayList<String> columns[];
    private JTree tree;
    
    public DatabasesView(Connector connector) 
    {
        //create the root node
        this.connector = connector;
        JScrollPane scroll = null;
        
        try {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Databases");        
            
            this.databases = connector.getDatabases();
            this.tables = new ArrayList[this.databases.size()];
            this.columns = new ArrayList[this.databases.size()];
                
            DefaultMutableTreeNode tablesNodes[] = new DefaultMutableTreeNode[this.databases.size()];
                
            
            for (int i = 0; i < this.databases.size(); i++) 
            {
                tablesNodes[i] = new DefaultMutableTreeNode(this.databases.get(i));
                ArrayList<String> tables = connector.getTables(this.databases.get(i));

                for (int ii = 0; ii < tables.size(); ii++) {
                    DefaultMutableTreeNode tempTable = new DefaultMutableTreeNode(tables.get(ii));
                    
                    this.columns[i] = connector.getColumns(tables.get(ii), this.databases.get(i));
                    
                    for (int iii = 0; iii < columns[i].size(); iii++) {
                        tempTable.add(new DefaultMutableTreeNode(columns[i].get(iii)));
                    }
                    tablesNodes[i].add(tempTable);
                }
                root.add(tablesNodes[i]);
            }
            
            //create the tree by passing in the root node
            tree = new JTree(root);
            scroll = new JScrollPane(tree);
            
            add(scroll);
            
            
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("JTree Example");
            this.pack();
            this.setVisible(true);
            tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    tree.setScrollsOnExpand(true);
                    if(selectedNode.getParent().toString() == "Databases")
                        connector.setDatabase(selectedNode.toString());
                    else if(selectedNode.getParent().getParent().toString() == "Databases")
                        connector.setDatabase(selectedNode.getParent().toString());
                    else
                        connector.setDatabase(selectedNode.getParent().getParent().toString());
                    
                    System.out.println(selectedNode.getParent().toString());
                }
            });
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabasesView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabasesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        scroll.setSize(250, 500);
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
            .addGap(0, 500, Short.MAX_VALUE)
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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcecute, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtErrors)
                    .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtErrors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcecuteActionPerformed
        String sql = txtQuery.getText();
        try {
            ArrayList<ArrayList<String>> result = connector.getBySQL(sql);
            
            String rowData[][] = new String[result.size()][result.get(0).size()];
            
            /*for (int i = 0; i < result.get(0).size(); i++) {
                System.out.print(result.get(0).get(i) + "  |  ");
            }*/
            
            System.out.println("");
            
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < result.get(0).size(); j++) {
                    rowData[i][j] = result.get(i).get(j);
                    System.out.print(result.get(i).get(j) + "  |  ");
                }
                
                System.out.println("");
            }
            
            //panelTable.removeAll();
            
            System.out.println(result.size() - 1 + " rows in set");

            //TableModel model = new DefaultTableModel(rowData, result.get(0).toArray());*/
            //table = new JTable(model);
            //table.setVisible(true);
            
            //JTable table = new JTable(rowData, result.get(0).toArray());
            //table.setBounds(30, 40, 200, 300);
            
            
            DefaultTableModel model = new DefaultTableModel(rowData, result.get(0).toArray());
            tableRes.setModel(model);
            //panelTable.add();
            //panelTable.setSize(500, 500);
            //panelTable.setVisible(true);
        } catch (SQLException ex) {
            txtErrores.setText("Hubo un problema al ejecutar la consulta:\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            txtErrores.setText(ex.getMessage());
        }
        
    }//GEN-LAST:event_btnExcecuteActionPerformed

    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabasesView(true).setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnExcecute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableRes;
    private javax.swing.JScrollPane tableResult;
    private javax.swing.JTextArea txtErrores;
    private javax.swing.JScrollPane txtErrors;
    private java.awt.TextField txtQuery;
    // End of variables declaration//GEN-END:variables
}
