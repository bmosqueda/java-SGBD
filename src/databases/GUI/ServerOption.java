package databases.GUI;

public class ServerOption extends javax.swing.JFrame {

    public ServerOption() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        btnPostgres = new javax.swing.JButton();
        btnMaria = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPostgres.setText("PostgreSQL");
        btnPostgres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostgresActionPerformed(evt);
            }
        });

        btnMaria.setText("MySQL / MariaDB");
        btnMaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMariaActionPerformed(evt);
            }
        });

        jLabel1.setText("Selecciona tu servidor de bases de datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMaria)
                    .addComponent(btnPostgres, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(btnMaria)
                .addGap(18, 18, 18)
                .addComponent(btnPostgres)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMariaActionPerformed
        ConnectionForm2 form = new ConnectionForm2(true);
        this.dispose();
        form.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_btnMariaActionPerformed

    private void btnPostgresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostgresActionPerformed
        ConnectionForm2 form = new ConnectionForm2(false);
        this.dispose();
        form.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_btnPostgresActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerOption().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMaria;
    private javax.swing.JButton btnPostgres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
